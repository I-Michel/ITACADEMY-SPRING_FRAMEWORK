package cat.itacademy.barcelonactiva.Michel.S04.T02.N01.model.service.impl;

import cat.itacademy.barcelonactiva.Michel.S04.T02.N01.exception.FruitNotFoundException;
import cat.itacademy.barcelonactiva.Michel.S04.T02.N01.model.domain.Fruit;
import cat.itacademy.barcelonactiva.Michel.S04.T02.N01.model.repository.FruitRepository;
import cat.itacademy.barcelonactiva.Michel.S04.T02.N01.model.service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FruitServiceImpl implements FruitService {

    @Autowired
    private FruitRepository fruitRepository;

    @Override
    public Fruit addFruit(Fruit fruit) {
        return fruitRepository.save(fruit);
    }

    @Override
    public Optional<Fruit> getOptionalFruit(int id) {
        return fruitRepository.findById(id);
    }

    @Override
    public Fruit updateFruit(Fruit fruit) {
        Optional<Fruit> optionalFruit = getOptionalFruit(fruit.getId());
        Fruit okFruit = optionalFruit.orElseThrow(() -> new FruitNotFoundException("Fruit not found with id: " + fruit.getId()));

        okFruit.setName(fruit.getName());
        okFruit.setKg(fruit.getKg());

        return fruitRepository.save(okFruit);
    }

    @Override
    public Fruit deleteFruit(int id) {
        Optional<Fruit> optionalFruit = getOptionalFruit(id);
        Fruit okFruit = optionalFruit.orElseThrow(() -> new FruitNotFoundException("Fruit not found with id: " + id));

        fruitRepository.deleteById(id);
        return okFruit;
    }

    @Override
    public Fruit getOneFruit(int id) {
        Optional<Fruit> optionalFruit = getOptionalFruit(id);
        return optionalFruit.orElseThrow(() -> new FruitNotFoundException("Fruit not found with id: " + id));
    }

    @Override
    public List<Fruit> getAllFruit() {
        return fruitRepository.findAll();
    }
}