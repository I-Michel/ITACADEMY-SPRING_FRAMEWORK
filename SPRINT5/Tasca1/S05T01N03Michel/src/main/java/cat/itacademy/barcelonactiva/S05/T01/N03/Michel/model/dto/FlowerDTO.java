package cat.itacademy.barcelonactiva.S05.T01.N03.Michel.model.dto;

import java.text.Collator;
import java.util.List;
import java.util.Locale;

public class FlowerDTO {
    private int pk_flowerID;
    private String flowerName;
    private String flowerCountry;
    private String flowerType;
    private static final List<String> EU_COUNTRIES = List.of("Austria", "Belgium", "Bulgaria",
            "Croatia", "Republic of Cyprus", "Czech Republic", "Denmark", "Estonia", "Finland",
            "France", "Germany", "Greece", "Hungary", "Ireland", "Italy", "Latvia", "Lithuania",
            "Luxembourg", "Malta", "Netherlands", "Poland", "Portugal", "Romania", "Slovakia",
            "Slovenia", "Spain", "Sweden");

    public FlowerDTO(int pk_flowerID, String flowerName, String flowerCountry) {
        this.pk_flowerID = pk_flowerID;
        this.flowerName = flowerName;
        this.flowerCountry = flowerCountry;
        this.flowerType = assignType(flowerCountry);
    }

    public FlowerDTO() {

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
    public String getFlowerType() {
        return flowerType;
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
    public void setFlowerType(String flowerType) {
        this.flowerType = flowerType;
    }

    public String assignType(String flowerCountry) {
        if (flowerCountry == null) {
            return "Unknown country";
        } else {
            Collator collator = Collator.getInstance(Locale.getDefault());
            collator.setStrength(Collator.PRIMARY);

           boolean isEU = EU_COUNTRIES.stream()
                   .anyMatch(EUcountry -> collator.equals(EUcountry, flowerCountry));

            return isEU ? "EU country" : "Non-EU country";
        }
    }

    @Override
    public String toString() {
        return "ID: " + this.pk_flowerID + " - Flower: " + this.flowerName + " - Country: "
                + this.flowerCountry + " - Region: " + this.flowerType + ".";
    }
}