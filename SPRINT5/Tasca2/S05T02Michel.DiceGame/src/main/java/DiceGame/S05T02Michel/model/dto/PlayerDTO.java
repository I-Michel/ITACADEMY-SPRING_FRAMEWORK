package DiceGame.S05T02Michel.model.dto;

import java.util.Date;
import java.util.List;

public class PlayerDTO {

    private int playerId;
    private String playerName;
    private Date registrationDate;
    private List<GameDTO> games;
    private float winRate;

    public PlayerDTO(String playerName, List<GameDTO> games) {
        this.playerName = playerName;
        this.games = games;
    }

    public PlayerDTO() {

    }

    public int getPlayerId() {
        return playerId;
    }
    public String getPlayerName() {
        return playerName;
    }
    public Date getRegistrationDate() {
        return registrationDate;
    }
    public List<GameDTO> getGames() {
        return games;
    }
    public float getWinRate() {
        return winRate;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
    public void setGames(List<GameDTO> games) {
        this.games = games;
    }
    public void setWinRate(float winRate) {
        this.winRate = winRate;
    }

    @Override
    public String toString() {
        return "ID: " + this.playerId + " - Player name: " + this.playerName +
                " - Registration date: " + this.registrationDate +
                " - Games: " + this.games.size() + " - Win rate: " +
                this.winRate + ".";
    }
}