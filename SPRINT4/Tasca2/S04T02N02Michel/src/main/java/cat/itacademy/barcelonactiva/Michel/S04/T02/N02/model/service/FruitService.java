package cat.itacademy.barcelonactiva.Michel.S04.T02.N02.model.service;

import cat.itacademy.barcelonactiva.Michel.S04.T02.N02.model.domain.Fruit;
import java.util.List;
import java.util.Optional;

public interface FruitService {
    Fruit addFruit(Fruit fruit);
    Optional<Fruit> getOptionalFruit(int id);
    Fruit updateFruit(Fruit fruit);
    Fruit deleteFruit(int id);
    Fruit getOneFruit(int id);
    List<Fruit> getAllFruit();
}