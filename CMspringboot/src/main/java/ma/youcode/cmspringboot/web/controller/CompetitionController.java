package ma.youcode.cmspringboot.web.controller;

import ma.youcode.cmspringboot.model.domain.Competition;
import ma.youcode.cmspringboot.model.dto.competitionDto.competionSaveDto.CompetitionRequestCreateDto;
import ma.youcode.cmspringboot.model.dto.competitionDto.CompetitionResponseDto;
import ma.youcode.cmspringboot.model.mapper.competionDtoMapper.CompetitionDtoMapper;
import ma.youcode.cmspringboot.service.CompetitionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/competition")
public class CompetitionController {
    private CompetitionService competitionService;

    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @GetMapping
    public ResponseEntity<List<CompetitionResponseDto>> getAllCompetition(@RequestParam(defaultValue = "0")
                                                                          @Valid @Min(0) Integer page,
                                                                          @RequestParam(defaultValue = "1") @Min(1) Integer size,
                                                                          @RequestParam(defaultValue = "asc") @Pattern(regexp = "asc|desc" ,message = "invalid direction") String directionSort,
                                                                          @RequestParam(defaultValue = "date") String properties){
        Sort.Direction direction = Sort.Direction.fromString(directionSort);
        Sort sort = Sort.by(direction, properties);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<Competition> competitionPage = competitionService.getAllCompetition(pageRequest);
        List<CompetitionResponseDto> competitionResponseDtoList = new ArrayList<>();
        competitionPage.forEach(cp->{
            competitionResponseDtoList.add(CompetitionDtoMapper.toCompetitionResponseDto(cp));
        });
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("X-Total-Page", String.valueOf(competitionPage.getTotalPages()))
                .header("X-Total-Element", String.valueOf(competitionPage.getTotalElements()))
                .body(competitionResponseDtoList);

    }
    @PostMapping
    public ResponseEntity<CompetitionResponseDto> saveCompetition(@RequestBody
                                                                  CompetitionRequestCreateDto competitionRequestCreateDto){
        Competition competitionMapped = CompetitionDtoMapper.toCompetition(competitionRequestCreateDto);
        Competition competitionSaved = competitionService.createCompetition(competitionMapped);
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(CompetitionDtoMapper.toCompetitionResponseDto(competitionSaved));
    }
}
