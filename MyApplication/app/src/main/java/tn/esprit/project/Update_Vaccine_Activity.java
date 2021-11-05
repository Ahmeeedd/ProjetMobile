package tn.esprit.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tn.esprit.project.database.MyDataBase;
import tn.esprit.project.models.Vaccine;

public class Update_Vaccine_Activity extends AppCompatActivity {


    private Button btnAdd;
    private Button btnCancel;
    private EditText editTextDescription;
    private EditText editTextNumberMonth;
    //var
    private MyDataBase database;
    private Vaccine vaccine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_vaccine);


        database = MyDataBase.getDataBase(this);

        btnAdd = findViewById(R.id.btAdd);
        btnCancel = findViewById(R.id.btCancel);
        editTextDescription = findViewById(R.id.editTextDescriptionVaccine);
        editTextNumberMonth = findViewById(R.id.editTextMonthNumber);

        vaccine = database.vaccineDAO().findVaccine(Integer.parseInt(getIntent().getStringExtra("vaccinid")));

        if(vaccine!=null){
            editTextDescription.setText(vaccine.getDescription());
            editTextNumberMonth.setText(String.valueOf(vaccine.getMonthNumber()));
        }

        btnAdd.setOnClickListener(View -> {

            vaccine.setDescription(editTextDescription.getText().toString());

            vaccine.setMonthNumber(Integer.parseInt(editTextNumberMonth.getText().toString()));

            database.vaccineDAO().updateVaccin(vaccine);

            Toast.makeText(getApplicationContext(), "Vaccine successfully updated !", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(Update_Vaccine_Activity.this, Affiche_List_Vaccine_Activity.class));


        });

        btnCancel.setOnClickListener(View -> {

            editTextDescription.getText().clear();
            editTextNumberMonth.getText().clear();


        });
    }
}