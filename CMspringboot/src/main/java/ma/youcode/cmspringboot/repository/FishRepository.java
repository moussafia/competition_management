package ma.youcode.cmspringboot.repository;

import ma.youcode.cmspringboot.model.domain.Fish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FishRepository extends JpaRepository<Fish, Long> {
}
