package tn.esprit.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import tn.esprit.project.database.MyDataBase;
import tn.esprit.project.models.Vaccine;

public class Affiche_List_Vaccine_Activity extends AppCompatActivity {


    //var
    private MyDataBase database;
    private List<Vaccine> vaccineList;
    private AdapterVaccine vAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_list_vaccine);

        //
        database = MyDataBase.getDataBase(this);
        vaccineList=database.vaccineDAO().getAllListVaccine();

        RecyclerView recyclerView=findViewById(R.id.listVaccinChild);
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


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButtonAdd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Affiche_List_Vaccine_Activity.this, Add_Vaccine_Activity.class));

            }
        });


    }
    private void showActionsDialog(final int position) {
        CharSequence colors[] = new CharSequence[]{"Edit", "Delete"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose option");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    showEdit(position);
                } else {
                    delete(position);
                }
            }
        });
        builder.show();
    }

    public void delete(int position){

        database.vaccineDAO().delete(this.vaccineList.get(position));

        vaccineList.remove(position);
        vAdapter.notifyItemRemoved(position);
    }

    public void showEdit(int position){

        Intent i = new Intent(Affiche_List_Vaccine_Activity.this, Update_Vaccine_Activity.class);

        i.putExtra("vaccinid",String.valueOf(this.vaccineList.get(position).getVaccineId()));

        startActivity(i);

    }
}