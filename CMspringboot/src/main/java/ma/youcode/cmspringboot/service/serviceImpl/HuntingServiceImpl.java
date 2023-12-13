package ma.youcode.cmspringboot.service.serviceImpl;

import ma.youcode.cmspringboot.model.domain.Competition;
import ma.youcode.cmspringboot.model.domain.Fish;
import ma.youcode.cmspringboot.model.domain.Hunting;
import ma.youcode.cmspringboot.model.domain.Member;
import ma.youcode.cmspringboot.repository.HuntingRepository;
import ma.youcode.cmspringboot.service.CompetitionService;
import ma.youcode.cmspringboot.service.FishService;
import ma.youcode.cmspringboot.service.HuntingService;
import ma.youcode.cmspringboot.service.MemberService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

public class HuntingServiceImpl implements HuntingService {
    private HuntingRepository huntingRepository;
    private FishService fishService;
    private CompetitionService competitionService;
    private MemberService memberService;

    public HuntingServiceImpl(HuntingRepository huntingRepository,
                              FishService fishService,
                              CompetitionService competitionService,
                              MemberService memberService) {
        this.huntingRepository = huntingRepository;
        this.fishService = fishService;
        this.competitionService = competitionService;
        this.memberService = memberService;
    }

    @Override
    public Hunting insertHuntingForMemberInCompetition(Hunting hunting) {
        processCheckWeightOfFish(hunting);
        processCheckWeightOfFish(hunting);
        Hunting huntingForSaving = processHuntingForFish(hunting);
        return huntingRepository.save(huntingForSaving);
    }



    @Override
    public Hunting updateHuntingForMemberInCompetition(Hunting hunting) {
        Hunting huntingForUpdate = processUpdateHuntingForFish(hunting);
        return huntingRepository.save(huntingForUpdate);
    }

    private void processCheckWeightOfFish(Hunting hunting) {
        Fish fish = fishService.getFishById(hunting.getFish());
        if(fish.getAverageWeight() < hunting.getFish().getAverageWeight())
            throw new IllegalStateException("Average weight " + hunting.getFish().getAverageWeight() + " for the fish named '"
                    + hunting.getFish().getName() + "'  should be equals or superior than "
            + fish.getAverageWeight());
    }
    @Override
    public Hunting processHuntingForFish(Hunting hunting) {
        Optional<Hunting> huntingSaved = findByMemberAndFishAndCompetition(hunting);
        Hunting huntingForSaving = new Hunting().builder()
                .id(huntingSaved.get().getId())
                .competition(huntingSaved.get().getCompetition())
                .member(huntingSaved.get().getMember())
                .fish(huntingSaved.get().getFish())
                .build();
        if (huntingSaved.isPresent())
            huntingForSaving.setNumberOfFish(huntingSaved.get().getNumberOfFish() + 1);
        else
            huntingForSaving.setNumberOfFish(1);
        return huntingForSaving;
    }
    @Override
    public Hunting processUpdateHuntingForFish(Hunting hunting){
        Optional<Hunting> huntingSaved = findByMemberAndFishAndCompetition(hunting);
        return new Hunting().builder()
                .id(huntingSaved.get().getId())
                .competition(huntingSaved.get().getCompetition())
                .member(huntingSaved.get().getMember())
                .fish(huntingSaved.get().getFish())
                .numberOfFish(hunting.getNumberOfFish())
                .build();
    }

    @Override
    public void deleteMemberHunting(Hunting hunting) {
        Hunting huntingForDelete = isHuntingExist(hunting);
        huntingRepository.delete(huntingForDelete);
    }
    @Override
    public Hunting isHuntingExist(Hunting hunting){
        return findByMemberAndFishAndCompetition(hunting)
                .orElseThrow(() -> new IllegalStateException("Hunting entry not found for Member: "
                        + hunting.getMember().getNum() + ", Competition: " + hunting.getCompetition().getCode()
                        + ", Fish ID: " + hunting.getFish().getId()));
    }
    @Override
    public Optional<Hunting> findByMemberAndFishAndCompetition(Hunting hunting){
        Member member = memberService.getMemberByNum(hunting.getMember());
        Competition competition = competitionService.getCompetitionByCode(hunting.getCompetition());
        Fish fish = fishService.getFishById(hunting.getFish());
        return huntingRepository.findByMemberAndCompetitionAndFish(member, competition, fish);
    }

    @Override
    public List<Hunting> getAllHuntingOfCompetition(Competition competition) {
        return huntingRepository.findByCompetition(competition)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No hunting entries found for competition with code: " + competition.getCode()));
    }

}
