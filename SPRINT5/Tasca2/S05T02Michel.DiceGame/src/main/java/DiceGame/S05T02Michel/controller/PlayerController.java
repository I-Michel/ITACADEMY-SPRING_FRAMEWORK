package DiceGame.S05T02Michel.controller;

import DiceGame.S05T02Michel.model.domain.Player;
import DiceGame.S05T02Michel.model.dto.PlayerDTO;
import DiceGame.S05T02Michel.model.service.impl.PlayerServiceMySQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerServiceMySQL playerServiceMySQL;

    // POST '/players': crea un jugador/a.
    @PostMapping("/")
    public ResponseEntity<PlayerDTO> addPlayer(@RequestBody PlayerDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerServiceMySQL.addPlayer(dto));
    }

    // PUT '/players': modifica el nom del jugador/a.
    @PutMapping("/")
    public ResponseEntity<PlayerDTO> updatePlayerName(@RequestBody PlayerDTO dto) {
        return ResponseEntity.ok(playerServiceMySQL.updatePlayerName(dto));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PlayerDTO> getOnePlayer(@PathVariable int playerId) {
        PlayerDTO gottenDto = playerServiceMySQL.getOnePlayer(playerId);
        return ResponseEntity.ok(gottenDto);
    }

    // GET '/players/': retorna el llistat de tots els jugadors/es del sistema amb el seu percentatge mitjà d’èxits.
    @GetMapping("/")
    public ResponseEntity<List<PlayerDTO>> getAllPlayers() {
        return ResponseEntity.ok(playerServiceMySQL.getAllPlayers());
        // FALTA ARREGLAR WINRATE
    }

    // GET '/players/ranking': retorna el ranking mig de tots els jugadors/es del sistema. És a dir, el percentatge mitjà d’èxits.
    @GetMapping("/ranking")
    public ResponseEntity<PlayerDTO> getAverageWinrate() {
        return ResponseEntity.ok(playerServiceMySQL.getAllPlayers());
        // FALTA ARREGLAR WINRATE
    }

    // GET '/players/ranking/loser': retorna el jugador/a amb pitjor percentatge d’èxit.


    // GET '/players/ranking/winner': retorna el jugador amb millor percentatge d’èxit.


}
