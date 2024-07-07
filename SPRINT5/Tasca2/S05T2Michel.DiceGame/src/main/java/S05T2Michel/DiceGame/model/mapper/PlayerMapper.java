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
        PlayerDTO dto = PlayerDTO.builder()
                .playerId(player.getPlayerId())
                .playerName(player.getPlayerName())
                .password(player.getPassword())
                .role(player.getRole())
                .registrationDate(player.getRegistrationDate())
                .winRate(getPlayerWinRate(player.getPlayerId()))
                .build();

        dto.setWinRate(getPlayerWinRate(dto.getPlayerId()));

        return dto;
    }

    public Player convertToEntity(PlayerDTO dto) {
        return Player.builder()
                .playerName(dto.getPlayerName())
                .password(dto.getPassword())
                .role(dto.getRole())
                .build();
    }

    public float getPlayerWinRate(int id) {
        List<GameDTO> games = gameService.getAllGames(id);

        int wonGames = (int) games.stream()
                .filter(GameDTO::isWin)
                .count();

        return (!games.isEmpty()) ? (float) wonGames / games.size() : 0f;
    }
}