package S05T2Michel.DiceGame.model.repository.mysql;

import S05T2Michel.DiceGame.model.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    boolean existsByPlayerNameIgnoreCase(String playerName);
}