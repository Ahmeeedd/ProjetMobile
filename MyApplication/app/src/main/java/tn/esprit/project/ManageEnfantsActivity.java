package tn.esprit.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import tn.esprit.project.database.MyDataBase;
import tn.esprit.project.models.Enfant;
import tn.esprit.project.models.User;

public class ManageEnfantsActivity extends AppCompatActivity {



    public static User userconnected;
    RecyclerView enfantRecyclerview;
    TextView txtnom ,txtpoid, txttaille;
    ImageView ivenfant;
    ArrayList<Enfant> listenfants;
    EnfantsManageAdapter enfantadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_enfants);

        enfantRecyclerview = findViewById(R.id.enfant_recycler_view);
        txtnom = findViewById(R.id.txt_name);
        txtpoid = findViewById(R.id.txt_weight);
        txttaille = findViewById(R.id.txtheight);

        listenfants = getEnfant((int)userconnected.getUserId());

        enfantadapter = new EnfantsManageAdapter(ManageEnfantsActivity.this,getApplicationContext(),listenfants);

        enfantRecyclerview.setAdapter(enfantadapter);

        enfantRecyclerview.setLayoutManager(new LinearLayoutManager(ManageEnfantsActivity.this));


        enfantRecyclerview.addOnItemTouchListener(new RecyclerTouchListener(this,
                enfantRecyclerview, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {



            }

            @Override
            public void onLongClick(View view, int position) {

                showActionsDialog(position);

            }
        }));

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }



    public ArrayList<Enfant> getEnfant(int id)
    {
        MyDataBase db = MyDataBase.getDataBase(getApplicationContext());

        return (ArrayList<Enfant>) db.enfantDAO().getAllEnfantbyUser(id);
    }

    private void showActionsDialog(final int position) {
        CharSequence colors[] = new CharSequence[]{"Add","Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Do you wont add a vaccin to this child ?");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                if (which == 0) {

                    ShowAddVaccin(position);

                } else {
                    builder.setCancelable(true);
                }


            }
        });
        builder.show();
    }

    public void ShowAddVaccin(int position){


        Intent i = new Intent(ManageEnfantsActivity.this, Add_child_vaccine_Activity.class);

        i.putExtra("enfantId",String.valueOf(this.listenfants.get(position).getEnfantId()));

        startActivity(i);

    }
}
