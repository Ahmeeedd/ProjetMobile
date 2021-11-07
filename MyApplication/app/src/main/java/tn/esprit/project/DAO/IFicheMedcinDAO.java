package tn.esprit.project.DAO;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import tn.esprit.project.models.Enfant;
import tn.esprit.project.models.EnfantWithVaccin;
import tn.esprit.project.models.FicheMedecin;


@Dao
public interface IFicheMedcinDAO {

    @Insert
    public void add(FicheMedecin f);

    @Query("select * from fichemedecin where ficheId=:id")
    public FicheMedecin getById(long id);

    @Query("select * from fichemedecin")
    public List<FicheMedecin> getAll();

}
