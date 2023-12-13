package ma.youcode.cmspringboot.model.dto.competitionDto.competionSaveDto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder @AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class CompetitionRequestCreateDto {
    @NotBlank
    private LocalDate date;
    @NotBlank
    private LocalTime startDate;
    @NotBlank
    private LocalTime endTime;
    @Size(min = 3, max = 200, message = "length is too height or too low")
    private String location;
    @PositiveOrZero
    private Float amount;
}
