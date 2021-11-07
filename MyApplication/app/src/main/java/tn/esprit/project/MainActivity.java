package tn.esprit.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tn.esprit.project.DAO.UserDAO;
import tn.esprit.project.database.MyDataBase;
import tn.esprit.project.models.Role;
import tn.esprit.project.models.User;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                Intent myintent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(myintent);
                Toast.makeText(this, "logout successfully", Toast.LENGTH_SHORT).show();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }
}