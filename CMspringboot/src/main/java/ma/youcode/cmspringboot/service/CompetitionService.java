package ma.youcode.cmspringboot.service;

import ma.youcode.cmspringboot.model.domain.Competition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompetitionService {
    public Competition createCompetition(Competition competition);
    public Competition getCompetitionByCode(Competition competition);
    public Competition updateCompetition(Competition competition);
    public Page<Competition> getAllCompetition(Pageable pageable);
}
