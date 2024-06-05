package com.example.footballnewsserver.service;

import com.example.footballnewsserver.dto.PlayerDTO;
import com.example.footballnewsserver.model.Player;
import com.example.footballnewsserver.model.Team;
import com.example.footballnewsserver.repository.PlayerRepository;
import com.example.footballnewsserver.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private PlayerService playerService;

    @Test
    void testAddPlayer() {
        // Test data
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setName("PlayerName");
        playerDTO.setBirthDate(LocalDate.of(1990, 1, 1));
        playerDTO.setNationality("Nationality");
        playerDTO.setPosition("Position");
        playerDTO.setTeamId(1L);

        Team team = new Team();
        team.setId(1L);

        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));
        when(playerRepository.save(any(Player.class))).thenAnswer(invocation -> {
            Player player = invocation.getArgument(0);
            player.setId(1L);
            return player;
        });

        Player savedPlayer = playerService.addPlayer(playerDTO);

        assertNotNull(savedPlayer);
        assertEquals("PlayerName", savedPlayer.getName());
        assertEquals(LocalDate.of(1990, 1, 1), savedPlayer.getBirthDate());
        assertEquals("Nationality", savedPlayer.getNationality());
        assertEquals("Position", savedPlayer.getPosition());
        assertEquals(1L, savedPlayer.getTeam().getId()); // Assuming team ID is set correctly
        verify(playerRepository).save(any(Player.class));
    }

    @Test
    void testFindPlayerByIdFound() throws Exception {
        // Test data
        Player player = new Player();
        player.setId(1L);
        player.setName("PlayerName");

        when(playerRepository.findById(1L)).thenReturn(Optional.of(player));

        Player foundPlayer = playerService.findPlayerById(1L);

        assertNotNull(foundPlayer);
        assertEquals("PlayerName", foundPlayer.getName());
        verify(playerRepository).findById(1L);
    }

    @Test
    void testFindPlayerByIdNotFound() {
        when(playerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> {
            playerService.findPlayerById(1L);
        });

        assertEquals("Player with with id 1 not found!", exception.getMessage());
        verify(playerRepository).findById(anyLong());
    }

    @Test
    void testUpdatePlayer() throws Exception {
        // Test data
        Player existingPlayer = new Player();
        existingPlayer.setId(1L);
        existingPlayer.setName("PlayerName");

        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setName("UpdatedPlayerName");
        playerDTO.setBirthDate(LocalDate.of(1990, 1, 1));
        playerDTO.setNationality("Nationality");
        playerDTO.setPosition("Position");
        playerDTO.setTeamId(1L);

        Team team = new Team();
        team.setId(1L);

        when(playerRepository.findById(1L)).thenReturn(Optional.of(existingPlayer));
        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));
        when(playerRepository.save(any(Player.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Player updatedPlayer = playerService.updatePlayer(1L, playerDTO);

        assertNotNull(updatedPlayer);
        assertEquals("UpdatedPlayerName", updatedPlayer.getName());
        assertEquals(LocalDate.of(1990, 1, 1), updatedPlayer.getBirthDate());
        assertEquals("Nationality", updatedPlayer.getNationality());
        assertEquals("Position", updatedPlayer.getPosition());
        assertEquals(1L, updatedPlayer.getTeam().getId()); // Assuming team ID is set correctly
        verify(playerRepository).findById(1L);
        verify(teamRepository).findById(1L);
        verify(playerRepository).save(any(Player.class));
    }

    @Test
    void testDeletePlayer() throws Exception {
        when(playerRepository.existsById(1L)).thenReturn(true);
        doNothing().when(playerRepository).deleteById(1L);

        playerService.deletePlayer(1L);

        verify(playerRepository).deleteById(1L);
    }
}
