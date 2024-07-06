package S05T2Michel.DiceGame.model.service;

import S05T2Michel.DiceGame.model.dto.PlayerDTO;
import S05T2Michel.DiceGame.model.domain.Player;
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
    float getPlayerWinRate(int playerId);
    float getAverageWinRate();
    PlayerDTO getBestPlayer();
    PlayerDTO getWorstPlayer();
}