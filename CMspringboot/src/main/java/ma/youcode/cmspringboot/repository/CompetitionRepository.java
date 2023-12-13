package ma.youcode.cmspringboot.repository;

import ma.youcode.cmspringboot.model.domain.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    Optional<Competition> findByDate(LocalDate date);
    Optional<Competition> findByDateAndCodeLike(LocalDate date, String code);
    Optional<Competition> findByCode(String code);

}
