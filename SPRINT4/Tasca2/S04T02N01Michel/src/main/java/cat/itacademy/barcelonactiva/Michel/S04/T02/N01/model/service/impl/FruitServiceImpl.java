package cat.itacademy.barcelonactiva.Michel.S04.T02.N01.model.service.impl;

import cat.itacademy.barcelonactiva.Michel.S04.T02.N01.exception.FruitNotFoundException;
import cat.itacademy.barcelonactiva.Michel.S04.T02.N01.model.domain.Fruit;
import cat.itacademy.barcelonactiva.Michel.S04.T02.N01.model.repository.FruitRepository;
import cat.itacademy.barcelonactiva.Michel.S04.T02.N01.model.service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FruitServiceImpl implements FruitService {

    @Autowired
    private FruitRepository fruitRepository;

    public Fruit addFruit(Fruit fruit) {
        try {
            return fruitRepository.save(fruit);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error when adding fruit: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while adding fruit: " + e.getMessage());
        }
    }

    public Optional<Fruit> getOptionalFruit(int id) throws FruitNotFoundException {
        Optional<Fruit> optionalFruit = fruitRepository.findById(id);

        if (optionalFruit.isEmpty()) {
            throw new FruitNotFoundException("Fruit not found: ID " + id + ".");
        }
        return optionalFruit;
    }

    public Fruit updateFruit(Fruit fruit) throws FruitNotFoundException {
        Optional<Fruit> optionalFruit = getOptionalFruit(fruit.getId());

        Fruit okFruit = optionalFruit.get();
        okFruit.setName(fruit.getName());
        okFruit.setKg(fruit.getKg());

        try {
            return fruitRepository.save(okFruit);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error when updating fruit with ID " + fruit.getId() +
                    ": " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating fruit with ID " +
                    fruit.getId() + ": " + e.getMessage());
        }
    }

    public Fruit deleteFruit(int id) throws FruitNotFoundException {
        Optional<Fruit> optionalFruit = getOptionalFruit(id);
        Fruit deletedFruit = optionalFruit.get();

        try {
            fruitRepository.deleteById(id);
            return deletedFruit;
        } catch (DataAccessException e) {
            throw new RuntimeException("Error when deleting fruit with ID " + id +
                    ": " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while deleting fruit with ID " +
                    id + ": " + e.getMessage());
        }
    }

    public Fruit getOneFruit(int id) throws FruitNotFoundException {
        Optional<Fruit> optionalFruit = getOptionalFruit(id);

        try {
            return optionalFruit.get();

        } catch (DataAccessException e) {
            throw new RuntimeException("Error when getting fruit with ID " + id +
                    ": " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while getting fruit with ID " +
                    id + ": " + e.getMessage());
        }
    }

    public List<Fruit> getAllFruit() {
        return fruitRepository.findAll();
    }
}
