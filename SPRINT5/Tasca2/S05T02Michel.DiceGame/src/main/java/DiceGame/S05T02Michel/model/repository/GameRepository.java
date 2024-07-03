package DiceGame.S05T02Michel.model.repository;

import DiceGame.S05T02Michel.model.domain.Game;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GameRepository extends MongoRepository<Game, ObjectId> {
    void deleteAllByPlayerId(int playerId);
    List<Game> findAllByPlayerId(int playerId);
}