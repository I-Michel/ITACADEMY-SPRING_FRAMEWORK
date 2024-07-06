package S05T2Michel.DiceGame.model.exception;

public class PlayerAlreadyExistsException extends RuntimeException {
    public PlayerAlreadyExistsException(String name) {
        super("Player already exists with name: " + name);
    }
}
