package DiceGame.S05T02Michel.service;

import DiceGame.S05T02Michel.model.domain.Player;
import DiceGame.S05T02Michel.model.dto.GameDTO;
import DiceGame.S05T02Michel.model.dto.PlayerDTO;
import DiceGame.S05T02Michel.model.exception.PlayerNotFoundException;
import DiceGame.S05T02Michel.model.mapper.PlayerMapper;
import DiceGame.S05T02Michel.model.repository.GameRepository;
import DiceGame.S05T02Michel.model.repository.PlayerRepository;
import DiceGame.S05T02Michel.model.service.impl.PlayerServiceMySQL;
import DiceGame.S05T02Michel.model.exception.PlayerAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceMySQLUnitTest {

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private PlayerMapper playerMapper;

    @InjectMocks
    private PlayerServiceMySQL playerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenValidPlayerName_thenPlayerShouldBeValidated() {
        String playerName = "Michel";

        when(playerRepository.existsByPlayerNameIgnoreCase(playerName)).thenReturn(false);

        String validatedName = playerService.validatePlayerName(playerName);

        assertThat(validatedName).isEqualTo(playerName);
    }

    @Test
    public void whenPlayerNameAlreadyExists_thenShouldThrowException() {
        String playerName = "Michel";

        when(playerRepository.existsByPlayerNameIgnoreCase(playerName)).thenReturn(true);

        assertThatThrownBy(() -> playerService.validatePlayerName(playerName))
                .isInstanceOf(PlayerAlreadyExistsException.class)
                .hasMessageContaining(playerName);
    }

    @Test
    public void whenAddPlayer_thenPlayerShouldBeAdded() {
        Player player = new Player();
        player.setPlayerName("Michel");

        PlayerDTO dto = new PlayerDTO();
        dto.setPlayerName("Michel");

        when(playerMapper.convertToEntity(dto)).thenReturn(player);
        when(playerRepository.save(player)).thenReturn(player);
        when(playerMapper.convertToDTO(player)).thenReturn(dto);

        PlayerDTO addedPlayer = playerService.addPlayer(dto);

        assertThat(addedPlayer.getPlayerName()).isEqualTo("Michel");
        verify(playerRepository, times(1)).save(player);
    }

    @Test
    public void whenGetOnePlayer_thenPlayerShouldBeReturned() {
        Player player = new Player();
        player.setPlayerId(1);

        PlayerDTO dto = new PlayerDTO();
        dto.setPlayerId(1);

        when(playerRepository.findById(1)).thenReturn(Optional.of(player));
        when(playerMapper.convertToDTO(player)).thenReturn(dto);

        PlayerDTO foundPlayer = playerService.getOnePlayer(1);

        assertThat(foundPlayer.getPlayerId()).isEqualTo(1);
    }

    @Test
    public void whenGetOnePlayer_ifNotFound_thenShouldThrowException() {
        when(playerRepository.findById(1)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> playerService.getOnePlayer(1))
                .isInstanceOf(PlayerNotFoundException.class)
                .hasMessageContaining(String.valueOf(1));
    }

    @Test
    public void whenUpdatePlayerName_thenPlayerNameShouldBeUpdated() {
        Player player = new Player();
        player.setPlayerId(1);
        player.setPlayerName("Old Name");

        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setPlayerId(1);
        playerDTO.setPlayerName("New Name");

        when(playerRepository.findById(1)).thenReturn(Optional.of(player));
        when(playerRepository.save(any(Player.class))).thenReturn(player);
        when(playerMapper.convertToDTO(player)).thenReturn(playerDTO);

        PlayerDTO updatedPlayer = playerService.updatePlayerName(playerDTO);

        assertThat(updatedPlayer.getPlayerName()).isEqualTo("New Name");
        verify(playerRepository, times(1)).save(player);
    }

    @Test
    public void whenDeletePlayer_thenPlayerShouldBeDeleted() {
        Player player = new Player();
        player.setPlayerId(11);

        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setPlayerId(11);

        when(playerRepository.findById(11)).thenReturn(Optional.of(player));
        when(playerMapper.convertToDTO(player)).thenReturn(playerDTO);

        PlayerDTO deletedPlayer = playerService.deletePlayer(11);

        assertThat(deletedPlayer.getPlayerId()).isEqualTo(11);
        verify(playerRepository, times(1)).deleteById(11);
        verify(gameRepository, times(1)).deleteAllByPlayerId(11);
    }

    @Test
    public void whenGetPlayerWinRate_thenReturnWinRate() {
        Player player = new Player();
        player.setPlayerId(1);

        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setPlayerId(1);

        GameDTO win = new GameDTO();
        win.setWin(true);
        GameDTO lose = new GameDTO();
        lose.setWin(false);

        List<GameDTO> games = Arrays.asList(win, lose);
        playerDTO.setGames(games);

        when(playerRepository.findById(1)).thenReturn(Optional.of(player));
        when(playerMapper.convertToDTO(player)).thenReturn(playerDTO);

        float winRate = playerService.getPlayerWinRate(1);

        assertThat(winRate).isEqualTo(0.5f);
    }

    @Test
    public void whenGetAverageWinRate_thenReturnAverageWinRate() {
        PlayerDTO player1 = new PlayerDTO();
        player1.setWinRate(1f);

        PlayerDTO player2 = new PlayerDTO();
        player2.setWinRate(0.5f);

        List<PlayerDTO> players = Arrays.asList(player1, player2);

        when(playerRepository.findAll()).thenReturn(Arrays.asList(new Player(), new Player()));
        when(playerMapper.convertToDTO(any(Player.class))).thenReturn(player1, player2);

        float averageWinRate = playerService.getAverageWinRate();

        assertThat(averageWinRate).isEqualTo(0.75f);
    }

    @Test
    public void whenGetBestPlayer_thenReturnPlayerWithBestWinRate() {
        PlayerDTO player1 = new PlayerDTO();
        player1.setWinRate(1f);

        PlayerDTO player2 = new PlayerDTO();
        player2.setWinRate(2f);

        List<PlayerDTO> players = Arrays.asList(player1, player2);

        when(playerRepository.findAll()).thenReturn(Arrays.asList(new Player(), new Player()));
        when(playerMapper.convertToDTO(any(Player.class))).thenReturn(player1, player2);

        PlayerDTO bestPlayer = playerService.getBestPlayer();

        assertThat(bestPlayer.getWinRate()).isEqualTo(2f);
    }

    @Test
    public void whenGetWorstPlayer_thenReturnPlayerWithWorstWinRate() {
        PlayerDTO player1 = new PlayerDTO();
        player1.setWinRate(1f);

        PlayerDTO player2 = new PlayerDTO();
        player2.setWinRate(2f);

        List<PlayerDTO> players = Arrays.asList(player1, player2);

        when(playerRepository.findAll()).thenReturn(Arrays.asList(new Player(), new Player()));
        when(playerMapper.convertToDTO(any(Player.class))).thenReturn(player1, player2);

        PlayerDTO worstPlayer = playerService.getWorstPlayer();

        assertThat(worstPlayer.getWinRate()).isEqualTo(1f);
    }
}