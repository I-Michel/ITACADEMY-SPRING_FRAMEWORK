package cat.itacademy.barcelonactiva.Michel.S04.T02.N01.model.services;

import cat.itacademy.barcelonactiva.Michel.S04.T02.N01.model.domain.Fruit;
import cat.itacademy.barcelonactiva.Michel.S04.T02.N01.model.repository.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FruitService {

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

    public Optional<Fruit> getOptionalFruit(int id) {
        return fruitRepository.findById(id);
    }

    public Fruit updateFruit(Fruit fruit) {
        Optional<Fruit> optionalFruit = getOptionalFruit(fruit.getId());
        try {
            if (optionalFruit.isPresent()) {
                Fruit okFruit = optionalFruit.get();
                okFruit.setName(fruit.getName());
                okFruit.setKg(fruit.getKg());
                return fruitRepository.save(okFruit);
            } else {
                throw new RuntimeException("Fruit not found: ID " + fruit.getId() + ".");
            }
        } catch (DataAccessException e) {
            throw new RuntimeException("Error when updating fruit with ID " + fruit.getId() +
                    ": " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating fruit with ID " +
                    fruit.getId() + ": " + e.getMessage());
        }
    }

    public Fruit deleteFruit(int id) {
        Optional<Fruit> optionalFruit = getOptionalFruit(id);
        try {
            if (optionalFruit.isPresent()) {
                Fruit deletedFruit = optionalFruit.get();
                fruitRepository.deleteById(id);
                return deletedFruit;
            } else {
                throw new RuntimeException("Fruit not found: ID " + id + ".");
            }
        } catch (DataAccessException e) {
            throw new RuntimeException("Error when deleting fruit with ID " + id +
                    ": " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while deleting fruit with ID " +
                    id + ": " + e.getMessage());
        }
    }

    public Fruit getOneFruit(int id) {
        Optional<Fruit> optionalFruit = getOptionalFruit(id);
        try {
            if (optionalFruit.isPresent()) {
                return optionalFruit.get();
            } else {
                throw new RuntimeException("Fruit not found: ID " + id + ".");
            }
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

    /* El Service contiene la lógica de negocio, los métodos de servicios. Es una capa intermedia
     * entre el Controller y el Repository: encapsula la lógica de negocio (Service) y la separa
     * de la lógica de presentación (Controller) y la lógica de acceso a datos (Repository).
     * Puede contener métodos que no estén directamente relacionados con operaciones CRUD, pero
     * que son necesarios para la lógica de negocio de la apliación. */
}