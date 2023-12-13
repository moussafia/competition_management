package ma.youcode.cmspringboot.service.serviceImpl;

import ma.youcode.cmspringboot.model.domain.Competition;
import ma.youcode.cmspringboot.repository.CompetitionRepository;
import ma.youcode.cmspringboot.service.CompetitionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDate;

@Service
@Transactional
public class CompetitionServiceImpl implements CompetitionService {
    private CompetitionRepository competitionRepository;

    public CompetitionServiceImpl(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    @Override
    public Competition createCompetition(Competition competition) {
        validateDateCompetition(competition);
        validateDateAlreadyExistForCreate(competition);
        competition.setCode(generateCode(competition));
        return this.competitionRepository.save(competition);
    }

    @Override
    public Competition getCompetitionByCode(Competition competition) {
        return competitionRepository.findByCode(competition.getCode())
                .orElseThrow(() -> new IllegalStateException("Competition with code " + competition.getCode() + " not found"));

    }

    @Override
    public Competition updateCompetition(Competition competition) {
        validateIfExistForUpdate(competition);
        validateDateCompetition(competition);
        Competition competitionExisted = validateDateAlreadyExistForUpdate(competition);
        Competition competitionForUpdate = competitionRepository.save(setCompetitionForUpdate(competitionExisted, competition));
        competitionForUpdate.setCode(generateCode(competition));
        return competitionForUpdate;
    }

    @Override
    public Page<Competition> getAllCompetition(Pageable pageable) {
        return competitionRepository.findAll(pageable);
    }

    String generateCode(Competition competition){
        StringBuilder code = new StringBuilder();
        String threeLetter = competition.getLocation().substring(0,3);
        return code.append(threeLetter).append("-").append(competition.getDate()).toString();
    }

    void validateDateCompetition(Competition competition){
        if(competition.getDate().isBefore(LocalDate.now()))
            throw new IllegalStateException("Competition start date should be at least 24 hours from now");
        if(competition.getDate().isBefore(LocalDate.now().plusDays(1L)))
            throw new IllegalStateException("Competition start date should be at least 24 hours from date created");
        if(competition.getEndTime().isBefore(competition.getStartDate()))
            throw new IllegalStateException("Competition end time should be after from Competition start time");
        if(competition.getEndTime().equals(competition.getStartDate()))
            throw new IllegalStateException("Competition end time should be after from Competition start time not equal");
        if(Duration.between(competition.getEndTime(), competition.getStartDate().plusHours(24)).toHours() > 24 )
            throw new IllegalStateException("end competition should not be more than 24 hour of its start date");

    }
    void validateDateAlreadyExistForCreate(Competition competition){
        competitionRepository.findByDate(competition.getDate()).ifPresent( date -> {
            throw new IllegalStateException("this "+ date.getDate() + " is already exist, you cannot have same competition in same day");
        });
    }
    Competition validateDateAlreadyExistForUpdate(Competition competition){
        return competitionRepository.findByDateAndCodeLike(competition.getDate(), competition.getCode())
                .orElseThrow(() -> new IllegalStateException("this "+ competition.getDate()
                        + " is already exist, you cannot have same competition in same day"));
    }
    void validateIfExistForUpdate(Competition competition){
        Competition competitionExisted = competitionRepository.findByCode(competition.getCode()).orElse(null);
        if(competitionExisted == null)
            throw new IllegalStateException("competition with code '" + competition.getCode() + "' is not exist");
    }
    Competition setCompetitionForUpdate(Competition competitionExisted, Competition competition) {
        Competition competitionNew = new Competition().builder()
                .code(competitionExisted.getCode())
                .amount(competition.getAmount())
                .date(competition.getDate())
                .startDate(competition.getStartDate())
                .endTime(competition.getEndTime())
                .location(competition.getLocation())
                .numberOfParticipants(competition.getNumberOfParticipants())
                .build();
        return competitionNew;
    }

}
