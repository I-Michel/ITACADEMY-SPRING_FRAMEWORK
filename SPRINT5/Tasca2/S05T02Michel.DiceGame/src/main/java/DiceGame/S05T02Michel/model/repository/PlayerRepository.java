package DiceGame.S05T02Michel.model.repository;

import DiceGame.S05T02Michel.model.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    boolean existsByPlayerNameIgnoreCase(String playerName);
}