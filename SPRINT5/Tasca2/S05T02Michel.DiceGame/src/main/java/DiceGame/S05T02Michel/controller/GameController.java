package DiceGame.S05T02Michel.controller;

import DiceGame.S05T02Michel.model.dto.GameDTO;
import DiceGame.S05T02Michel.model.service.impl.GameServiceMongoDB;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class GameController {

    @Autowired
    private GameServiceMongoDB gameServiceMongoDB;

    // POST '/players/{id}/games': un jugador/a específic realitza una tirada dels daus.
    @Operation(summary = "Add new game by player ID")
    @PostMapping("/{id}/games")
    public ResponseEntity<GameDTO> addGame(@PathVariable int playerId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gameServiceMongoDB.addGame(playerId));
    }

    // GET '/players/{id}/games': retorna el llistat de jugades per un jugador/a.
    @Operation(summary = "Get all games by player ID")
    @GetMapping("/{id}/games")
    public ResponseEntity<List<GameDTO>> getAllGames(@PathVariable int playerId) {
        return ResponseEntity.ok(gameServiceMongoDB.getAllGames(playerId));
    }

    // DELETE '/players/{id}/games': elimina les tirades del jugador/a.
    @Operation(summary = "Delete all games by player ID")
    @DeleteMapping("/{id}/games")
    public ResponseEntity<GameDTO> deleteGames(@PathVariable int playerId) {
        gameServiceMongoDB.deleteGames(playerId);
        return ResponseEntity.noContent().build();
    }
}
