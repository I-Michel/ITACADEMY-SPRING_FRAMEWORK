package cat.itacademy.barcelonactiva.Michel.S04.T02.N02.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Fruit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String name;
        private int kg;

    public Fruit(String name, int kg) {
        this.name = name;
        this.kg = kg;
    }

    public Fruit() {

    }

    public int getId() {
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
