package tn.esprit.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tn.esprit.project.database.MyDataBase;
import tn.esprit.project.models.Enfant;
import tn.esprit.project.models.EnfantVaccine;
import tn.esprit.project.models.ModelViewEnfantVaccine;

public class Add_child_vaccine_Activity extends AppCompatActivity {


    //var
    private MyDataBase database;

    private AdapterVaccinEnfant adapterVaccinEnfant;

    private TextView nomPrenomChild;

    List<ModelViewEnfantVaccine> list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child_vaccine);


        database = MyDataBase.getDataBase(this);
        list= new ArrayList<>();

        //------->
        for (EnfantVaccine enfantVaccine:database.enfantVaccineDAO().getByEnfant(1)
             ) {


            list.add(new ModelViewEnfantVaccine(database.vaccineDAO()
                    .findVaccine(enfantVaccine.getVaccineId()).getDescription()
                    ,enfantVaccine.getDateVaccine(),enfantVaccine.getEnfantId()
                    ,enfantVaccine.getVaccineId()));
            
        }

       /* List<ModelViewEnfantVaccine> list = new ArrayList<>();
        list.add(new ModelViewEnfantVaccine("desc1",new Date()));
        list.add(new ModelViewEnfantVaccine("desc2",new Date()));
        list.add(new ModelViewEnfantVaccine("desc3",new Date()));
        list.add(new ModelViewEnfantVaccine("desc4",new Date()));
        list.add(new ModelViewEnfantVaccine("desc5",new Date()));*/

        Enfant enfant = new Enfant(30, 30, "ali", 30, new Date());

        //database.enfantDAO().add(enfant);

        //System.out.println("--->"+database.enfantDAO().getEnfantWithVaccinList().size());


        nomPrenomChild = findViewById(R.id.textViewNomPrenom);

        nomPrenomChild.setText("oussema ");






        RecyclerView recyclerView=findViewById(R.id.listVaccinToChild);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));


        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));

        adapterVaccinEnfant=new AdapterVaccinEnfant(this,list);

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

                    if(modelViewEnfantVaccine!=null){


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
}