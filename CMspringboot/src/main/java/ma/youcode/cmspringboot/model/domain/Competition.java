package ma.youcode.cmspringboot.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private LocalDate date;
    private LocalDateTime startDate;
    private LocalDateTime endTime;
    private Integer numberOfParticipants;
    private String location;
    private Float amount;
    @OneToMany(mappedBy = "competition")
    private Set<Ranking> ranking;
    @OneToMany(mappedBy = "competition")
    private Set<Hunting> hunting;
}
