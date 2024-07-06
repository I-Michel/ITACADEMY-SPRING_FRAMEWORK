package S05T2Michel.DiceGame.model.service.impl;

import S05T2Michel.DiceGame.model.repository.mysql.UserRepository;
import S05T2Michel.DiceGame.model.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) {
                return userRepository.findUserByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
            }
        };
    }
}