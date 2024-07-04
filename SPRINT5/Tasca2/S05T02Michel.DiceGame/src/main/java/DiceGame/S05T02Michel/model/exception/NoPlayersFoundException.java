package DiceGame.S05T02Michel.model.exception;

public class NoPlayersFoundException extends RuntimeException {
    public NoPlayersFoundException() {
        super("No players found");
    }
}
