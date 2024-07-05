package DiceGame.S05T02Michel.controller;

import DiceGame.S05T02Michel.model.dto.request.SignInRequest;
import DiceGame.S05T02Michel.model.dto.request.SignUpRequest;
import DiceGame.S05T02Michel.model.dto.response.AuthenticationResponse;
import DiceGame.S05T02Michel.model.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players/auth")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @Operation(summary = "Sign up: create new user")
    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> signUp(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signUp(request));
    }

    @Operation(summary = "Sign in as user")
    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> signIn(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(authenticationService.signIn(request));
    }
}