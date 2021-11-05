package tn.esprit.project.DAO;

import androidx.room.Dao;
import androidx.room.Insert;

import tn.esprit.project.models.Enfant;
import tn.esprit.project.models.EnfantVaccine;

@Dao
public interface IEnfantVaccineDAO {


    @Insert
    public void add(EnfantVaccine e);
}
