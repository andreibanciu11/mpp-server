//package com.example.footballnewsserver.configuration;
//
//import com.example.footballnewsserver.model.Team;
//import com.example.footballnewsserver.repository.TeamRepository;
//import org.fluttercode.datafactory.impl.DataFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//public class LoadDatabase {
//
//    private final TeamRepository teamRepository;
//
//    public LoadDatabase(TeamRepository teamRepository) {
//        this.teamRepository = teamRepository;
//    }
//
//    @Bean
//    public CommandLineRunner initDatabase() {
//        return args -> {
//            String[] footballClubNames = {
//                    "FC Barcelona", "Real Madrid CF", "Manchester United FC", "Liverpool FC", "Juventus FC",
//                    "FC Bayern Munich", "Paris Saint-Germain FC", "Chelsea FC", "Arsenal FC", "Manchester City FC",
//                    "AC Milan", "Inter Milan", "AS Roma", "Napoli FC", "Borussia Dortmund",
//                    "Atletico Madrid", "Tottenham Hotspur FC", "Leicester City FC", "West Ham United FC", "Everton FC",
//                    "Ajax Amsterdam", "PSV Eindhoven", "FC Porto", "SL Benfica", "Sporting CP",
//                    "Olympique Lyonnais", "Olympique de Marseille", "AS Monaco FC", "Lille OSC", "FC Girondins de Bordeaux",
//                    "Celtic FC", "Rangers FC", "FC Basel", "Young Boys", "AEK Athens FC",
//                    "Dynamo Kyiv", "Shakhtar Donetsk", "Fenerbahçe SK", "Galatasaray SK", "Besiktas JK",
//                    "Red Star Belgrade", "Partizan Belgrade", "CSKA Moscow", "Spartak Moscow", "Zenit St. Petersburg",
//                    "Boca Juniors", "River Plate", "Flamengo", "São Paulo FC", "Santos FC"
//            };
//            DataFactory df = new DataFactory();
//            List<Team> teams = new ArrayList<>();
//            for (int i = 0; i < 20; i++) {
//                int randomIndex = df.getNumberBetween(0, footballClubNames.length);
//                String name = footballClubNames[randomIndex];
//                int foundationYear = df.getNumberBetween(1850, 2020); // Example: Random year between 1850 and 2020
//                String stadium = name + " Stadium"; // Construct a stadium name based on the team name
//                teams.add(new Team(name, foundationYear, stadium));
//            }
//            teamRepository.saveAll(teams);
//            System.out.println("Database initialized with random football clubs");
//        };
//    }
//
//    public void addTeam(Team team) {
//        teamRepository.save(team);
//        System.out.println("Added new team to the database: " + team.getName());
//    }
//}