package cat.itacademy.barcelonactiva.Michel.S04.T02.N03.model.service;

import cat.itacademy.barcelonactiva.Michel.S04.T02.N03.exception.FruitNotFoundException;
import cat.itacademy.barcelonactiva.Michel.S04.T02.N03.model.domain.Fruit;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface FruitService {
    Fruit addFruit(Fruit fruit);
    Optional<Fruit> getOptionalFruit(ObjectId id) throws FruitNotFoundException;
    Fruit updateFruit(Fruit fruit) throws FruitNotFoundException;
    Fruit deleteFruit(ObjectId id) throws FruitNotFoundException;
    Fruit getOneFruit(ObjectId id) throws FruitNotFoundException;
    List<Fruit> getAllFruit();
}