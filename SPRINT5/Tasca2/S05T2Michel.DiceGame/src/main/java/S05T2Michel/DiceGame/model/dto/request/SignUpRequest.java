package S05T2Michel.DiceGame.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    @NotBlank
    @Size(max = 45)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 45)
    private String password;
}
