package DiceGame.S05T02Michel.model.repository;

import DiceGame.S05T02Michel.model.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
}
