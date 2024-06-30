package cat.itacademy.barcelonactiva.S05.T01.N03.Michel.model.exception;

public class FlowerNotFoundException extends RuntimeException {
    public FlowerNotFoundException(int id) {
        super("Flower not found with id: " + id);
    }

}
