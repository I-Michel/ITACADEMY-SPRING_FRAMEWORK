package S05T2Michel.DiceGame.controller;


import S05T2Michel.DiceGame.model.dto.GameDTO;
import S05T2Michel.DiceGame.model.service.impl.GameServiceMongoDB;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/players")
public class GameController {

    @Autowired
    private final GameServiceMongoDB gameServiceMongoDB;

    @Operation(summary = "Add new game by player ID")
    @PostMapping("/{id}/games")
    public ResponseEntity<GameDTO> addGame(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gameServiceMongoDB.addGame(id));
    }

    @Operation(summary = "Get all games by player ID")
    @GetMapping("/{id}/games")
    public ResponseEntity<List<GameDTO>> getAllGames(@PathVariable int id) {
        return ResponseEntity.ok(gameServiceMongoDB.getAllGames(id));
    }

    @Operation(summary = "Delete all games by player ID")
    @DeleteMapping("/{id}/games")
    public ResponseEntity<GameDTO> deleteGames(@PathVariable int id) {
        gameServiceMongoDB.deleteGames(id);
        return ResponseEntity.noContent().build();
    }
}