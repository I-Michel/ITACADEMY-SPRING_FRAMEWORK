package S05T2Michel.DiceGame.model.exception;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(String message) {
        super(message);
    }
}
