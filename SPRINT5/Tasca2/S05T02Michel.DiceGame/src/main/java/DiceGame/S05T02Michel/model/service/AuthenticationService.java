package DiceGame.S05T02Michel.model.service;

import DiceGame.S05T02Michel.model.dto.request.SignInRequest;
import DiceGame.S05T02Michel.model.dto.request.SignUpRequest;
import DiceGame.S05T02Michel.model.dto.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse signUp(SignUpRequest request);

    AuthenticationResponse signIn(SignInRequest request);
}