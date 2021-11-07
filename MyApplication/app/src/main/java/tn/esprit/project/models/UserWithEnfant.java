package tn.esprit.project.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserWithEnfant {

    @Embedded
    public User user;
    @Relation(parentColumn = "parentId",entityColumn = "enfantId")
    public List<Enfant> enfants;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Enfant> getEnfants() {
        return enfants;
    }

    public void setEnfants(List<Enfant> enfants) {
        this.enfants = enfants;
    }
}
