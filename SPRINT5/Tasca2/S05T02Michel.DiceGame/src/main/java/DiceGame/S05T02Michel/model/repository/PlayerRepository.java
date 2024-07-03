package DiceGame.S05T02Michel.model.repository;

import DiceGame.S05T02Michel.model.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    Optional<Player> findByPlayerName (String playerName);
    boolean existsByPlayerNameIgnoreCase(String playerName);
}