package ma.youcode.cmspringboot.repository;

import ma.youcode.cmspringboot.model.domain.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
    Optional<Level> findByCode(Integer code);
    @Query("select max(l.points) From Level l")
    Integer findMaxPoint();
}
