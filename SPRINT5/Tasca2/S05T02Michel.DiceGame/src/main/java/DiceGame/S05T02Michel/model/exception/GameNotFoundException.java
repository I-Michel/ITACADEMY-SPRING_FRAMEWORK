package DiceGame.S05T02Michel.model.exception;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(int id) {
        super("Game not found with id: " + id);
    }
}
