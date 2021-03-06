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

import tn.esprit.project.database.MyDataBase;
import tn.esprit.project.models.Vaccine;

public class Add_Vaccine_Activity extends AppCompatActivity {


    //var
    private MyDataBase database;


    //widgets
    private Button btnAdd;
    private Button btnCancel;
    private EditText editTextDescription;
    private EditText editTextNumberMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vaccine);


        database = MyDataBase.getDataBase(this);

        btnAdd = findViewById(R.id.btAdd);
        btnCancel = findViewById(R.id.btCancel);
        editTextDescription = findViewById(R.id.editTextDescriptionVaccine);
        editTextNumberMonth = findViewById(R.id.editTextMonthNumber);

        btnAdd.setOnClickListener(View -> {

            if(editTextNumberMonth.getText().toString().isEmpty() && editTextDescription.getText().toString().isEmpty()){

                Toast.makeText(getApplicationContext(), "Fields are empty !", Toast.LENGTH_SHORT).show();

            }else {

                Vaccine vaccine = new Vaccine(Integer.parseInt(editTextNumberMonth.getText().toString()), editTextDescription.getText().toString());
                database.vaccineDAO().add(vaccine);


                Toast.makeText(getApplicationContext(), "Vaccine successfully registered !", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(Add_Vaccine_Activity.this, Affiche_List_Vaccine_Activity.class));

            }

        });

        btnCancel.setOnClickListener(View -> {

            editTextDescription.getText().clear();
            editTextNumberMonth.getText().clear();


        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu_logout, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                Intent myintent=new Intent(Add_Vaccine_Activity.this,LoginActivity.class);
                startActivity(myintent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}