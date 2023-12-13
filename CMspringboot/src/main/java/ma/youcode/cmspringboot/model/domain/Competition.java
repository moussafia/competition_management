package ma.youcode.cmspringboot.model.domain;


import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Competition {
    @Id
    private String code;
    private LocalDate date;
    private LocalTime startDate;
    private LocalTime endTime;
    private Integer numberOfParticipants;
    @Size(min = 3, max = 200, message = "length is too low or too height")
    private String location;
    private Float amount;
    @OneToMany(mappedBy = "competition")
    private Set<Ranking> ranking;
    @OneToMany(mappedBy = "competition")
    private Set<Hunting> hunting;
}
