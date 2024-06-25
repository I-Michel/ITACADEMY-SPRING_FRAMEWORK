package cat.itacademy.barcelonactiva.Michel.S04.T02.N03.model.service;

import cat.itacademy.barcelonactiva.Michel.S04.T02.N03.model.domain.Fruit;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface FruitService {
    Fruit addFruit(Fruit fruit);
    Optional<Fruit> getOptionalFruit(ObjectId id);
    Fruit updateFruit(Fruit fruit);
    Fruit deleteFruit(ObjectId id);
    Fruit getOneFruit(ObjectId id);
    List<Fruit> getAllFruit();
}