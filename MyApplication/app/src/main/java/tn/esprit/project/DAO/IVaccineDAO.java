package tn.esprit.project.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import tn.esprit.project.models.Vaccine;

@Dao
public interface IVaccineDAO {

    @Insert
    public void add(Vaccine v);
    @Delete
    public void delete(Vaccine v);

    @Query("select * from Vaccine")
    public List<Vaccine> getAllListVaccine();

    @Query("select * from Vaccine where vaccineId =:idP ")
    public Vaccine findVaccine(int idP);

    @Update
    public void updateVaccin(Vaccine v);

}
