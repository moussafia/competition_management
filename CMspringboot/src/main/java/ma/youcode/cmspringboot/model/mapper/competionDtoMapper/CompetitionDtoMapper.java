package ma.youcode.cmspringboot.model.mapper.competionDtoMapper;

import ma.youcode.cmspringboot.model.domain.Competition;
import ma.youcode.cmspringboot.model.dto.competitionDto.competionSaveDto.CompetitionRequestCreateDto;
import ma.youcode.cmspringboot.model.dto.competitionDto.CompetitionResponseDto;

public class CompetitionDtoMapper {
    public static CompetitionResponseDto toCompetitionResponseDto(Competition competition){
        return new CompetitionResponseDto(competition.getCode(),
                competition.getDate(), competition.getStartDate(), competition.getEndTime(),
                 competition.getNumberOfParticipants(), competition.getLocation(),
                competition.getAmount());
    }

    public static Competition toCompetition(CompetitionRequestCreateDto competitionRequestCreateDto){
        return new Competition().builder()
                .date(competitionRequestCreateDto.getDate())
                .amount(competitionRequestCreateDto.getAmount())
                .startDate(competitionRequestCreateDto.getStartDate())
                .endTime(competitionRequestCreateDto.getEndTime())
                .location(competitionRequestCreateDto.getLocation()).build();
    }

}
