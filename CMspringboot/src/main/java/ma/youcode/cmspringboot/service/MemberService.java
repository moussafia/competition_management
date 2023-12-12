package ma.youcode.cmspringboot.service;

import ma.youcode.cmspringboot.model.domain.Member;

public interface MemberService {
    Member createMember(Member member);
    Member searchMember(String keySearch);
}
