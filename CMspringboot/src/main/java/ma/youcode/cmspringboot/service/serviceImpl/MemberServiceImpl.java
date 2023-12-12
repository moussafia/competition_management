package ma.youcode.cmspringboot.service.serviceImpl;

import ma.youcode.cmspringboot.model.domain.Member;
import ma.youcode.cmspringboot.repository.MemberRepository;
import ma.youcode.cmspringboot.service.MemberService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MemberServiceImpl implements MemberService {
    private MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member createMember(Member member) {
        ValidateMemeberIfExistForCreate(member);
        member.setAccessionDate(LocalDate.now());
        return this.memberRepository.save(member);
    }
    @Override
    public Member searchMember(String keySearch) {
        if(keySearch.matches("\\d+"))
            return this.memberRepository.findByNumOrNameOrFamilyName(Integer.valueOf(keySearch), "", "");
        else
            return this.memberRepository.findByNumOrNameOrFamilyName(null, keySearch, keySearch);
    }
    void ValidateMemeberIfExistForCreate(Member member){
        memberRepository.findByIdentityNumber(member.getIdentityNumber())
                .ifPresent(m->{
                    throw new IllegalStateException(m.getIdentityNumber() + " is Already exist");
                });
    }

}
