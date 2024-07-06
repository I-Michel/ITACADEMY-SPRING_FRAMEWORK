package S05T2Michel.DiceGame.model.exception;

public class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String email) {
            super("User not found with email: " + email);
        }
}
