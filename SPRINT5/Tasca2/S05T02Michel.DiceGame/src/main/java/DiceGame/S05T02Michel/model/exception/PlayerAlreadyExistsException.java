package DiceGame.S05T02Michel.model.exception;

public class PlayerAlreadyExistsException extends RuntimeException {
    public PlayerAlreadyExistsException(String name) {
        super("Player already exists with name: " + name);
    }
}
