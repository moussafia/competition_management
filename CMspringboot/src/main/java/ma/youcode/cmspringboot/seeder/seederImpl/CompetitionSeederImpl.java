package ma.youcode.cmspringboot.seeder.seederImpl;

import ma.youcode.cmspringboot.model.domain.Competition;
import ma.youcode.cmspringboot.repository.CompetitionRepository;
import ma.youcode.cmspringboot.seeder.CompetitionSeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
@Component
public class CompetitionSeederImpl implements CompetitionSeeder {
    private CompetitionRepository competitionRepository;

    @Autowired
    public CompetitionSeederImpl(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    @Override
    public void createListCompetition() {
        List<Competition> competitionList = new ArrayList<>();
        LocalDate date = LocalDate.now();
        competitionList.add(new Competition().builder().amount(300f).code("ims-12-12-23").location("casablanca").date(date).startDate(LocalDateTime.of(2023, 12, 12, 8, 0)).endTime(LocalDateTime.of(2023, 12, 12, 20, 0)).build());
        competitionList.add(new Competition().builder().amount((float) (Math.random()*300)).code("ims-13-12-23").location("rabat").date(date).startDate(LocalDateTime.of(2023, 12, 13, 8, 0)).endTime(LocalDateTime.of(2023, 12, 13, 20, 0)).build());
        competitionList.add(new Competition().builder().amount((float) (Math.random()*300)).code("ims-14-12-23").location("tanger").date(date).startDate(LocalDateTime.of(2023, 12, 14, 8, 0)).endTime(LocalDateTime.of(2023, 12, 14, 20, 0)).build());
        competitionList.add(new Competition().builder().amount((float) (Math.random()*300)).code("ims-15-12-23").location("Al Hoceïma").date(date).startDate(LocalDateTime.of(2023, 12, 15, 8, 0)).endTime(LocalDateTime.of(2023, 12, 15, 20, 0)).build());
        competitionList.add(new Competition().builder().amount((float) (Math.random()*300)).code("ims-16-12-23").location("agadir").date(date).startDate(LocalDateTime.of(2023, 12, 16, 8, 0)).endTime(LocalDateTime.of(2023, 12, 16, 20, 0)).build());
        competitionList.add(new Competition().builder().amount((float) (Math.random()*300)).code("ims-17-12-23").location("sala").date(date).startDate(LocalDateTime.of(2023, 12, 17, 8, 0)).endTime(LocalDateTime.of(2023, 12, 17, 20, 0)).build());
        competitionList.add(new Competition().builder().amount((float) (Math.random()*300)).code("ims-18-12-23").location("safi").date(date).startDate(LocalDateTime.of(2023, 12, 18, 8, 0)).endTime(LocalDateTime.of(2023, 12, 18, 20, 0)).build());
        competitionList.add(new Competition().builder().amount((float) (Math.random()*300)).code("ims-19-12-23").location("Dakhla").date(date).startDate(LocalDateTime.of(2023, 12, 19, 8, 0)).endTime(LocalDateTime.of(2023, 12, 19, 20, 0)).build());
        competitionList.add(new Competition().builder().amount((float) (Math.random()*300)).code("ims-20-12-23").location("casablanca").date(date).startDate(LocalDateTime.of(2023, 12, 20, 8, 0)).endTime(LocalDateTime.of(2023, 12, 20, 20, 0)).build());

        competitionList.forEach(c -> competitionRepository.save(c));

    }
}
