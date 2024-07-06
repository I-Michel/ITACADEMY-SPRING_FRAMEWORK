package DiceGame.S05T02Michel.service;

import DiceGame.S05T02Michel.model.domain.Game;
import DiceGame.S05T02Michel.model.dto.GameDTO;
import DiceGame.S05T02Michel.model.exception.GameNotFoundException;
import DiceGame.S05T02Michel.model.mapper.GameMapper;
import DiceGame.S05T02Michel.model.repository.GameRepository;
import DiceGame.S05T02Michel.model.service.impl.GameServiceMongoDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GameServiceMongoDBUnitTest {

    @Mock
    private GameRepository gameRepository;

    @Mock
    private GameMapper gameMapper;

    @InjectMocks
    private GameServiceMongoDB gameService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("GameServiceMongoDBUnitTest - Test return superHeroes list")
    @Test
    public void whenNewGame_thenNewGameDTOShouldBeCreated() {
        when(gameRepository.save(any(Game.class))).thenAnswer(invocation -> invocation.getArgument(0));

        GameDTO dto = gameService.newGame(1);

        assertThat(dto).isNotNull();
        assertThat(dto.getPlayerId()).isEqualTo(1);
    }

    @Test
    public void whenAddGame_thenGameDTOShouldBeAdded() {
        Game game = new Game();
        GameDTO dto = new GameDTO();
        dto.setPlayerId(1);

        when(gameMapper.convertToEntity(any(GameDTO.class))).thenReturn(game);
        when(gameMapper.convertToDTO(any(Game.class))).thenReturn(dto);
        when(gameRepository.save(any(Game.class))).thenReturn(game);

        GameDTO addedGame = gameService.addGame(1);

        assertThat(addedGame).isNotNull();
        assertThat(addedGame.getPlayerId()).isEqualTo(1);
    }

    @Test
    public void whenGetAllGames_thenListOfGameDTOsShouldBeReturned() {
        Game game = new Game();
        game.setPlayerId(1);

        GameDTO dto = new GameDTO();
        dto.setPlayerId(1);

        when(gameRepository.findAllByPlayerId(1)).thenReturn(List.of(game));
        when(gameMapper.convertToDTO(game)).thenReturn(dto);

        List<GameDTO> games = gameService.getAllGames(1);

        assertThat(games).isNotEmpty();
        assertThat(games).hasSize(1);
        assertThat(games.get(0).getPlayerId()).isEqualTo(1);
    }

    @Test
    public void whenGetAllGames_ifEmpty_thenShouldThrowGameNotFoundException() {
        when(gameRepository.findAllByPlayerId(1)).thenReturn(List.of());

        assertThatThrownBy(() -> gameService.getAllGames(1))
                .isInstanceOf(GameNotFoundException.class)
                .hasMessageContaining("No games found for player with ID: " + 1);
    }

    @Test
    public void whenDeleteGames_thenGamesShouldBeDeleted() {
        Game game = new Game();
        game.setPlayerId(1);

        when(gameRepository.findAllByPlayerId(1)).thenReturn(List.of(game));

        gameService.deleteGames(1);

        verify(gameRepository, times(1)).deleteAllByPlayerId(1);
    }

    @Test
    public void whenDeleteGames_ifEmpty_thenShouldThrowGameNotFoundException() {
        when(gameRepository.findAllByPlayerId(1)).thenReturn(List.of());

        assertThatThrownBy(() -> gameService.deleteGames(1))
                .isInstanceOf(GameNotFoundException.class)
                .hasMessageContaining("No games found to delete for player with ID: " + 1);
    }
}