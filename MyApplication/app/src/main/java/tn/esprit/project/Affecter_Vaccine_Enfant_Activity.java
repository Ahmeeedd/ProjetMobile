package tn.esprit.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import tn.esprit.project.database.MyDataBase;
import tn.esprit.project.models.Enfant;
import tn.esprit.project.models.EnfantVaccine;
import tn.esprit.project.models.Vaccine;

public class Affecter_Vaccine_Enfant_Activity extends AppCompatActivity {


    private MyDataBase database;
    private List<Vaccine> vaccineList;
    private AdapterVaccine vAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affecter_vaccine_enfant);



        database = MyDataBase.getDataBase(this);
        vaccineList=database.vaccineDAO().getAllListVaccine();

        RecyclerView recyclerView=findViewById(R.id.listVaccinToChild);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));


        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));

        vAdapter=new AdapterVaccine(this,vaccineList);

        recyclerView.setAdapter(vAdapter);

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





    }


    private void showActionsDialog(final int position) {
        CharSequence colors[] = new CharSequence[]{"Add","Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Do you wont add  this vaccin ?");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                if (which == 0) {



                    AffectToChild(position);

                    startActivity(new Intent(Affecter_Vaccine_Enfant_Activity.this, Add_child_vaccine_Activity.class));
                } else {
                    builder.setCancelable(true);
                }


            }
        });
        builder.show();
    }

    public void AffectToChild(int pos){

        Vaccine vaccine = vaccineList.get(pos);


//----->

        EnfantVaccine enfantVaccine = new EnfantVaccine(vaccine.getVaccineId(),1,new Date());

        database.enfantVaccineDAO().add(enfantVaccine);




    }

}