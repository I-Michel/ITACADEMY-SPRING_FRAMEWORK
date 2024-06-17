package cat.itacademy.barcelonactiva.Michel.S04.T02.N01.model.service;

import cat.itacademy.barcelonactiva.Michel.S04.T02.N01.exception.FruitNotFoundException;
import cat.itacademy.barcelonactiva.Michel.S04.T02.N01.model.domain.Fruit;

import java.util.List;
import java.util.Optional;

public interface FruitService {
    Fruit addFruit(Fruit fruit);
    Optional<Fruit> getOptionalFruit(int id) throws FruitNotFoundException;
    Fruit updateFruit(Fruit fruit) throws FruitNotFoundException;
    Fruit deleteFruit(int id) throws FruitNotFoundException;
    Fruit getOneFruit(int id) throws FruitNotFoundException;
    List<Fruit> getAllFruit();
}