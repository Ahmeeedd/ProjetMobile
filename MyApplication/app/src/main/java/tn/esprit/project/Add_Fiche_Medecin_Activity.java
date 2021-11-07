package tn.esprit.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import tn.esprit.project.database.MyDataBase;
import tn.esprit.project.models.Enfant;
import tn.esprit.project.models.FicheMedecin;
import tn.esprit.project.models.User;

public class Add_Fiche_Medecin_Activity extends AppCompatActivity {


    EditText Enom, Eadresse, Etel, Eemail;
    Button aded;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fiche_medecin);
        Button cancel=(Button) findViewById(R.id.btCancel);
        Enom = findViewById(R.id.Enom);
        Eadresse = findViewById(R.id.Eadresse);
        Etel = findViewById(R.id.Etel);
        Eemail = findViewById(R.id.Eemail);
        aded = findViewById(R.id.aded);


        aded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Enomstring = Enom.getText().toString();
                String Eadressestring = Eadresse.getText().toString();
                int Etelstring = Integer.parseInt(Etel.getText().toString());
                String Eemailstring = Eemail.getText().toString();

                if ( Enomstring.isEmpty() ||  Eadressestring.isEmpty()
                        || Etelstring == 0 || Eemailstring.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Some fields are empty !", Toast.LENGTH_SHORT).show();
                } else {

                    FicheMedecin ficheMedecin = new FicheMedecin(Enomstring,Eadressestring,Etelstring,Eemailstring);


                    if (ficheMedecin != null) {
                        MyDataBase db = MyDataBase.getDataBase(getApplicationContext());
                        db.ficheMedcinDAO().add(ficheMedecin);
                        Toast.makeText(getApplicationContext(), "Doctor added successfully !", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "there was some error !", Toast.LENGTH_SHORT).show();
                    }


                    clearInputsFields();

                }


            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent=new Intent(Add_Fiche_Medecin_Activity.this,MainActivity.class);
                startActivity(myintent);
            }
        });

    }


    public void clearInputsFields()
    {
        Enom.getText().clear();
        Eadresse.getText().clear();
        Etel.getText().clear();
        Eemail.getText().clear();

    }


}
