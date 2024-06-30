package cat.itacademy.barcelonactiva.S05.T01.N03.Michel.controller;

import cat.itacademy.barcelonactiva.S05.T01.N03.Michel.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.S05.T01.N03.Michel.model.service.impl.FlowerServiceImpl;
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

    @PostMapping("/add")
    public ResponseEntity<FlowerDTO> addFlower(@RequestBody FlowerDTO dto) {
        FlowerDTO addedDto = flowerService.addFlower(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedDto);
    }

    @PutMapping("/update")
    public ResponseEntity<FlowerDTO> updateFlower(@RequestBody FlowerDTO dto) {
        FlowerDTO updatedDto = flowerService.updateFlower(dto);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<FlowerDTO> deleteFlower(@PathVariable int id) {
        flowerService.deleteFlower(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<FlowerDTO> getOneFlower(@PathVariable int id) {
        FlowerDTO gottenDto = flowerService.getOneFlower(id);
        return ResponseEntity.ok(gottenDto);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<FlowerDTO>> getAllFlowers() {
        return ResponseEntity.ok(flowerService.getAllFlowers());
    }
}