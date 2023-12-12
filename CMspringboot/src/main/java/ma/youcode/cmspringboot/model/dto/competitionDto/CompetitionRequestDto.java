package ma.youcode.cmspringboot.model.dto.competitionDto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Builder @AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class CompetitionRequestDto {
    private Long id;
    private String code;
    private LocalDate date;
    private LocalDateTime startDate;
    private LocalDateTime endTime;
    private Integer numberOfParticipants;
    private String location;
    private Float amount;
}
