package cat.itacademy.barcelonactiva.Michel.S05.T01.N02.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDetails {

    private int statusCode;
    private String message;
    private String description;
}