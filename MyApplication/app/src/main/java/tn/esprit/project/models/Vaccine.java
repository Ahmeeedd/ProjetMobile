package tn.esprit.project.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Vaccine {


    @PrimaryKey(autoGenerate = true)
    private int vaccineId;

    private int monthNumber;

    private String description;



    public Vaccine(){

    }

    public Vaccine(int vaccineId, int monthNumber, String description) {
        this.vaccineId = vaccineId;
        this.monthNumber = monthNumber;
        this.description = description;
    }

    public Vaccine(int monthNumber, String description) {
        this.monthNumber = monthNumber;
        this.description = description;
    }

    public int getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(int vaccineId) {
        this.vaccineId = vaccineId;
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public void setMonthNumber(int monthNumber) {
        this.monthNumber = monthNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
