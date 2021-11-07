package tn.esprit.project;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import tn.esprit.project.DAO.IEnfantDAO;
import tn.esprit.project.database.MyDataBase;
import tn.esprit.project.models.Enfant;
import tn.esprit.project.models.EnfantVaccine;
import tn.esprit.project.models.ModelViewEnfantVaccine;

public class Add_child_vaccine_Activity extends AppCompatActivity {


    //var
    private MyDataBase database;

    private AdapterVaccinEnfant adapterVaccinEnfant;

    private TextView nomPrenomChild;

    private TextView textViewAge;

    private  TextView dateNai;

    List<ModelViewEnfantVaccine> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child_vaccine);


        textViewAge = findViewById(R.id.textViewAge);

        dateNai = findViewById(R.id.textViewDateNaissance);

        database = MyDataBase.getDataBase(this);
        list = new ArrayList<>();

        // --->


        try {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            Date dateD = formatter.parse("2021-09-06");


            Enfant enfant = new Enfant(30, 30, "ali", 30, dateD);

          //   database.enfantDAO().add(enfant);

           // System.out.println("-->size"+database.enfantDAO().getAll().size());

        } catch (ParseException e) { // TODO Auto-generated catch block
            e.printStackTrace();
        }


        //------->
        for (EnfantVaccine enfantVaccine : database.enfantVaccineDAO().getByEnfant(1)
        ) {


            list.add(new ModelViewEnfantVaccine(database.vaccineDAO()
                    .findVaccine(enfantVaccine.getVaccineId()).getDescription()
                    , enfantVaccine.getDateVaccine(), enfantVaccine.getEnfantId()
                    , enfantVaccine.getVaccineId()));

        }

       /* List<ModelViewEnfantVaccine> list = new ArrayList<>();
        list.add(new ModelViewEnfantVaccine("desc1",new Date()));
        list.add(new ModelViewEnfantVaccine("desc2",new Date()));
        list.add(new ModelViewEnfantVaccine("desc3",new Date()));
        list.add(new ModelViewEnfantVaccine("desc4",new Date()));
        list.add(new ModelViewEnfantVaccine("desc5",new Date()));*/


        //System.out.println("--->"+database.enfantDAO().getEnfantWithVaccinList().size());


        nomPrenomChild = findViewById(R.id.textViewNomPrenom);

        nomPrenomChild.setText("oussema ");


        //intialisation

        Enfant enfant = database.enfantDAO().getById(1);

        if(enfant!=null){

            textViewAge.setText("Age:" + String.valueOf(calculAge(enfant.getDate_naiss()))+" Month");

            dateNai.setText("date of birth:"+formatDate(enfant.getDate_naiss()));

        }




        RecyclerView recyclerView = findViewById(R.id.listVaccinToChild);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));

        adapterVaccinEnfant = new AdapterVaccinEnfant(this, list);

        recyclerView.setAdapter(adapterVaccinEnfant);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {


            }

            @Override
            public void onLongClick(View view, int position) {

                showActionsDialog(position);
            }
        }));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButtonAddVaccinE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Add_child_vaccine_Activity.this, Affecter_Vaccine_Enfant_Activity.class));

            }
        });


    }

    private void showActionsDialog(final int position) {
        CharSequence colors[] = new CharSequence[]{"Delete", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you wont to delete this vaccine ?");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    ModelViewEnfantVaccine modelViewEnfantVaccine = list.get(position);

                    if (modelViewEnfantVaccine != null) {


                        database.enfantVaccineDAO().delete(modelViewEnfantVaccine.getIdEnfant(),
                                modelViewEnfantVaccine.getIdVaccin());

                        list.remove(position);

                        adapterVaccinEnfant.notifyItemRemoved(position);


                    }
                } else {
                    builder.setCancelable(true);
                }
            }
        });
        builder.show();
    }





    public long calculAge(Date d) {


        try {

            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.FRANCE);

            String dSystem = sdf.format(new Date());

            String dOfBirth = sdf.format(d);

            Date firstDate = sdf.parse(dOfBirth);
            Date secondDate = sdf.parse(dSystem);



            long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
             return  diff /30 ;

        } catch (ParseException e) { // TODO Auto-generated catch block
            return 0;
        }

    }

    private String formatDate(Date datep) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

            return fmt.format(datep);
        } catch (Exception e) {

            return "--:--";

        }
    }


}