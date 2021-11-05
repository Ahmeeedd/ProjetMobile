package tn.esprit.project.models;

import java.util.Date;

public class ModelViewEnfantVaccine {

    private  String description;
    private Date dateCreation;

    private  long idEnfant;
    private int idVaccin;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public long getIdEnfant() {
        return idEnfant;
    }

    public void setIdEnfant(int idEnfant) {
        this.idEnfant = idEnfant;
    }

    public int getIdVaccin() {
        return idVaccin;
    }

    public void setIdVaccin(int idVaccin) {
        this.idVaccin = idVaccin;
    }

    public ModelViewEnfantVaccine(String description, Date dateCreation,long idEnfant,int idVaccin) {
        this.description = description;
        this.dateCreation = dateCreation;
        this.idEnfant=idEnfant;
        this.idVaccin=idVaccin;
    }
}
