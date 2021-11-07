package tn.esprit.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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

    private long enfantId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affecter_vaccine_enfant);

        //


        enfantId=Long.parseLong(getIntent().getStringExtra("enfantId"));

        database = MyDataBase.getDataBase(this);

        //initialisation();

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


        EnfantVaccine enfantVaccine = new EnfantVaccine(vaccine.getVaccineId(),enfantId,new Date());

        try {


            database.enfantVaccineDAO().add(enfantVaccine);

            if (Add_child_vaccine_Activity.enfant != null) {
                Add_child_vaccine_Activity.enfant.getVaccinToDoList().
                        remove(vaccine);
            }
        }catch (SQLiteConstraintException ex){
            Toast.makeText(getApplicationContext(), "Vaccined !", Toast.LENGTH_SHORT).show();
            return;
        }




    }

    public void initialisation(){

        if(database.vaccineDAO().getAllListVaccine().size()!=0){

            database.vaccineDAO().add(new Vaccine(2,"vaccin1-2M"));
            database.vaccineDAO().add(new Vaccine(2,"vaccin2-2M"));
            database.vaccineDAO().add(new Vaccine(3,"vaccin3-3M"));
            database.vaccineDAO().add(new Vaccine(3,"vaccin4-3M"));

        }
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
                Intent myintent=new Intent(Affecter_Vaccine_Enfant_Activity.this,LoginActivity.class);
                startActivity(myintent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}