package DiceGame.S05T02Michel.model.mapper;

import DiceGame.S05T02Michel.model.domain.Game;
import DiceGame.S05T02Michel.model.dto.GameDTO;
import org.springframework.stereotype.Component;

@Component
public class GameMapper {
    public GameDTO convertToDTO(Game game) {
        return GameDTO.builder()
                .gameId(game.getGameId())
                .diceRoll1(game.getDiceRoll1())
                .diceRoll2(game.getDiceRoll2())
                .result(game.getDiceRoll1() + game.getDiceRoll2())
                .win(game.getDiceRoll1() + game.getDiceRoll2() == 7)
                .gameDate(game.getGameDate())
                .playerId(game.getPlayerId())
                .build();

    }

    public Game convertToEntity(GameDTO dto) {
        return Game.builder()
                .diceRoll1(dto.getDiceRoll1())
                .diceRoll2(dto.getDiceRoll2())
                .playerId(dto.getPlayerId())
                .build();
    }
}
