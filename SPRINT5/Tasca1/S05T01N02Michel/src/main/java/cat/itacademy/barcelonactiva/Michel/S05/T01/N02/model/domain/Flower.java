package cat.itacademy.barcelonactiva.Michel.S05.T01.N02.model.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "flower")
public class Flower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_flower_id")
    private int pk_flowerID;

    @NotEmpty(message = "Flower name cannot be empty")
    @Column(name = "flower_name", length = 45, nullable = false)
    private String flowerName;

    @NotEmpty(message = "Flower country cannot be empty")
    @Column(name = "flower_country", length = 45, nullable = false)
    private String flowerCountry;

    public Flower(String flowerName, String flowerCountry) {
        this.flowerName = flowerName;
        this.flowerCountry = flowerCountry;
    }

    public Flower() {

    }

    public int getPk_flowerID() {
        return pk_flowerID;
    }
    public String getFlowerName() {
        return flowerName;
    }
    public String getFlowerCountry() {
        return flowerCountry;
    }

    public void setPk_flowerID(int pk_flowerID) {
        this.pk_flowerID = pk_flowerID;
    }
    public void setFlowerName(String flowerName) {
        this.flowerName = flowerName;
    }
    public void setFlowerCountry(String flowerCountry) {
        this.flowerCountry = flowerCountry;
    }

    @Override
    public String toString() {
        return "ID: " + this.pk_flowerID + " - Flower: " + this.flowerName + " - Country: " + this.flowerCountry + ".";
    }
}
