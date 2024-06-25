package cat.itacademy.barcelonactiva.Michel.S04.T02.N03.exception;

public class FruitNotFoundException extends RuntimeException {
    public FruitNotFoundException(String message) {
        super(message);
    }
}