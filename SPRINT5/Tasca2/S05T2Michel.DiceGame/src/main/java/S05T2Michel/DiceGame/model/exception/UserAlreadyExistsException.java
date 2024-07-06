package S05T2Michel.DiceGame.model.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String email) {
        super("User already exists with email: " + email);
    }
}
