package ma.youcode.cmspringboot.model.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

@Getter @Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ranking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer ranke;
    private Integer score;
    @ManyToOne
    @JsonBackReference
    private Competition competition;
    @ManyToOne
    @JsonBackReference
    private Member member;
}