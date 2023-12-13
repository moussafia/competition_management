package ma.youcode.cmspringboot.service.serviceImpl;

import ma.youcode.cmspringboot.model.domain.Competition;
import ma.youcode.cmspringboot.model.domain.Member;
import ma.youcode.cmspringboot.model.domain.Ranking;
import ma.youcode.cmspringboot.repository.CompetitionRepository;
import ma.youcode.cmspringboot.repository.RankingRepository;
import ma.youcode.cmspringboot.service.CompetitionService;
import ma.youcode.cmspringboot.service.MemberService;
import ma.youcode.cmspringboot.service.RankingService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class RankingServiceImpl implements RankingService {
    private RankingRepository rankingRepository;
    private MemberService memberService;
    private CompetitionService competitionService;
    private final CompetitionRepository competitionRepository;

    public RankingServiceImpl(RankingRepository rankingRepository, MemberService memberService, CompetitionService competitionService,
                              CompetitionRepository competitionRepository) {
        this.rankingRepository = rankingRepository;
        this.memberService = memberService;
        this.competitionService = competitionService;
        this.competitionRepository = competitionRepository;
    }

    @Override
    public Ranking InscribeMemberToCompetition(Ranking ranking) {
        Member member = validateMemberIfExist(ranking);
        Competition competition = validateCompetitionIfExist(ranking);
        validateIfMemberAlreadyInscribeInCompetition(member, competition);
        validateAvailabilityDateForInscribe(competition);
        ranking.setMember(member); ranking.setCompetition(competition);
        return rankingRepository.save(ranking);
    }

    @Override
    public void removeMemberForCompetition(Ranking ranking) {
        Member member = validateMemberIfExist(ranking);
        Competition competition = validateCompetitionIfExist(ranking);
        Ranking rankingForSaving = findRankingByCompetitionAndMember(competition, member);
        rankingRepository.delete(rankingForSaving);
    }

    @Override
    public Ranking createScoreForCompetition(Member member, Competition competition) {
        Ranking ranking = rankingRepository.findByCompetitionAndMember(competition, member)
                .orElseThrow(() -> new  IllegalStateException("member with name " + member.getName() + " is not inscribe " +
                        "in this competition " + competition.getCode()));
        return rankingRepository.save(ranking);
    }

//    @Transactional
//    @Override
//    public Ranking createScoreForCompetition(String competitionCode) {
//        Competition competition = competitionService.getCompetitionByCode(competitionCode);
//        Set<Hunting> huntingList = competition.getHunting();
//        huntingList.forEach(hl -> {
//            Ranking ranking = rankingRepository.findByCompetitionAndMember(competition , hl.getMember())
//                    .orElseThrow(() -> new IllegalStateException());
//            Integer score = hl.getFish().getLevel().getPoints()*hl.getNumberOfFish();
//            ranking.setRanke(ranking.getRanke() + score);
//            rankingRepository.save(ranking);
//        });
//
//
//
//        return null;
//    }

    @Override
    public Ranking findRankingByCompetitionAndMember(Competition competition, Member member) {
        return  rankingRepository.findByCompetitionAndMember(competition, member)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Ranking not found for Member '" + member.getName() +
                                "' and Competition '" + competition.getCode() + "'"));
    }

    @Override
    public List<Ranking> createRankingForCompetition(String competitionCode) {
        Competition competition = competitionService.getCompetitionByCode(competitionCode);
        Set<Ranking> rankings = competition.getRanking();
        final Integer[] index = {0};
        return rankingRepository.saveAll(rankings.stream()
                .sorted(Comparator.comparing(Ranking::getScore))
                .map( hunting -> {
                    index[0]++;
                    hunting.setRank(index[0]);
                    return hunting;
                }).collect(Collectors.toList()));
    }

    private Member validateMemberIfExist(Ranking ranking) {

        return memberService.getMemberByNum(ranking.getMember());
    }

    private Competition validateCompetitionIfExist(Ranking ranking) {
        return competitionService.getCompetitionByCode(ranking.getCompetition().getCode());
    }


    void validateIfMemberAlreadyInscribeInCompetition(Member member, Competition competition){
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
