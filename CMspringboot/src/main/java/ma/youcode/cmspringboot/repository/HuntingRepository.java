package ma.youcode.cmspringboot.repository;

import ma.youcode.cmspringboot.model.domain.Hunting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting, Integer> {
}
