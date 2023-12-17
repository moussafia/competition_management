package ma.youcode.cmspringboot.seeder.dbSeed;

import ma.youcode.cmspringboot.model.domain.Level;
import ma.youcode.cmspringboot.seeder.CompetitionSeeder;
import ma.youcode.cmspringboot.seeder.FishSeeder;
import ma.youcode.cmspringboot.seeder.LevelSeeder;
import ma.youcode.cmspringboot.seeder.MemberSeeder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbSeed {
    private LevelSeeder levelSeeder;
    private FishSeeder fishSeeder;
    private CompetitionSeeder competitionSeeder;
    private MemberSeeder memberSeeder;

    public DbSeed(LevelSeeder levelSeeder, FishSeeder fishSeeder,
                  CompetitionSeeder competitionSeeder, MemberSeeder memberSeeder) {
        this.levelSeeder = levelSeeder;
        this.fishSeeder = fishSeeder;
        this.competitionSeeder = competitionSeeder;
        this.memberSeeder = memberSeeder;
    }

    @Bean
    public CommandLineRunner start(){
        return args -> {
            List<Level> levelList = levelSeeder.saveLevel();
            fishSeeder.saveFish(levelList);
            competitionSeeder.createListCompetition();
            memberSeeder.createMemberSeeder();
        };
    }
}
