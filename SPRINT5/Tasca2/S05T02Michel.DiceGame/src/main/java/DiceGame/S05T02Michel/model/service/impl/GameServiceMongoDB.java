package DiceGame.S05T02Michel.model.service.impl;

import DiceGame.S05T02Michel.model.domain.Game;
import DiceGame.S05T02Michel.model.dto.GameDTO;
import DiceGame.S05T02Michel.model.exception.GameNotFoundException;
import DiceGame.S05T02Michel.model.mapper.GameMapper;
import DiceGame.S05T02Michel.model.repository.GameRepository;
import DiceGame.S05T02Michel.model.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class GameServiceMongoDB implements GameService {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameMapper gameMapper;

    @Override
    public GameDTO newGame(int playerId) {
        Random random = new Random();
        int diceRoll1 = random.nextInt(6) + 1;
        int diceRoll2 = random.nextInt(6) + 1;
        int result = diceRoll1 + diceRoll2;
        return GameDTO.builder()
                .diceRoll1(diceRoll1)
                .diceRoll2(diceRoll2)
                .result(result)
                .win(result == 7)
                .playerId(playerId)
                .build();
    }

    @Override
    public GameDTO addGame(int playerId) {
        return gameMapper.convertToDTO(gameRepository.save(gameMapper.convertToEntity(newGame(playerId))));
    }

    @Override
    public List<GameDTO> getAllGames(int playerId) {
        List<Game> games = gameRepository.findAllByPlayerId(playerId);

        if (games.isEmpty()) {
            throw new GameNotFoundException("No games found for player with ID: " + playerId);
        }
        return games.stream()
                .map(gameMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteGames(int playerId) {
        List<Game> games = gameRepository.findAllByPlayerId(playerId);

        if (games.isEmpty()) {
            throw new GameNotFoundException("No games found to delete for player with ID: " + playerId);
        }
        gameRepository.deleteAllByPlayerId(playerId);
    }
}