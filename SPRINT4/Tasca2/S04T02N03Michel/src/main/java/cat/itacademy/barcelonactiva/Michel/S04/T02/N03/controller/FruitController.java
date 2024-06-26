package cat.itacademy.barcelonactiva.Michel.S04.T02.N03.controller;

import cat.itacademy.barcelonactiva.Michel.S04.T02.N03.model.domain.Fruit;
import cat.itacademy.barcelonactiva.Michel.S04.T02.N03.model.service.impl.FruitServiceImpl;
import org.bson.types.ObjectId;
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
    public ResponseEntity<Fruit> updateFruit(@RequestBody Fruit fruit) {
        Fruit updatedFruit = fruitService.updateFruit(fruit);
        return ResponseEntity.ok(updatedFruit);
    }

    // http://localhost:8080/fruit/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Fruit> deleteFruit(@PathVariable ObjectId id) {
        Fruit deletedFruit = fruitService.deleteFruit(id);
        return ResponseEntity.ok(deletedFruit);
    }


    // http://localhost:8080/fruit/getOne/{id}
    @GetMapping("/getOne/{id}")
    public ResponseEntity<Fruit> getOneFruit(@PathVariable ObjectId id) {
        Fruit gottenFruit = fruitService.getOneFruit(id);
        return ResponseEntity.ok(gottenFruit);
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
