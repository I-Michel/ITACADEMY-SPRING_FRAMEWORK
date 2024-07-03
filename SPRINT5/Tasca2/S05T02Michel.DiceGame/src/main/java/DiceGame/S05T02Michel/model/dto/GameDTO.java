package DiceGame.S05T02Michel.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameDTO {

    private ObjectId gameId;
    private int diceRoll1;
    private int diceRoll2;
    private int result = diceRoll1 + diceRoll2;
    private boolean win;
    private Date gameDate;
    private int playerId;
}