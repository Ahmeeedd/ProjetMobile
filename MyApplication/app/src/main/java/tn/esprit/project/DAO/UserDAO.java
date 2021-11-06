package tn.esprit.project.DAO;



import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import tn.esprit.project.models.User;

@Dao
public interface UserDAO {

    @Insert
    void registerUser(User u);

    @Query("SELECT * FROM User where email=(:email) and password=(:password)")
    User loginUser(String email,String password);

    @Query("SELECT * FROM User where email=(:email)")
    User checkUser(String email);

    @Query("Update User set password=(:newpassword)  where email=(:email)")
    public void updatePassword(String email,String newpassword);

    @Delete
    void deleteUser(User u);

    @Query("SELECT profileimage from User where firstname=(:firstname)")
    public byte[] selectProfileImage(String firstname);


    @Query("Update User set email=(:emailtomodify),firstname=(:firstnameTomodify),lastname=(:lastnameTomodify),password=(:passwordTomodify),phonenumber=(:phonenumberTomodify) where userId=(:id)")
    public void updateUserProfile(String emailtomodify,String firstnameTomodify,String lastnameTomodify,String passwordTomodify,String phonenumberTomodify,long id);

    @Query("Update User set profileimage=(:imageprofile) where firstname=(:f)")
    public void updateUserImage(byte[] imageprofile,String f);


    @Query("SELECT * from User where email=(:email)")
    User checkIfUserExists(String email);

    @Query("SELECT * FROM User")
    List<User> getAllUsers();
}
