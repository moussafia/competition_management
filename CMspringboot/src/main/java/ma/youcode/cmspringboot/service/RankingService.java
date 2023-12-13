package ma.youcode.cmspringboot.service;

import ma.youcode.cmspringboot.model.domain.Competition;
import ma.youcode.cmspringboot.model.domain.Member;
import ma.youcode.cmspringboot.model.domain.Ranking;

import java.util.List;

public interface RankingService {
    Ranking InscribeMemberToCompetition(Ranking ranking);
    void removeMemberForCompetition(Ranking ranking);
    Ranking createScoreForCompetition(Member member, Competition competition);
    Ranking findRankingByCompetitionAndMember(Competition competition, Member member);
    List<Ranking> createRankingForCompetition(String competitionCode);
}
