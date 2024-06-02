package cat.itacademy.barcelonactiva.Michel.S04.T02.N01.controllers;

import cat.itacademy.barcelonactiva.Michel.S04.T02.N01.model.domain.Fruit;
import cat.itacademy.barcelonactiva.Michel.S04.T02.N01.model.services.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fruit")
public class FruitController {

    @Autowired
    private FruitService fruitService;

    // http://localhost:8080/fruita/add
    @PostMapping("/add")
    public ResponseEntity<Fruit> addFruit(@RequestBody Fruit fruit) {
        Fruit addedFruit = fruitService.addFruit(fruit);
        return ResponseEntity.ok(addedFruit);
    }

    // http://localhost:8080/fruit/update
    @PutMapping("/update")
    public ResponseEntity<Fruit> updateFruit(@RequestBody Fruit fruit) {
        Fruit updatedFruit = fruitService.updateFruit(fruit);
        return ResponseEntity.ok(updatedFruit);
    }

    // http://localhost:8080/fruit/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Fruit> deleteFruit(@PathVariable int id) {
        Fruit deletedFruit = fruitService.deleteFruit(id);
        return ResponseEntity.ok(deletedFruit);
    }


    // http://localhost:8080/fruit/getOne/{id}
    @GetMapping("/getOne/{id}")
    public ResponseEntity<Fruit> getOneFruit(@PathVariable int id) {
        Fruit gettedFruit = fruitService.getOneFruit(id);
        return ResponseEntity.ok(gettedFruit);
    }

    // http://localhost:8080/fruit/getAll
    @GetMapping("getAll")
    public ResponseEntity<List<Fruit>> getAllFruit(){
        return ResponseEntity.ok(fruitService.getAllFruit());
    }

    /* El Controller maneja solicitudes HTTP relaciondas con Fruit y gestiona la interacción entre el cliente y
     * la lógica de negocio, delegando la responsabilidad al Service. Interpreta los datos del cliente, invoca
     * los métodos correspondientes del Service y devuelve las respuestas al cliente. Es el punto de entrada
     * a la aplicación desde el exterior, ya que recibe las solicitudes HTTP y las enruta donde corresponde */
}
