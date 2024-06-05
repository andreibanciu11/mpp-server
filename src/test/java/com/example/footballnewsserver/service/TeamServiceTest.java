package com.example.footballnewsserver.service;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.footballnewsserver.model.Team;
import com.example.footballnewsserver.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamService teamService;

    @Test
    void testGetTeams() {
        //Test case 1

        Team team = new Team("FC Barcelona", 1899, "Camp Nou");
        Page<Team> page = new PageImpl<>(Collections.singletonList(team));
        when(teamRepository.findAll(any(PageRequest.class))).thenReturn(page);

        Page<Team> result = teamService.getTeams(PageRequest.of(0, 1));

        assertFalse(result.isEmpty());
        assertEquals(1, result.getTotalElements());
        verify(teamRepository).findAll(any(PageRequest.class));

        //Test case 2

        page = new PageImpl<>(List.of(
                new Team("FC Barcelona", 1899, "Camp Nou"),
                new Team("Real Madrid CF", 1902, "Santiago Bernabéu"),
                new Team("Manchester United FC", 1878, "Old Trafford"),
                new Team("Liverpool FC", 1892, "Anfield"),
                new Team("Juventus FC", 1897, "Allianz Stadium"),
                new Team("FC Bayern Munich", 1900, "Allianz Arena"),
                new Team("Paris Saint-Germain FC", 1970, "Parc des Princes"),
                new Team("Chelsea FC", 1905, "Stamford Bridge"),
                new Team("Arsenal FC", 1886, "Emirates Stadium"),
                new Team("Manchester City FC", 1880, "Etihad Stadium")
        ));
        when(teamRepository.findAll(any(PageRequest.class))).thenReturn(page);
        result = teamService.getTeams(PageRequest.of(0, 10));
        assertFalse(result.isEmpty());
        assertEquals(10, result.getTotalElements());
        verify(teamRepository, times(2)).findAll(any(PageRequest.class));
    }

    @Test
    void testAddTeam() {
        //Test case 1

        Team team1 = new Team("FC Barcelona", 1899, "Camp Nou");
        Page<Team> page = new PageImpl<>(Collections.singletonList(team1));
        when(teamRepository.save(any(Team.class))).thenReturn(team1);
        when(teamRepository.findAll(any(PageRequest.class))).thenReturn(page);

        Team savedTeam1 = teamService.addTeam(team1);
        Page<Team> result = teamService.getTeams(PageRequest.of(0, 10));

        assertNotNull(savedTeam1);
        assertEquals("FC Barcelona", savedTeam1.getName());
        assertEquals(result.getTotalElements(), 1);
        verify(teamRepository).save(any(Team.class));

        //Test case 2

        Team team2 = new Team("FC Barcelona B", 1899, "Camp Nou");
        page = new PageImpl<>(List.of(new Team("FC Barcelona", 1899, "Camp Nou"),
                new Team("FC Barcelona B", 1899, "Camp Nou")));
        when(teamRepository.save(any(Team.class))).thenReturn(team2);
        when(teamRepository.findAll(any(PageRequest.class))).thenReturn(page);

        Team savedTeam2 = teamService.addTeam(team2);
        result = teamService.getTeams(PageRequest.of(0, 10));
        assertNotNull(savedTeam2);
        assertEquals("FC Barcelona B", savedTeam2.getName());
        assertEquals(result.getTotalElements(), 2);
        verify(teamRepository, times(2)).save(any(Team.class));
    }

    @Test
    void testFindTeamByIdFound() throws Exception {
        //Test case 1

        Team team = new Team("FC Barcelona", 1899, "Camp Nou");
        when(teamRepository.findTeamById(1L)).thenReturn(Optional.of(team));

        Team foundTeam = teamService.findTeamById(1L);

        assertNotNull(foundTeam);
        assertEquals("FC Barcelona", foundTeam.getName());
        verify(teamRepository).findTeamById(1L);

        //Test case 2
        Team team2 = new Team("FC Barcelona B", 1899, "Camp Nou");
        when(teamRepository.findTeamById(2L)).thenReturn(Optional.of(team2));

        Team foundTeam2 = teamService.findTeamById(2L);

        assertNotNull(foundTeam2);
        assertEquals("FC Barcelona B", foundTeam2.getName());
        verify(teamRepository).findTeamById(2L);
    }

    @Test
    void testFindTeamByIdNotFound() {
        when(teamRepository.findTeamById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> {
            teamService.findTeamById(1L);
        });

        assertEquals("Team with with id 1 not found!", exception.getMessage());
        verify(teamRepository).findTeamById(anyLong());
    }

    @Test
    void testUpdateTeam() throws Exception {
        Team existingTeam = new Team("FC Barcelona", 1899, "Camp Nou");
        Team updatedDetails = new Team("FC Barcelona Updated", 1900, "Camp Nou Updated");
        when(teamRepository.findById(1L)).thenReturn(Optional.of(existingTeam));
        when(teamRepository.save(any(Team.class))).thenReturn(updatedDetails);

        Team updatedTeam = teamService.updateTeam(1L, updatedDetails);

        assertNotNull(updatedTeam);
        assertEquals("FC Barcelona Updated", updatedTeam.getName());
        verify(teamRepository).save(any(Team.class));
    }

    @Test
    void testDeleteTeam() throws Exception {
        when(teamRepository.existsById(1L)).thenReturn(true);
        doNothing().when(teamRepository).deleteById(1L);

        teamService.deleteTeam(1L);

        verify(teamRepository).deleteById(1L);
    }
}
