package ma.youcode.cmspringboot.model.dto.competitionDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public record CompetitionResponseDto(
        Long id,
        String code,
        LocalDate date,
        LocalDateTime startDate,
        LocalDateTime endTime,
        Integer numberOfParticipants,
        String location,
        Float amount
) {
}
