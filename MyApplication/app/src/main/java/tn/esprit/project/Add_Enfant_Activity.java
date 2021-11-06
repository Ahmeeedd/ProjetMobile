package tn.esprit.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import java.util.Locale;

import tn.esprit.project.database.MyDataBase;
import tn.esprit.project.models.Enfant;
import tn.esprit.project.models.User;

public class Add_Enfant_Activity extends AppCompatActivity {



    final Calendar myCalendar = Calendar.getInstance();
    EditText Epoids, Etaille,Eprenom,Eage,Edate_naiss;
    Button submitbtn;
    public static User userconnected;

 //   MyDataBase db = MyDataBase.getDataBase(getApplicationContext());



    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_enfant);

        Epoids =  findViewById(R.id.Epoids);
        Etaille= findViewById(R.id.Etaille);
        Eprenom = findViewById(R.id.Eprenom);
        Eage =  findViewById(R.id.Eage);
        submitbtn =  findViewById(R.id.idaddnew);
        Edate_naiss= findViewById(R.id.date_naiss);


        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double Epoidsstring = Double.parseDouble(Epoids.getText().toString());
                int Etaillestring = Integer.parseInt(Etaille.getText().toString());
                String Eprenomstring = Eprenom.getText().toString();
                int Eagestring = Integer.parseInt(Eage.getText().toString());
                String Edate_naissstring = Edate_naiss.getText().toString();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


                try {
                    Date date = formatter.parse(Edate_naissstring);

                    if (Epoidsstring == 0.0 || Etaillestring == 0 || Eprenomstring.isEmpty()
                            || Eagestring == 0 || Edate_naissstring.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Some fields are empty !", Toast.LENGTH_SHORT).show();
                    } else {

                        Enfant enfant = new Enfant((double) Epoidsstring, (int) Etaillestring, Eprenomstring, Eagestring, date,(int)userconnected.getUserId());


                        if (enfant != null) {
                            MyDataBase db = MyDataBase.getDataBase(getApplicationContext());
                            db.enfantDAO().add(enfant);
                            Toast.makeText(getApplicationContext(), "Enfant added successfully !", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getApplicationContext(), "there was some error !", Toast.LENGTH_SHORT).show();
                        }


                        clearInputsFields();

                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }  });


        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            }

        };




        Edate_naiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimeDialog(Edate_naiss);
            }
        });


    }

    private void showDateTimeDialog(final EditText date_time_in) {
        final Calendar calendar=Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar.set(Calendar.MINUTE,minute);

                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");

                        date_time_in.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };

                new TimePickerDialog(Add_Enfant_Activity.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
            }
        };

        new DatePickerDialog(Add_Enfant_Activity.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    public void clearInputsFields()
    {
        Epoids.getText().clear();
        Etaille.getText().clear();
        Eprenom.getText().clear();
        Eage.getText().clear();
        Edate_naiss.getText().clear();

    }


}