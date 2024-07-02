package DiceGame.S05T02Michel.model.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_player")
    private int playerId;

    @Column(name = "player_name", length = 45, nullable = false)
    private String playerName;

    @Column(name = "registration_date", nullable = false, updatable = false, insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public Player() {

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

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "ID: " + this.playerId + " - Player name: " + this.playerName +
                " - Registration date: " + this.registrationDate + ".";
    }
}