package DiceGame.S05T02Michel.model.service;

import DiceGame.S05T02Michel.model.dto.GameDTO;

import java.util.List;

public interface GameService {
    GameDTO newGame(int playerId);
    GameDTO addGame(int id);
    List<GameDTO> getAllGames(int playerId);
    void deleteGames(int playerId);

}
