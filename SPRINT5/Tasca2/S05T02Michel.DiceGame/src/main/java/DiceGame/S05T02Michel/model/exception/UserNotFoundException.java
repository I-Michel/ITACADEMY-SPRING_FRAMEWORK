package DiceGame.S05T02Michel.model.exception;

public class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String email) {
            super("User not found with email: " + email);
        }
}
