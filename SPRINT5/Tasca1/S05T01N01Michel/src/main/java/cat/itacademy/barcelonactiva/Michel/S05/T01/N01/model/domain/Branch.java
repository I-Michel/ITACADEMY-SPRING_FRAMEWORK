package cat.itacademy.barcelonactiva.Michel.S05.T01.N01.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk_branchID;
    private String branchName;
    private String branchCountry;

    public Branch(String branchName, String branchCountry) {
        this.branchName = branchName;
        this.branchCountry = branchCountry;
    }

    public Branch() {

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

    public void setPk_branchID(int pk_branchID) { this.pk_branchID = pk_branchID; }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
    public void setBranchCountry(String branchCountry) {
        this.branchCountry = branchCountry;
    }

    @Override
    public String toString() {
        return "ID: " + this.pk_branchID + " - Branch: " + this.branchName + " - Country: " + this.branchCountry + ".";
    }
}
