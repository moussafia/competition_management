package ma.youcode.cmspringboot.service.serviceImpl;

import ma.youcode.cmspringboot.model.domain.Member;
import ma.youcode.cmspringboot.repository.MemberRepository;
import ma.youcode.cmspringboot.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        ValidateIfExistForCreate(member);
        member.setAccessionDate(LocalDate.now());
        return memberRepository.save(member);
    }
    @Override
    public Member updateMember(Member member) {
        ValidateIfExistForUpdate(member);
        return memberRepository.save(member);
    }

    @Override
    public Member getMemberByNum(Member member) {
        return memberRepository.findByNum(member.getNum())
                .orElseThrow(() -> new IllegalStateException("Member with number " + member.getNum() + " not found"));
    }

    @Override
    public Member searchMember(String keySearch) {
        if(keySearch.matches("\\d+"))
            return memberRepository.findByNumOrNameOrFamilyName(Integer.valueOf(keySearch), "", "")
                    .orElseThrow(() -> new IllegalStateException("Member with number " + keySearch + " not found"));
        else
            return memberRepository.findByNumOrNameOrFamilyName(null, keySearch, keySearch)
                    .orElseThrow(() -> new IllegalStateException("Member with name or family name " + keySearch + " not found"));
    }

    @Override
    public Page<Member> getAllMember(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    void ValidateIfExistForCreate(Member member){
        memberRepository.findByIdentityNumber(member.getIdentityNumber())
                .ifPresent(m->{
                    throw new IllegalStateException(m.getIdentityNumber() + " is Already exist");
                });
    }
    void ValidateIfExistForUpdate(Member member){
        Member memberExisted = memberRepository.findByNum(member.getNum()).orElse(null);
        if (memberExisted == null)
            throw new IllegalStateException("member with number adherent " + member.getNum() + " not exist");
    }

}
