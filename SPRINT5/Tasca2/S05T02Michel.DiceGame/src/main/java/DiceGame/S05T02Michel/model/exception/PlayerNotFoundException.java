package DiceGame.S05T02Michel.model.exception;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(int id) {
        super("Player not found with id: " + id);
    }
}
