package DiceGame.S05T02Michel.model.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {

    private int statusCode;
    private String message;
    private String description;
}
