package cat.itacademy.barcelonactiva.Michel.S04.T02.N03.model.domain;

import jakarta.persistence.Id;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fruit")
public class Fruit {
    @Id
        private ObjectId id;
        private String name;
        private int kg;

    public Fruit(String name, int kg) {
        this.name = name;
        this.kg = kg;
    }

    public Fruit() {

    }

    public ObjectId getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getKg() {
        return kg;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setKg(int kg) {
        this.kg = kg;
    }

    @Override
    public String toString() {
        return "ID: " + id + " - Fruit: " + name + " - Quantity: " + kg + " kg.";
    }
}
