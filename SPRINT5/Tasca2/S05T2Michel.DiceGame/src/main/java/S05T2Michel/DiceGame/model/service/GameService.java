package S05T2Michel.DiceGame.model.service;

import S05T2Michel.DiceGame.model.dto.GameDTO;
import S05T2Michel.DiceGame.model.domain.Game;

import java.util.List;

public interface GameService {
    GameDTO newGame(int playerId);
    GameDTO addGame(int id);
    List<GameDTO> getAllGames(int playerId);
    void deleteGames(int playerId);
}