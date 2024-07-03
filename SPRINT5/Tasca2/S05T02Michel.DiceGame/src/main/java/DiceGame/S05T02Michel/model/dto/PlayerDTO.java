package DiceGame.S05T02Michel.model.dto;

import DiceGame.S05T02Michel.model.domain.Game;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerDTO {

    private int playerId;
    private String playerName;
    private Date registrationDate;
    private List<GameDTO> games;
    private float winRate;

    public static class calculateWinRate(int playerId) {

    }
}