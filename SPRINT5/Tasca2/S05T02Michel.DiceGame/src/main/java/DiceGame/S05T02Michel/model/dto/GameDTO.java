package DiceGame.S05T02Michel.model.dto;

import DiceGame.S05T02Michel.model.domain.Player;

public class GameDTO {

    private int gameId;
    private int diceRoll1;
    private int diceRoll2;
    private boolean win;
    private Player player;

    public GameDTO(int diceRoll1, int diceRoll2, Player player) {
        this.diceRoll1 = diceRoll1;
        this.diceRoll2 = diceRoll2;
        this.win = (this.diceRoll1 + this.diceRoll2 == 7);
        this.player = player;
    }

    public GameDTO() {

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
    public boolean getWin() { return win; }
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
    public void setWin(boolean win) { this.win = win; }
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "ID: " + this.gameId + " - Player: " + this.player.getPlayerName() +
                " - 1st Dice Roll: " + this.diceRoll1 + " - 2nd Dice Roll: " + this.diceRoll2 +
                " - Result: " + (this.diceRoll1 + this.diceRoll2) + " - Win: " + this.win + ".";
    }
}