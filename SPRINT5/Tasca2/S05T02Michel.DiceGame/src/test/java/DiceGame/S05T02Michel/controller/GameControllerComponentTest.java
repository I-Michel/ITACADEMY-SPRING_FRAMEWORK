package DiceGame.S05T02Michel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import DiceGame.S05T02Michel.model.dto.GameDTO;
import DiceGame.S05T02Michel.model.service.impl.GameServiceMongoDB;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GameController.class)
@AutoConfigureMockMvc
public class GameControllerComponentTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameServiceMongoDB gameService;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenAddGame_thenStatus201() throws Exception {
        GameDTO dto = new GameDTO();
        dto.setPlayerId(1);
        dto.setResult(7);
        dto.setWin(true);

        when(gameService.addGame(anyInt())).thenReturn(dto);

        mockMvc.perform(post("/players/{id}/games", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.playerId").value(1))
                .andExpect(jsonPath("$.result").value(7))
                .andExpect(jsonPath("$.win").value(true));
    }

    @Test
    public void whenGetAllGames_thenStatus200() throws Exception {

        List<GameDTO> games = Arrays.asList(
                GameDTO.builder()
                        .gameId(new ObjectId())
                        .diceRoll1(1)
                        .diceRoll2(5)
                        .result(6)
                        .win(false)
                        .playerId(1)
                        .build(),

                GameDTO.builder().
                        gameId(new ObjectId())
                        .diceRoll1(3)
                        .diceRoll2(4)
                        .result(7)
                        .win(true)
                        .playerId(1)
                        .build()
        );

        when(gameService.getAllGames(1)).thenReturn(games);

        mockMvc.perform(get("/players/{id}/games", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].playerId").value(1))
                .andExpect(jsonPath("$[0].win").value(false))
                .andExpect(jsonPath("$[1].result").value(7))
                .andExpect(jsonPath("$[1].diceRoll1").value(3))
                .andExpect(jsonPath("$[2].diceRoll2").value(4));
    }

    @Test
    public void whenDeleteGames_thenStatus204() throws Exception {
        mockMvc.perform(delete("/players/{id}/games", 1))
                .andExpect(status().isNoContent());

        verify(gameService, times(1)).deleteGames(1);
    }
}