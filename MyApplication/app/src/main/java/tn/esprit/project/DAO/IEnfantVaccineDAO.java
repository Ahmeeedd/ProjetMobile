package tn.esprit.project.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import tn.esprit.project.models.Enfant;
import tn.esprit.project.models.EnfantVaccine;

@Dao
public interface IEnfantVaccineDAO {


    @Insert
    public void add(EnfantVaccine e);

    @Query("select * from EnfantVaccine where enfantId=:idEnfant")
    public List<EnfantVaccine> getByEnfant(long idEnfant);

    @Query("delete from EnfantVaccine where enfantId=:idE and vaccineId=:idV ")
    public  void delete(long idE,int idV);
}
