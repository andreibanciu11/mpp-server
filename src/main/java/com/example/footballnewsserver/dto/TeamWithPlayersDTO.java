package com.example.footballnewsserver.dto;

import com.example.footballnewsserver.model.Player;

import java.util.List;

public class TeamWithPlayersDTO {
    private Long id;
    private String name;
    private int foundedYear;
    private String stadium;
    private List<Player> players;

    public TeamWithPlayersDTO() {

    }

    public TeamWithPlayersDTO(Long id, String name, int foundedYear, String stadium, List<Player> players) {
        this.id = id;
        this.name = name;
        this.foundedYear = foundedYear;
        this.stadium = stadium;
        this.players = players;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFoundedYear() {
        return foundedYear;
    }

    public void setFoundedYear(int foundedYear) {
        this.foundedYear = foundedYear;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
