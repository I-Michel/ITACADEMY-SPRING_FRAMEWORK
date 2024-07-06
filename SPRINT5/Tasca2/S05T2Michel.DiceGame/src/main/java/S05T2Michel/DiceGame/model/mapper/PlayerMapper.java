package S05T2Michel.DiceGame.model.mapper;

import S05T2Michel.DiceGame.model.domain.Player;
import S05T2Michel.DiceGame.model.dto.GameDTO;
import S05T2Michel.DiceGame.model.dto.PlayerDTO;
import S05T2Michel.DiceGame.model.service.impl.GameServiceMongoDB;
import S05T2Michel.DiceGame.model.service.impl.PlayerServiceMySQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class PlayerMapper {

    @Autowired
    @Lazy
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