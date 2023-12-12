package ma.youcode.cmspringboot.repository;

import ma.youcode.cmspringboot.model.domain.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    LocalDateTime findByStartDate(LocalDateTime startDate);

}
