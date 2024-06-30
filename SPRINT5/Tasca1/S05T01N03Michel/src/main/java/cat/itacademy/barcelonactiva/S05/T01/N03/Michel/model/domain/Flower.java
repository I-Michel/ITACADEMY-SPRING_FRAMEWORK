package cat.itacademy.barcelonactiva.S05.T01.N03.Michel.model.domain;

public class Flower {

    private int pk_flowerID;
    private String flowerName;
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