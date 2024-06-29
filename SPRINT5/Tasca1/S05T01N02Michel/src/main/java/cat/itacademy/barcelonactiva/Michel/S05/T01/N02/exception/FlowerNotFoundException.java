package cat.itacademy.barcelonactiva.Michel.S05.T01.N02.exception;

public class FlowerNotFoundException extends RuntimeException {
    public FlowerNotFoundException(int id) {
        super("Flower not found with id: " + id);
    }

}
