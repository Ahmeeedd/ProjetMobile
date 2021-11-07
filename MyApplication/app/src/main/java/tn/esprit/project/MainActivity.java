package tn.esprit.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Intent myintent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView addMed = (TextView) findViewById(R.id.addedId);
        addMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myintent = new Intent(MainActivity.this, Add_Fiche_Medecin_Activity.class);
                startActivity(myintent);
            }
        });

        TextView addV = (TextView) findViewById(R.id.addVaccin);
        addV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, Affiche_List_Vaccine_Activity.class));
            }
        });

        TextView addVaccin = (TextView) findViewById(R.id.addInterVaccin);
        addVaccin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, Add_Vaccine_Activity.class));
            }
        });

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