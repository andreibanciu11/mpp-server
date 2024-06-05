package com.example.footballnewsserver.service;

import com.example.footballnewsserver.dto.PlayerDTO;
import com.example.footballnewsserver.model.Player;
import com.example.footballnewsserver.model.Team;
import com.example.footballnewsserver.repository.PlayerRepository;
import com.example.footballnewsserver.repository.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    private PlayerRepository playerRepository;
    private TeamRepository teamRepository;

    public PlayerService(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    public List<Player> getPlayers() {
        return this.playerRepository.findAll();
    }
    public Player addPlayer(PlayerDTO playerDTO) {
        Player player = new Player();
        player.setName(playerDTO.getName());
        player.setBirthDate(playerDTO.getBirthDate());
        player.setNationality(playerDTO.getNationality());
        player.setPosition(playerDTO.getPosition());

        // Find the team based on ID and set it
        Team team = teamRepository.findById(playerDTO.getTeamId())
                .orElseThrow(() -> new RuntimeException("Team not found"));
        player.setTeam(team);

        return playerRepository.save(player);
    }
    public Player findPlayerById(Long id) throws Exception {
        return this.playerRepository.findById(id).orElseThrow(() -> new Exception("Player with with id " + id + " not found!"));
    }
    public Player updatePlayer(Long id, PlayerDTO playerDetails) throws Exception {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new Exception("Player with with id " + id + " not found!"));

        player.setName(playerDetails.getName());
        player.setBirthDate(playerDetails.getBirthDate());
        player.setNationality(playerDetails.getNationality());
        player.setPosition(playerDetails.getPosition());

        Team team = teamRepository.findById(playerDetails.getTeamId())
                .orElseThrow(() -> new RuntimeException("Team not found"));
        player.setTeam(team);

        return playerRepository.save(player);
    }

    @Transactional
    public void deletePlayer(Long id) throws Exception {
        if (!playerRepository.existsById(id)) {
            throw new Exception("Player with with id " + id + " not found!");
        }
        playerRepository.deleteById(id);
        playerRepository.flush();
        System.out.println(playerRepository.findAll().size());
    }
}
