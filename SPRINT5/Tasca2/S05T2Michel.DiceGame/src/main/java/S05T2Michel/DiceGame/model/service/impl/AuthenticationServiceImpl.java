package S05T2Michel.DiceGame.model.service.impl;

import S05T2Michel.DiceGame.model.domain.User;
import S05T2Michel.DiceGame.model.dto.request.SignInRequest;
import S05T2Michel.DiceGame.model.dto.request.SignUpRequest;
import S05T2Michel.DiceGame.model.dto.response.AuthenticationResponse;
import S05T2Michel.DiceGame.model.enums.Role;
import S05T2Michel.DiceGame.model.exception.UserAlreadyExistsException;
import S05T2Michel.DiceGame.model.exception.UserNotFoundException;
import S05T2Michel.DiceGame.model.repository.mysql.UserRepository;
import S05T2Michel.DiceGame.model.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
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