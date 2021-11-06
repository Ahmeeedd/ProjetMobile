package tn.esprit.project.DAO;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import tn.esprit.project.models.Enfant;
import tn.esprit.project.models.EnfantWithVaccin;


@Dao
public interface IEnfantDAO {

    @Insert
    public void add(Enfant e);

    @Transaction
    @Query("SELECT * FROM enfant")
    public List<EnfantWithVaccin> getEnfantWithVaccinList();

    @Query("select * from enfant where enfantId=:id")
    public Enfant getById(long id);

    @Query("select * from enfant")
    public List<Enfant> getAll();


}
