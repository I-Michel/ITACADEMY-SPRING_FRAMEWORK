package S05T2Michel.DiceGame.controller;

import S05T2Michel.DiceGame.model.dto.GameDTO;
import S05T2Michel.DiceGame.model.dto.PlayerDTO;
import S05T2Michel.DiceGame.model.service.impl.PlayerServiceMySQL;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.Collections;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PlayerController.class)
@AutoConfigureMockMvc
public class PlayerControllerComponentTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerServiceMySQL playerService;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenAddPlayer_thenStatus201() throws Exception {
        PlayerDTO dto = new PlayerDTO();
        dto.setPlayerName("Michel");

        when(playerService.addPlayer(any(PlayerDTO.class))).thenReturn(dto);

        mockMvc.perform(post("/players/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.playerName").value("Michel"));
    }

    @Test
    public void whenUpdatePlayerName_thenStatus200() throws Exception {
        PlayerDTO dto = new PlayerDTO();
        dto.setPlayerId(1);
        dto.setPlayerName("New name");

        when(playerService.updatePlayerName(any(PlayerDTO.class))).thenReturn(dto);

        mockMvc.perform(put("/players/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.playerName").value("New name"));
    }

    @Test
    public void whenGetOnePlayer_thenStatus200() throws Exception {
        GameDTO gameDTO = new GameDTO();
        gameDTO.setGameId(new ObjectId());
        gameDTO.setDiceRoll1(3);
        gameDTO.setDiceRoll2(4);
        gameDTO.setResult(7);
        gameDTO.setWin(true);
        gameDTO.setPlayerId(1);

        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setPlayerId(1);
        playerDTO.setPlayerName("Michel");
        playerDTO.setGames(Collections.singletonList(gameDTO));

        when(playerService.getOnePlayer(1)).thenReturn(playerDTO);

        mockMvc.perform(get("/players/get/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.playerId").value(1))
                .andExpect(jsonPath("$.playerName").value("Michel"))
                .andExpect(jsonPath("$.games[0].gameId").exists())
                .andExpect(jsonPath("$.games[0].diceRoll1").value(3))
                .andExpect(jsonPath("$.games[0].diceRoll2").value(4))
                .andExpect(jsonPath("$.games[0].result").value(7))
                .andExpect(jsonPath("$.games[0].win").value(true));
    }

    @Test
    public void whenGetAllPlayers_thenStatus200() throws Exception {
        GameDTO gameDTO1 = new GameDTO();
        gameDTO1.setGameId(new ObjectId());
        gameDTO1.setDiceRoll1(3);
        gameDTO1.setDiceRoll2(4);
        gameDTO1.setResult(7);
        gameDTO1.setWin(true);
        gameDTO1.setPlayerId(1);

        GameDTO gameDTO2 = new GameDTO();
        gameDTO2.setGameId(new ObjectId());
        gameDTO2.setDiceRoll1(1);
        gameDTO2.setDiceRoll2(4);
        gameDTO2.setResult(5);
        gameDTO2.setWin(false);
        gameDTO2.setPlayerId(2);

        PlayerDTO playerDTO1 = new PlayerDTO();
        playerDTO1.setPlayerId(1);
        playerDTO1.setPlayerName("Michel");
        playerDTO1.setGames(Collections.singletonList(gameDTO1));

        PlayerDTO playerDTO2 = new PlayerDTO();
        playerDTO2.setPlayerId(2);
        playerDTO2.setPlayerName("Lehcim");
        playerDTO2.setGames(Collections.singletonList(gameDTO2));

        List<PlayerDTO> players = Arrays.asList(playerDTO1, playerDTO2);

        when(playerService.getAllPlayers()).thenReturn(players);

        mockMvc.perform(get("/players/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].playerId").value(1))
                .andExpect(jsonPath("$[0].playerName").value("Michel"))
                .andExpect(jsonPath("$[0].games[0].gameId").exists())
                .andExpect(jsonPath("$[0].games[0].diceRoll1").value(3))
                .andExpect(jsonPath("$[0].games[0].win").value(true))
                .andExpect(jsonPath("$[1].playerId").value(2))
                .andExpect(jsonPath("$[1].playerName").value("Lehcim"))
                .andExpect(jsonPath("$[1].games[0].gameId").exists())
                .andExpect(jsonPath("$[1].games[0].diceRoll1").value(2))
                .andExpect(jsonPath("$[1].games[0].result").value(5));
    }

    @Test
    public void whenGetAverageWinRate_thenReturnAverageWinRate() throws Exception {
        float winRate = 0.5f;

        when(playerService.getAverageWinRate()).thenReturn(winRate);

        mockMvc.perform(get("/players/winrate")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(winRate));
    }

    @Test
    public void whenGetBestPlayer_thenReturnBestPlayer() throws Exception {
        PlayerDTO dto = new PlayerDTO();
        dto.setPlayerId(1);
        dto.setPlayerName("Michel");
        dto.setWinRate(5f);

        when(playerService.getBestPlayer()).thenReturn(dto);

        mockMvc.perform(get("/players/winrate/winner")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.playerId").value(dto.getPlayerId()))
                .andExpect(jsonPath("$.playerName").value(dto.getPlayerName()))
                .andExpect(jsonPath("$.winRate").value(dto.getWinRate()));
    }

    @Test
    public void whenGetWorstPlayer_thenReturnWorstPlayer() throws Exception {
        PlayerDTO dto = new PlayerDTO();
        dto.setPlayerId(1);
        dto.setPlayerName("Michel");
        dto.setWinRate(0f);

        when(playerService.getWorstPlayer()).thenReturn(dto);

        mockMvc.perform(get("/players/winrate/loser")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.playerId").value(dto.getPlayerId()))
                .andExpect(jsonPath("$.playerName").value(dto.getPlayerName()))
                .andExpect(jsonPath("$.winRate").value(dto.getWinRate()));
    }
}