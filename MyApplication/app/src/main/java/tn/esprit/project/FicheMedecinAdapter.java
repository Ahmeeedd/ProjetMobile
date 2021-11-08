package tn.esprit.project;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tn.esprit.project.models.Enfant;
import tn.esprit.project.models.FicheMedecin;

public class FicheMedecinAdapter extends  RecyclerView.Adapter<FicheMedecinAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList<FicheMedecin> enfantslist;
    private int pos;

    public FicheMedecinAdapter(Activity activity, Context context, ArrayList<FicheMedecin> liste) {
        this.activity = activity;
        this.context = context;
        this.enfantslist = liste;
    }


    @NonNull
    @Override
    public FicheMedecinAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.medecin_row, parent, false);
        return new FicheMedecinAdapter.MyViewHolder(view);

    }



    @Override
    public void onBindViewHolder(@NonNull FicheMedecinAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        // this.position = position;

        holder.txtname.setText(String.valueOf(enfantslist.get(position).getNomPrenom()));
        holder.txtphonenumber.setText(String.valueOf(enfantslist.get(position).getTel()));
        holder.txtadresse.setText(String.valueOf(enfantslist.get(position).getAdresse()));
        holder.txtemail.setText(String.valueOf(enfantslist.get(position).getEmail()));
        holder.imageenfant.setImageResource(R.drawable.doctor);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Bundle result = new Bundle();
                result.putString("fiche_id_medecin",String.valueOf(enfantslist.get(position).getFicheId()));
                result.putString("name",enfantslist.get(position).getNomPrenom());
                result.putString("email",String.valueOf(enfantslist.get(position).getEmail()));
                result.putString("numeroTel",String.valueOf(enfantslist.get(position).getTel()));
                result.putString("adresse",String.valueOf(enfantslist.get(position).getAdresse()));

            }
        });


    }


    @Override
    public int getItemCount() {
        return enfantslist.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtname, txtphonenumber, txtemail, txtadresse;
        ImageView imageenfant;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            // itemView.setOnClickListener(this);
            txtname = itemView.findViewById(R.id.txt_name);
            txtphonenumber = itemView.findViewById(R.id.txttelephone);
            txtadresse = itemView.findViewById(R.id.txt_adresse);
            txtemail = itemView.findViewById(R.id.txtemail);
            imageenfant = itemView.findViewById(R.id.imagemedecin);
            mainLayout = itemView.findViewById(R.id.layoutmedecin);


            // mainLayout.setOnClickListener();

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//
//                }
//            });

        }


    }
}
