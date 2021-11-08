package tn.esprit.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import tn.esprit.project.database.MyDataBase;
import tn.esprit.project.models.Enfant;
import tn.esprit.project.models.FicheMedecin;

public class Affiche_Fiche_Medecin_Activity extends AppCompatActivity {


    RecyclerView ficheMedcinRecyclerView;
    TextView txtnom ,txtemail, txtadresse,txttelephone;
    ImageView ivmedecin;
    ArrayList<FicheMedecin> listemedecins;
    FicheMedecinAdapter medecinAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_fiche_medecin);



        ficheMedcinRecyclerView = findViewById(R.id.fichemedecinRecyclerView);
        txtnom = findViewById(R.id.txt_name);
        txtemail = findViewById(R.id.txtemail);
        txtadresse = findViewById(R.id.txt_adresse);
        txttelephone = findViewById(R.id.txttelephone);



        listemedecins = getFichesMedecin();

        medecinAdapter = new FicheMedecinAdapter(Affiche_Fiche_Medecin_Activity.this,getApplicationContext(),listemedecins);

        ficheMedcinRecyclerView.setAdapter(medecinAdapter);

        ficheMedcinRecyclerView.setLayoutManager(new LinearLayoutManager(Affiche_Fiche_Medecin_Activity.this));

    }


    public ArrayList<FicheMedecin> getFichesMedecin()
    {
        MyDataBase db = MyDataBase.getDataBase(getApplicationContext());

        return (ArrayList<FicheMedecin>) db.ficheMedcinDAO().getAll();
    }
}