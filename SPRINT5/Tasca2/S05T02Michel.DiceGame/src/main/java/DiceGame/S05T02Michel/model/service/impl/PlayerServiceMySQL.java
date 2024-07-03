package DiceGame.S05T02Michel.model.service.impl;

import DiceGame.S05T02Michel.model.domain.Player;
import DiceGame.S05T02Michel.model.dto.GameDTO;
import DiceGame.S05T02Michel.model.dto.PlayerDTO;
import DiceGame.S05T02Michel.model.exception.PlayerAlreadyExistsException;
import DiceGame.S05T02Michel.model.exception.PlayerNotFoundException;
import DiceGame.S05T02Michel.model.repository.GameRepository;
import DiceGame.S05T02Michel.model.repository.PlayerRepository;
import DiceGame.S05T02Michel.model.service.PlayerService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlayerServiceMySQL implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameRepository gameRepository;

    private PlayerDTO convertToDTO(Player player) {
        return PlayerDTO.builder()
                .playerId(player.getPlayerId())
                .playerName(player.getPlayerName())
                .registrationDate(player.getRegistrationDate())
                .games(new ArrayList<GameDTO>())
                .build();
    }

    private Player convertToEntity(PlayerDTO dto) {
        return Player.builder()
                .playerName(dto.getPlayerName())
                .build();
    }

    @Override
    public String validatePlayerName(String playerName) {
        if (StringUtils.isBlank(playerName) || playerName.equalsIgnoreCase("UNKNOWN")) {
            return "UNKNOWN";
        } else if (playerRepository.existsByPlayerNameIgnoreCase(playerName)) {
            throw new PlayerAlreadyExistsException(playerName);
        } else {
            return playerName;
        }
    }

    @Override
    public PlayerDTO addPlayer(PlayerDTO dto) {
        validatePlayerName(dto.getPlayerName());
        return convertToDTO(playerRepository.save(convertToEntity(dto)));
    }

    @Override
    public Optional<Player> getOptionalPlayer(int playerId) {
        return playerRepository.findById(playerId);
    }

    @Override
    public PlayerDTO getOnePlayer(int playerId) {
        Optional<Player> optionalPlayer = getOptionalPlayer(playerId);

        return convertToDTO(optionalPlayer.orElseThrow(() -> new PlayerNotFoundException(playerId)));
    }

    @Override
    public List<PlayerDTO> getAllPlayers() {
        List<Player> players = playerRepository.findAll();
        return players.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PlayerDTO updatePlayerName(PlayerDTO dto) {
        Optional<Player> optionalPlayer = getOptionalPlayer(dto.getPlayerId());
        Player okPlayer = optionalPlayer.orElseThrow(() -> new PlayerNotFoundException(dto.getPlayerId()));

        okPlayer.setPlayerName(validatePlayerName(okPlayer.getPlayerName()));

        return convertToDTO(playerRepository.save(okPlayer));
    }

    @Override
    public PlayerDTO deletePlayer(int playerId) {
        Optional<Player> optionalPlayer = getOptionalPlayer(playerId);
        Player okPlayer = optionalPlayer.orElseThrow(() -> new PlayerNotFoundException(playerId));

        playerRepository.deleteById(playerId);
        gameRepository.deleteAllByPlayerId(playerId);

        return convertToDTO(okPlayer);
    }

    @Override
    public void getPlayerWinrate(int playerId) {
        List<GameDTO> games = getAllGames(playerId);

    }
}