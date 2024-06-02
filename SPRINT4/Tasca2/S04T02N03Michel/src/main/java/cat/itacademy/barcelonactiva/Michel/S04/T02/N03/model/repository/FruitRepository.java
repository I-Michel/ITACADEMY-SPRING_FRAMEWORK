package cat.itacademy.barcelonactiva.Michel.S04.T02.N03.model.repository;

import cat.itacademy.barcelonactiva.Michel.S04.T02.N03.model.domain.Fruit;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FruitRepository extends MongoRepository<Fruit, ObjectId> {

    // El Repository maneja y contiene métodos/operaciones CRUD para Fruit que luego usaremos en Service.
}
