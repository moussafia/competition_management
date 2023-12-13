package ma.youcode.cmspringboot.service;

import ma.youcode.cmspringboot.model.domain.Ranking;

public interface RankingService {
    Ranking InscribeMemberToCompetition(Ranking ranking);
    void removeMemberForCompetition(Ranking ranking);
    Ranking createScoreForCompetition(Ranking ranking);
}
