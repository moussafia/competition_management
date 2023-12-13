package ma.youcode.cmspringboot.service.serviceImpl;

import ma.youcode.cmspringboot.model.domain.Competition;
import ma.youcode.cmspringboot.model.domain.Hunting;
import ma.youcode.cmspringboot.model.domain.Member;
import ma.youcode.cmspringboot.model.domain.Ranking;
import ma.youcode.cmspringboot.repository.RankingRepository;
import ma.youcode.cmspringboot.service.CompetitionService;
import ma.youcode.cmspringboot.service.HuntingService;
import ma.youcode.cmspringboot.service.MemberService;
import ma.youcode.cmspringboot.service.RankingService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RankingServiceImpl implements RankingService {
    private RankingRepository rankingRepository;
    private MemberService memberService;
    private CompetitionService competitionService;

    public RankingServiceImpl(RankingRepository rankingRepository, MemberService memberService, CompetitionService competitionService) {
        this.rankingRepository = rankingRepository;
        this.memberService = memberService;
        this.competitionService = competitionService;
    }

    @Override
    public Ranking InscribeMemberToCompetition(Ranking ranking) {
        Member member = validateMemberIfExist(ranking);
        Competition competition = validateCompetitionIfExist(ranking);
        validateIfMemberAlreadyInscribeInCompetition(ranking);
        validateAvailabilityDateForInscribe(competition);
        ranking.setMember(member); ranking.setCompetition(competition);
        return rankingRepository.save(ranking);
    }

    @Override
    public void removeMemberForCompetition(Ranking ranking) {
        Member member = validateMemberIfExist(ranking);
        Competition competition = validateCompetitionIfExist(ranking);
        Ranking rankingForSaving = rankingRepository.findByCompetitionAndMember(competition, member)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Ranking not found for Member '" + member.getName() +
                                "' and Competition '" + competition.getCode() + "'"));
        rankingRepository.delete(rankingForSaving);
    }

    @Override
    public Ranking createScoreForCompetition(Ranking ranking) {
        Competition competition = competitionService.getCompetitionByCode(ranking.getCompetition());
        Set<Hunting> huntingList = competition.getHunting();
        huntingList.forEach(h ->{
            
        });
        return null;
    }

    private Member validateMemberIfExist(Ranking ranking) {

        return memberService.getMemberByNum(ranking.getMember());
    }

    private Competition validateCompetitionIfExist(Ranking ranking) {
        return competitionService.getCompetitionByCode(ranking.getCompetition());
    }


    void validateIfMemberAlreadyInscribeInCompetition(Ranking ranking){
        Member member = memberService.getMemberByNum(ranking.getMember());
        Competition competition = competitionService.getCompetitionByCode(ranking.getCompetition());
        Optional<Ranking> rankingExisted = rankingRepository.findByCompetitionAndMember(competition, member);
        if(rankingExisted.isPresent())
            throw new IllegalStateException("member with name " + member.getName()
            + " already inscribe in this competition with name " + competition.getCode());

    }
    void validateAvailabilityDateForInscribe(Competition competition) {
        long hoursUntilCompetition = ChronoUnit.HOURS.between(LocalDate.now(), competition.getDate());
        if(hoursUntilCompetition <= 24){
            throw new IllegalStateException("Cannot inscribe for this competition with a date '" +
                    competition.getDate() + "' less than 24 hours from now. Remaining time: " + hoursUntilCompetition + " hours.");
        }
    }
}
