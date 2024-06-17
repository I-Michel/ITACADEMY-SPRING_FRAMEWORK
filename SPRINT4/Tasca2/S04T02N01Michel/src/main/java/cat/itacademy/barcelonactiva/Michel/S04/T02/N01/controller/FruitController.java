package cat.itacademy.barcelonactiva.Michel.S04.T02.N01.controller;

import cat.itacademy.barcelonactiva.Michel.S04.T02.N01.exception.FruitNotFoundException;
import cat.itacademy.barcelonactiva.Michel.S04.T02.N01.model.domain.Fruit;
import cat.itacademy.barcelonactiva.Michel.S04.T02.N01.model.service.impl.FruitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fruit")
public class FruitController {

    @Autowired
    private FruitServiceImpl fruitService;

    // http://localhost:8080/fruita/add
    @PostMapping("/add")
    public ResponseEntity<Fruit> addFruit(@RequestBody Fruit fruit) {
        Fruit addedFruit = fruitService.addFruit(fruit);
        return ResponseEntity.ok(addedFruit);
    }

    // http://localhost:8080/fruit/update
    @PutMapping("/update")
    public ResponseEntity<Fruit> updateFruit(@RequestBody Fruit fruit) throws FruitNotFoundException {
        Fruit updatedFruit = fruitService.updateFruit(fruit);
        return ResponseEntity.ok(updatedFruit);
    }

    // http://localhost:8080/fruit/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Fruit> deleteFruit(@PathVariable int id) throws FruitNotFoundException {
        Fruit deletedFruit = fruitService.deleteFruit(id);
        return ResponseEntity.ok(deletedFruit);
    }


    // http://localhost:8080/fruit/getOne/{id}
    @GetMapping("/getOne/{id}")
    public ResponseEntity<Fruit> getOneFruit(@PathVariable int id) throws FruitNotFoundException {
        Fruit gettedFruit = fruitService.getOneFruit(id);
        return ResponseEntity.ok(gettedFruit);
    }

    // http://localhost:8080/fruit/getAll
    @GetMapping("getAll")
    public ResponseEntity<List<Fruit>> getAllFruit(){
        return ResponseEntity.ok(fruitService.getAllFruit());
    }
}
