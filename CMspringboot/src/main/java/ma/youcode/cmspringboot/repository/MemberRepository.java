package ma.youcode.cmspringboot.repository;

import ma.youcode.cmspringboot.model.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository  extends JpaRepository<Member, Integer> {
    Optional<Member> findByNumOrNameOrFamilyName(Integer num, String name, String familyName);
    Optional<Member> findByIdentityNumber(String identityNumber);

    Optional<Member> findByNum(Integer integer);
}
