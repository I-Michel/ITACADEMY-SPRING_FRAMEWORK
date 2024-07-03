package cat.itacademy.barcelonactiva.Michel.S05.T01.N02.controller;

import cat.itacademy.barcelonactiva.Michel.S05.T01.N02.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.Michel.S05.T01.N02.model.service.impl.FlowerServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/flower")
public class FlowerController {

    @Autowired
    private FlowerServiceImpl flowerService;

    @Operation(summary = "Add new flower")
    @PostMapping("/add")
    public ResponseEntity<FlowerDTO> addFlower(@RequestBody FlowerDTO dto) {
        FlowerDTO addedDto = flowerService.addFlower(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedDto);
    }

    @Operation(summary = "Update flower")
    @PutMapping("/update")
    public ResponseEntity<FlowerDTO> updateFlower(@RequestBody FlowerDTO dto) {
        FlowerDTO updatedDto = flowerService.updateFlower(dto);
        return ResponseEntity.ok(updatedDto);
    }

    @Operation(summary = "Delete flower by ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<FlowerDTO> deleteFlower(@PathVariable int id) {
        flowerService.deleteFlower(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get flower details by ID")
    @GetMapping("/getOne/{id}")
    public ResponseEntity<FlowerDTO> getOneFlower(@PathVariable int id) {
        FlowerDTO gottenDto = flowerService.getOneFlower(id);
        return ResponseEntity.ok(gottenDto);
    }

    @Operation(summary = "Get all flowers")
    @GetMapping("getAll")
    public ResponseEntity<List<FlowerDTO>> getAllFlowers() {
        return ResponseEntity.ok(flowerService.getAllFlowers());
    }
}