package S05T2Michel.DiceGame.model.service.impl;

import S05T2Michel.DiceGame.model.domain.Player;
import S05T2Michel.DiceGame.model.dto.GameDTO;
import S05T2Michel.DiceGame.model.dto.PlayerDTO;
import S05T2Michel.DiceGame.model.exception.NoPlayersFoundException;
import S05T2Michel.DiceGame.model.exception.PlayerAlreadyExistsException;
import S05T2Michel.DiceGame.model.exception.PlayerNotFoundException;
import S05T2Michel.DiceGame.model.mapper.PlayerMapper;
import S05T2Michel.DiceGame.model.repository.mongodb.GameRepository;
import S05T2Michel.DiceGame.model.repository.mysql.PlayerRepository;
import S05T2Michel.DiceGame.model.service.PlayerService;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerServiceMySQL implements PlayerService {

    @Autowired
    private final PlayerRepository playerRepository;

    @Autowired
    private final GameRepository gameRepository;

    @Autowired
    private final PlayerMapper playerMapper;

    @Override
    public String validatePlayerName(String playerName) {
        if (StringUtils.isBlank(playerName) || playerName.equalsIgnoreCase("UNKNOWN")) {
            playerName = "UNKNOWN";
        } else if (playerRepository.existsByPlayerNameIgnoreCase(playerName)) {
            throw new PlayerAlreadyExistsException(playerName);
        }
        return playerName;
    }

    @Override
    public Optional<Player> getOptionalPlayer(int playerId) {
        return playerRepository.findById(playerId);
    }

    @Override
    public PlayerDTO getOnePlayer(int playerId) {
        Optional<Player> optionalPlayer = getOptionalPlayer(playerId);

        return playerMapper.convertToDTO(optionalPlayer.orElseThrow(() -> new PlayerNotFoundException(playerId)));
    }

    @Override
    public List<PlayerDTO> getAllPlayers() {
        List<Player> players = playerRepository.findAll();
        return players.stream()
                .map(playerMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PlayerDTO updatePlayerName(int id, String newName) {
        Optional<Player> optionalPlayer = getOptionalPlayer(id);
        Player okPlayer = optionalPlayer.orElseThrow(() -> new PlayerNotFoundException(id));

        okPlayer.setPlayerName(validatePlayerName(newName));

        return playerMapper.convertToDTO(playerRepository.save(okPlayer));
    }

    @Override
    public PlayerDTO deletePlayer(int playerId) {
        Optional<Player> optionalPlayer = getOptionalPlayer(playerId);
        Player okPlayer = optionalPlayer.orElseThrow(() -> new PlayerNotFoundException(playerId));

        playerRepository.deleteById(playerId);
        gameRepository.deleteAllByPlayerId(playerId);

        return playerMapper.convertToDTO(okPlayer);
    }

    @Override
    public float getAverageWinRate() {
        List<PlayerDTO> players = getAllPlayers();

        if (players.isEmpty()) {
            throw new NoPlayersFoundException();
        }

        OptionalDouble totalWinRate = players.stream()
                .mapToDouble(PlayerDTO::getWinRate)
                .average();

        return (float) totalWinRate.orElse(0);
    }

    @Override
    public PlayerDTO getBestPlayer() {
        List<PlayerDTO> players = getAllPlayers();

        return players.stream()
                .max(Comparator.comparing(PlayerDTO::getWinRate))
                .orElseThrow(NoPlayersFoundException::new);
    }

    @Override
    public PlayerDTO getWorstPlayer() {
        List<PlayerDTO> players = getAllPlayers();

        return players.stream()
                .min(Comparator.comparing(PlayerDTO::getWinRate))
                .orElseThrow(NoPlayersFoundException::new);
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String playerName) {
                return playerRepository.findPlayerByPlayerName(playerName)
                        .orElseThrow(() -> new UsernameNotFoundException("Player not found with name: " + playerName));
            }
        };
    }
}