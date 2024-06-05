package com.example.footballnewsserver.service;

import com.example.footballnewsserver.model.Team;
import com.example.footballnewsserver.repository.TeamRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    private TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Page<Team> getTeams(Pageable pageable) {
        return this.teamRepository.findAll(pageable);
    }
    public Team addTeam(Team newTeam) { return this.teamRepository.save(newTeam); }
    public Team findTeamById(Long id) throws Exception {
        return this.teamRepository.findTeamById(id).orElseThrow(() -> new Exception("Team with with id " + id + " not found!"));
    }
    public Team updateTeam(Long id, Team teamDetails) throws Exception {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new Exception("Team with with id " + id + " not found!"));

        team.setName(teamDetails.getName());
        team.setFoundedYear(teamDetails.getFoundedYear());
        team.setStadium(teamDetails.getStadium());
        return teamRepository.save(team);
    }

    public void deleteTeam(Long id) throws Exception {
        if (!teamRepository.existsById(id)) {
            throw new Exception("Team with with id " + id + " not found!");
        }
        teamRepository.deleteById(id);
    }
}
