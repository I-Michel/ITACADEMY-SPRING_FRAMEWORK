package S05T2Michel.DiceGame.model.dto;

import S05T2Michel.DiceGame.model.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Player name is required")
    @Size(min = 6, max = 10, message = "Player name must be between 6 and 10 characters")
    private String playerName;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 10, message = "Password must be between 6 and 10 characters")
    private String password;

    private Date registrationDate;
    private Role role;
    private List<GameDTO> games;
    private float winRate;

    public float calculateWinRate() {

        int wonGames = (int) this.games.stream()
                .filter(GameDTO::isWin)
                .count();

        return (!this.games.isEmpty()) ? (float) wonGames / this.games.size() : 0f;
    }
}