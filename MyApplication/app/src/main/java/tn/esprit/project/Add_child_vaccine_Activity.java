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
import tn.esprit.project.models.User;
import tn.esprit.project.models.Vaccine;

public class Add_child_vaccine_Activity extends AppCompatActivity {


    //var
    private MyDataBase database;

    private AdapterVaccinEnfant adapterVaccinEnfant;

    private TextView nomPrenomChild;

    private TextView textViewAge;

    private TextView dateNai;

    private TextView textViewHistorique;
    private TextView texViewToDo;

    private List<ModelViewEnfantVaccine> list;
    public static Enfant enfant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child_vaccine);

        texViewToDo =findViewById(R.id.textViewD);

        textViewAge = findViewById(R.id.textViewAge);

        textViewHistorique = findViewById(R.id.textViewHistorique);

        dateNai = findViewById(R.id.textViewDateNaissance);

        database = MyDataBase.getDataBase(this);

        list = new ArrayList<>();

        nomPrenomChild = findViewById(R.id.textViewNomPrenom);


        if (enfant == null) {

            enfant = database.enfantDAO().getById((Long.parseLong(getIntent().getStringExtra("enfantId"))));

        }

        if (enfant != null) {



                List<EnfantVaccine> enfantVaccines = database.enfantVaccineDAO().getByEnfant(enfant.getEnfantId());

                verif(enfantVaccines);

                nomPrenomChild.setText("Name: " + enfant.getPrenom());

                textViewAge.setText("Age:" + String.valueOf(calculAge(enfant.getDate_naiss())) + " Month");

                dateNai.setText("date of birth:" + formatDate(enfant.getDate_naiss()));


               // textViewHistorique.setText("Vaccin History (" + enfantVaccines.size() + ")");

                //------->
                for (EnfantVaccine enfantVaccine : enfantVaccines
                ) {
                    list.add(new ModelViewEnfantVaccine(database.vaccineDAO()
                            .findVaccine(enfantVaccine.getVaccineId()).getDescription()
                            , enfantVaccine.getDateVaccine(), enfantVaccine.getEnfantId()
                            , enfantVaccine.getVaccineId()));

                }





        }


        /*try {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            Date dateD = formatter.parse("2021-09-06");


            Enfant enfant = new Enfant(30, 30, "ali", 30, dateD);

          //   database.enfantDAO().add(enfant);

           // System.out.println("-->size"+database.enfantDAO().getAll().size());

        } catch (ParseException e) { // TODO Auto-generated catch block
            e.printStackTrace();
        }*/


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

                Intent i = new Intent(Add_child_vaccine_Activity.this, Affecter_Vaccine_Enfant_Activity.class);

                i.putExtra("enfantId", String.valueOf(enfant.getEnfantId()));

                startActivity(i);

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

                        //update
                        List<EnfantVaccine> enfantVaccines = database.enfantVaccineDAO().getByEnfant(enfant.getEnfantId());

                        verif(enfantVaccines);


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
            return diff / 30;

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

    public void verif(List<EnfantVaccine> vaccineList) {


        int nb = 0;

        for (Vaccine vaccine : database.vaccineDAO().getAllListVaccine()
        ) {

            if (vaccine.getMonthNumber() <= calculAge(enfant.getDate_naiss())
                    && verifContains(vaccine, vaccineList) == false) {

                nb++;
                enfant.getVaccinToDoList().add(vaccine);
            }

        }

        if (nb != 0) {


            texViewToDo.setText("Vaccin to do :"+nb);

            CharSequence colors[] = new CharSequence[]{"OK"};

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("You have " + nb + " Vaccin to do !");
            builder.setItems(colors, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    builder.setCancelable(true);
                }
            });
            builder.show();


        }


    }

    public boolean verifContains(Vaccine v, List<EnfantVaccine> vaccineList) {


        for (EnfantVaccine enfantVaccine : vaccineList
        ) {

            if (enfantVaccine.getVaccineId() == v.getVaccineId()) {
                return true;
            }

        }

        return false;
    }

}

