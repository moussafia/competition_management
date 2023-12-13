package ma.youcode.cmspringboot.service;

import ma.youcode.cmspringboot.model.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberService {
    Member createMember(Member member);
    public Member updateMember(Member member);
    public Member getMemberByNum(Member member);
    Member searchMember(String keySearch);
    Page<Member> getAllMember(Pageable pageable);
}
