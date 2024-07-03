package DiceGame.S05T02Michel.model.service;

import DiceGame.S05T02Michel.model.domain.Player;
import DiceGame.S05T02Michel.model.dto.PlayerDTO;
import java.util.List;
import java.util.Optional;

public interface PlayerService {
    String validatePlayerName(String playerName);
    PlayerDTO addPlayer(PlayerDTO dto);
    Optional<Player> getOptionalPlayer(int id);
    PlayerDTO getOnePlayer(int id);
    List<PlayerDTO> getAllPlayers();
    PlayerDTO updatePlayerName(PlayerDTO dto);
    PlayerDTO deletePlayer(int id);
}