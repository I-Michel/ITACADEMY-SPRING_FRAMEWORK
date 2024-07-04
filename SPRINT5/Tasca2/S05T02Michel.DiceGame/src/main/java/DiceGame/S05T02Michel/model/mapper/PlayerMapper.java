package DiceGame.S05T02Michel.model.mapper;

import DiceGame.S05T02Michel.model.domain.Player;
import DiceGame.S05T02Michel.model.dto.GameDTO;
import DiceGame.S05T02Michel.model.dto.PlayerDTO;
import DiceGame.S05T02Michel.model.service.impl.GameServiceMongoDB;
import DiceGame.S05T02Michel.model.service.impl.PlayerServiceMySQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class PlayerMapper {

    @Autowired
    private PlayerServiceMySQL playerService;

    @Autowired
    private GameServiceMongoDB gameService;

    public PlayerDTO convertToDTO(Player player) {

        List<GameDTO> games = gameService.getAllGames(player.getPlayerId());

        return PlayerDTO.builder()
                .playerId(player.getPlayerId())
                .playerName(player.getPlayerName())
                .registrationDate(player.getRegistrationDate())
                .games(games)
                .winRate(playerService.getPlayerWinRate(player.getPlayerId()))
                .build();
    }

    public Player convertToEntity(PlayerDTO dto) {
        return Player.builder()
                .playerName(dto.getPlayerName())
                .build();
    }
}