package ma.youcode.cmspringboot.model.mapper.competionDtoMapper;

import ma.youcode.cmspringboot.model.domain.Competition;
import ma.youcode.cmspringboot.model.dto.competitionDto.CompetitionRequestDto;
import ma.youcode.cmspringboot.model.dto.competitionDto.CompetitionResponseDto;

public class CompetitionDtoMapper {
    public static CompetitionResponseDto toCompetitionResponseDto(Competition competition){
        return new CompetitionResponseDto(competition.getId(), competition.getCode(),
                competition.getDate(), competition.getStartDate(), competition.getEndTime(),
                 competition.getNumberOfParticipants(), competition.getLocation(),
                competition.getAmount());
    }

    public static Competition toCompetition(CompetitionRequestDto competitionRequestDto){
        return new Competition().builder().id(competitionRequestDto.getId())
                .code(competitionRequestDto.getCode())
                .amount(competitionRequestDto.getAmount())
                .date(competitionRequestDto.getDate())
                .startDate(competitionRequestDto.getStartDate())
                .endTime(competitionRequestDto.getEndTime())
                .numberOfParticipants(competitionRequestDto.getNumberOfParticipants())
                .location(competitionRequestDto.getLocation()).build();
    }

}
