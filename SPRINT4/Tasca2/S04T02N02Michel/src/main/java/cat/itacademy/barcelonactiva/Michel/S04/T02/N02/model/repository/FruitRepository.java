package cat.itacademy.barcelonactiva.Michel.S04.T02.N02.model.repository;

import cat.itacademy.barcelonactiva.Michel.S04.T02.N02.model.domain.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FruitRepository extends JpaRepository<Fruit, Integer> {

    // El Repository maneja y contiene métodos/operaciones CRUD para Fruit que luego usaremos en Service.
}
