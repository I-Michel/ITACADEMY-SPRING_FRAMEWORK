package S05T2Michel.DiceGame.model.mapper;

import S05T2Michel.DiceGame.model.domain.Player;
import S05T2Michel.DiceGame.model.dto.GameDTO;
import S05T2Michel.DiceGame.model.dto.PlayerDTO;
import S05T2Michel.DiceGame.model.service.impl.GameServiceMongoDB;
import S05T2Michel.DiceGame.model.service.impl.PlayerServiceMySQL;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PlayerMapper {

    @Autowired
    private final GameServiceMongoDB gameService;

    public PlayerDTO convertToDTO(Player player) {

        List<GameDTO> games = gameService.getAllGames(player.getPlayerId());

        PlayerDTO dto = PlayerDTO.builder()
                .playerId(player.getPlayerId())
                .playerName(player.getPlayerName())
                .password(player.getPassword())
                .role(player.getRole())
                .registrationDate(player.getRegistrationDate())
                .games(games)
                .build();

        dto.setWinRate(dto.calculateWinRate());

        return dto;
    }

    public Player convertToEntity(PlayerDTO dto) {
        return Player.builder()
                .playerName(dto.getPlayerName())
                .password(dto.getPassword())
                .role(dto.getRole())
                .build();
    }
}