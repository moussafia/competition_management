package ma.youcode.cmspringboot.service.serviceImpl;

import ma.youcode.cmspringboot.model.domain.Competition;
import ma.youcode.cmspringboot.repository.CompetitionRepository;
import ma.youcode.cmspringboot.service.CompetitionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class CompetitionServiceImpl implements CompetitionService {
    private CompetitionRepository competitionRepository;

    public CompetitionServiceImpl(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    @Override
    public Competition createCompetition(Competition competition) {
        competition.setDate(LocalDate.now());
        competition.setNumberOfParticipants(null);
        validateDateCompetitionForCreate(competition);
        return this.competitionRepository.save(competition);
    }

    @Override
    public Page<Competition> getAllCompetition(Pageable pageable) {
        return competitionRepository.findAll(pageable);
    }


    void validateDateCompetitionForCreate(Competition competition){
        if(competition.getStartDate().isBefore(LocalDateTime.now()))
            throw new IllegalStateException("Competition start date should be at least 24 hours from now");
        if(competition.getStartDate().isBefore(LocalDateTime.now().plusHours(24)))
            throw new IllegalStateException("Competition start date should be at least 24 hours from now");
        if(competition.getEndTime().isBefore(competition.getStartDate()))
            throw new IllegalStateException("Competition end time should be after from Competition start time");
        if(competition.getEndTime().isEqual(competition.getStartDate()))
            throw new IllegalStateException("Competition end time should be after from Competition start time not equal");
        if(competition.getEndTime().isAfter(competition.getStartDate().plusHours(24)))
            throw new IllegalStateException("end competition should not be more than 24 hour of its start date");
    }
}
