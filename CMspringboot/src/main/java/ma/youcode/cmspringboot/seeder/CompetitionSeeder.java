package ma.youcode.cmspringboot.seeder;

import ma.youcode.cmspringboot.model.domain.Competition;

import java.util.List;

public interface CompetitionSeeder {
    public Competition createOne();
    public List<Competition> createListCompetition();
}
