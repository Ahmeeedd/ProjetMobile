package tn.esprit.project.models;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class EnfantWithVaccin {


    @Embedded
    public Enfant enfant;


    @Relation(
            parentColumn = "enfantId",
            entityColumn = "vaccineId",
            associateBy = @Junction(EnfantVaccine.class)
    )
    public List<Vaccine> vaciList;


    public Enfant getEnfant() {
        return enfant;
    }

    public void setEnfant(Enfant enfant) {
        this.enfant = enfant;
    }

    public List<Vaccine> getVaciList() {
        return vaciList;
    }

    public void setVaciList(List<Vaccine> vaciList) {
        this.vaciList = vaciList;
    }
}
