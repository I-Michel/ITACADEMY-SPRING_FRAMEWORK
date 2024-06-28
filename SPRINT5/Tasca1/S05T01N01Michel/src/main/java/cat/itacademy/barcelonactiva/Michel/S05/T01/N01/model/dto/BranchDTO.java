package cat.itacademy.barcelonactiva.Michel.S05.T01.N01.model.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import java.text.Collator;
import java.util.List;
import java.util.Locale;

public class BranchDTO {

    @Id
    private int pk_branchID;
    @NotEmpty(message = "Branch name cannot be empty")
    private String branchName;
    @NotEmpty(message = "Branch country cannot be empty")
    private String branchCountry;
    private String branchType;
    private static final List<String> EU_COUNTRIES = List.of("Austria", "Belgium", "Bulgaria",
            "Croatia", "Republic of Cyprus", "Czech Republic", "Denmark", "Estonia", "Finland",
            "France", "Germany", "Greece", "Hungary", "Ireland", "Italy", "Latvia", "Lithuania",
            "Luxembourg", "Malta", "Netherlands", "Poland", "Portugal", "Romania", "Slovakia",
            "Slovenia", "Spain", "Sweden");

    public BranchDTO(int pk_branchID, String branchName, String branchCountry) {
        this.pk_branchID = pk_branchID;
        this.branchName = branchName;
        this.branchCountry = branchCountry;
        this.branchType = assignType(branchCountry);
    }

    public BranchDTO() {

    }

    public int getPk_branchID() {
        return pk_branchID;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getBranchCountry() {
        return branchCountry;
    }

    public String getBranchType() {
        return branchType;
    }

    public void setPk_branchID(int pk_branchID) {
        this.pk_branchID = pk_branchID;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public void setBranchCountry(String branchCountry) {
        this.branchCountry = branchCountry;
    }

    public String assignType(String branchCountry) {
        if (branchCountry == null) {
            return "Unknown country";
        } else {
            Collator collator = Collator.getInstance(Locale.getDefault());
            collator.setStrength(Collator.PRIMARY);

           boolean isEU = EU_COUNTRIES.stream()
                   .anyMatch(EUcountry -> collator.equals(EUcountry, branchCountry));

            return isEU ? "EU country" : "Non-EU country";
        }
    }

    @Override
    public String toString() {
        return "ID: " + this.pk_branchID + " - Branch: " + this.branchName + " - Country: "
                + this.branchCountry + " - Type: " + this.branchType + ".";
    }
}
