package DiceGame.S05T02Michel.model.mapper;

import DiceGame.S05T02Michel.model.domain.Player;
import DiceGame.S05T02Michel.model.dto.PlayerDTO;
import DiceGame.S05T02Michel.model.service.impl.GameServiceMongoDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    @Autowired
    private GameServiceMongoDB gameService;

    public PlayerDTO convertToDTO(Player player) {

        return PlayerDTO.builder()
                .playerId(player.getPlayerId())
                .playerName(player.getPlayerName())
                .registrationDate(player.getRegistrationDate())
                .games(gameService.getAllGames(player.getPlayerId()))
                .build();
    }

    public Player convertToEntity(PlayerDTO dto) {
        return Player.builder()
                .playerName(dto.getPlayerName())
                .build();
    }
}