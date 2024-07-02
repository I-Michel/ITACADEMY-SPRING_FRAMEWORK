package DiceGame.S05T02Michel.model.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_game")
    private int gameId;

    @NotEmpty
    @Column(name = "dice_roll1", nullable = false)
    private int diceRoll1;

    @NotEmpty
    @Column(name = "dice_roll2", nullable = false)
    private int diceRoll2;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    public Game(int diceRoll1, int diceRoll2, Player player) {
        this.diceRoll1 = diceRoll1;
        this.diceRoll2 = diceRoll2;
        this.player = player;
    }

    public Game() {

    }

    public int getGameId() {
        return gameId;
    }
    public int getDiceRoll1() {
        return diceRoll1;
    }
    public int getDiceRoll2() {
        return diceRoll2;
    }
    public Player getPlayer() {
        return player;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
    public void setDiceRoll1(int diceRoll1) {
        this.diceRoll1 = diceRoll1;
    }
    public void setDiceRoll2(int diceRoll2) {
        this.diceRoll2 = diceRoll2;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "ID: " + this.gameId + " - Player: " + this.player.getPlayerName() +
                " - 1st Dice Roll: " + this.diceRoll1 + " - 2nd Dice Roll: " + this.diceRoll2 +  ".";
    }
}
