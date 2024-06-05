package com.example.footballnewsserver.utils;

import com.example.footballnewsserver.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.fluttercode.datafactory.impl.DataFactory;

import java.util.Random;
import com.example.footballnewsserver.repository.TeamRepository;

@EnableScheduling
@Component
public class TeamGenerator {

    private final SimpMessagingTemplate template;
    private final TeamRepository teamRepository;
    private final Random random = new Random();

    private String[] footballClubNames = {
            "FC Barcelona", "Real Madrid CF", "Manchester United FC", "Liverpool FC", "Juventus FC",
            "FC Bayern Munich", "Paris Saint-Germain FC", "Chelsea FC", "Arsenal FC", "Manchester City FC",
            "AC Milan", "Inter Milan", "AS Roma", "Napoli FC", "Borussia Dortmund",
            "Atletico Madrid", "Tottenham Hotspur FC", "Leicester City FC", "West Ham United FC", "Everton FC",
            "Ajax Amsterdam", "PSV Eindhoven", "FC Porto", "SL Benfica", "Sporting CP",
            "Olympique Lyonnais", "Olympique de Marseille", "AS Monaco FC", "Lille OSC", "FC Girondins de Bordeaux",
            "Celtic FC", "Rangers FC", "FC Basel", "Young Boys", "AEK Athens FC",
            "Dynamo Kyiv", "Shakhtar Donetsk", "Fenerbahçe SK", "Galatasaray SK", "Besiktas JK",
            "Red Star Belgrade", "Partizan Belgrade", "CSKA Moscow", "Spartak Moscow", "Zenit St. Petersburg",
            "Boca Juniors", "River Plate", "Flamengo", "São Paulo FC", "Santos FC"
    };

    @Autowired
    public TeamGenerator(SimpMessagingTemplate template, TeamRepository teamRepository) {
        this.template = template;
        this.teamRepository = teamRepository;
    }

//    @Scheduled(fixedDelay = 5000)
//    public void generateAndBroadcastTeam() {
//        DataFactory df = new DataFactory();
//        int randomIndex = df.getNumberBetween(0, footballClubNames.length);
//        Team team = new Team(footballClubNames[randomIndex], df.getNumberBetween(1850, 2020), "Stadium " + footballClubNames[randomIndex] + " Stadium");
//        long repoLength = this.teamRepository.findAll().size();
//        team.setId(repoLength+1);
//        teamRepository.save(team);
//        template.convertAndSend("/topic/teams", team);
//    }
}
