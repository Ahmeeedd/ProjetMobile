package tn.esprit.project.models;

import androidx.room.Entity;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(primaryKeys = {"vaccineId", "enfantId"})
public class EnfantVaccine {

    private int vaccineId;
    private long enfantId;
    @TypeConverters(Converter.class)
    private Date dateVaccine;


    public EnfantVaccine(int vaccineId, long enfantId, Date dateVaccine) {
        this.vaccineId = vaccineId;
        this.enfantId = enfantId;
        this.dateVaccine = dateVaccine;

    }public EnfantVaccine() {

    }


    public int getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(int vaccineId) {
        this.vaccineId = vaccineId;
    }

    public long getEnfantId() {
        return enfantId;
    }

    public void setEnfantId(long enfantId) {
        this.enfantId = enfantId;
    }

    public Date getDateVaccine() {
        return dateVaccine;
    }

    public void setDateVaccine(Date dateVaccine) {
        this.dateVaccine = dateVaccine;
    }

}
