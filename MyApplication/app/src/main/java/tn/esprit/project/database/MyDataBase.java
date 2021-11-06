package tn.esprit.project.database;


import android.content.Context;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import tn.esprit.project.DAO.IEnfantDAO;
import tn.esprit.project.DAO.IEnfantVaccineDAO;
import tn.esprit.project.DAO.IVaccineDAO;
import tn.esprit.project.DAO.UserDAO;
import tn.esprit.project.models.Enfant;
import tn.esprit.project.models.EnfantVaccine;
import tn.esprit.project.models.User;
import tn.esprit.project.models.Vaccine;


@Database(entities = {User.class, Vaccine.class, EnfantVaccine.class, Enfant.class},version=4/*,
        autoMigrations = {
                @AutoMigration(from = 2,to = 3)}*/)

//@Database(entities = {User.class},version=3 )

public abstract class MyDataBase extends RoomDatabase {

    private static final String dbName = "db_My_Baby";

    private static MyDataBase instance;

    public abstract UserDAO userdao();

    public abstract IVaccineDAO vaccineDAO();

    public abstract IEnfantDAO enfantDAO();

    public abstract IEnfantVaccineDAO enfantVaccineDAO();



    public static  MyDataBase getDataBase(Context context)

    {
        if (instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),MyDataBase.class,dbName)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}

