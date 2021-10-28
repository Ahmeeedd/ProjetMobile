package tn.esprit.project.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import tn.esprit.project.DAO.UserDAO;
import tn.esprit.project.models.User;


@Database(entities = {User.class},version=1 )

public abstract class MyDataBase extends RoomDatabase {

    private static final String dbName = "db_My_Baby";

    private static MyDataBase instance;

    public abstract UserDAO userdao();

    public static  MyDataBase getDataBase(Context context)

    {
        if (instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),MyDataBase.class,dbName)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}

