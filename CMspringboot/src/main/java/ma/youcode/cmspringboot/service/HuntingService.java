package ma.youcode.cmspringboot.service;

import ma.youcode.cmspringboot.model.domain.Competition;
import ma.youcode.cmspringboot.model.domain.Hunting;

import java.util.List;
import java.util.Optional;

public interface HuntingService {
    Hunting insertHuntingForMemberInCompetition(Hunting hunting);
    Hunting updateHuntingForMemberInCompetition(Hunting hunting);
    Hunting processHuntingForFish(Hunting hunting);
    public Hunting processUpdateHuntingForFish(Hunting hunting);
    public void deleteMemberHunting(Hunting hunting);
    Hunting isHuntingExist(Hunting hunting);
    Optional<Hunting> findByMemberAndFishAndCompetition(Hunting hunting);
    List<Hunting> getAllHuntingOfCompetition(Competition competition);

}
