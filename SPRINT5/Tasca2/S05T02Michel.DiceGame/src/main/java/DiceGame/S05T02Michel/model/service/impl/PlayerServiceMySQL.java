package DiceGame.S05T02Michel.model.service.impl;

import DiceGame.S05T02Michel.model.domain.Player;
import DiceGame.S05T02Michel.model.dto.GameDTO;
import DiceGame.S05T02Michel.model.dto.PlayerDTO;
import DiceGame.S05T02Michel.model.exception.NoPlayersFoundException;
import DiceGame.S05T02Michel.model.exception.PlayerAlreadyExistsException;
import DiceGame.S05T02Michel.model.exception.PlayerNotFoundException;
import DiceGame.S05T02Michel.model.mapper.PlayerMapper;
import DiceGame.S05T02Michel.model.repository.GameRepository;
import DiceGame.S05T02Michel.model.repository.PlayerRepository;
import DiceGame.S05T02Michel.model.service.PlayerService;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerServiceMySQL implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PlayerMapper playerMapper;

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
    public PlayerDTO addPlayer(PlayerDTO dto) {
        validatePlayerName(dto.getPlayerName());
        return playerMapper.convertToDTO(playerRepository.save(playerMapper.convertToEntity(dto)));
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
    public PlayerDTO updatePlayerName(PlayerDTO dto) {
        Optional<Player> optionalPlayer = getOptionalPlayer(dto.getPlayerId());
        Player okPlayer = optionalPlayer.orElseThrow(() -> new PlayerNotFoundException(dto.getPlayerId()));

        okPlayer.setPlayerName(validatePlayerName(okPlayer.getPlayerName()));

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
    public float getPlayerWinRate(int playerId) {
        List<GameDTO> games = getOnePlayer(playerId).getGames();

        int wonGames = (int) games.stream()
                .filter(GameDTO::isWin)
                .count();

        return (!games.isEmpty()) ? (float) wonGames / games.size() : 0f;
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
}