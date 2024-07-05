package DiceGame.S05T02Michel.model.service.impl;

import DiceGame.S05T02Michel.model.domain.User;
import DiceGame.S05T02Michel.model.dto.request.SignInRequest;
import DiceGame.S05T02Michel.model.dto.request.SignUpRequest;
import DiceGame.S05T02Michel.model.dto.response.AuthenticationResponse;
import DiceGame.S05T02Michel.model.enums.Role;
import DiceGame.S05T02Michel.model.exception.UserAlreadyExistsException;
import DiceGame.S05T02Michel.model.exception.UserNotFoundException;
import DiceGame.S05T02Michel.model.repository.UserRepository;
import DiceGame.S05T02Michel.model.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtServiceImpl jwtService;
    private AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse signUp(SignUpRequest request) {
        if (request.getEmail().isEmpty() || request.getPassword().isEmpty()){
            throw new IllegalArgumentException("Email and password cannot be null");
        }

        userRepository.findUserByEmail(request.getEmail())
                .ifPresent(user -> {
                    throw new UserAlreadyExistsException(user.getEmail());
                });


        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
        String token = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public AuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        User user = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow(()-> new UserNotFoundException(request.getEmail()));

        String token = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
}