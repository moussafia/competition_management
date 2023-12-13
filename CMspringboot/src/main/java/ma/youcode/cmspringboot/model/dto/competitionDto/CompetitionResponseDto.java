package ma.youcode.cmspringboot.model.dto.competitionDto;

import java.time.LocalDate;
import java.time.LocalTime;

public record CompetitionResponseDto(
        String code,
        LocalDate date,
        LocalTime startDate,
        LocalTime endTime,
        Integer numberOfParticipants,
        String location,
        Float amount
) {
}
