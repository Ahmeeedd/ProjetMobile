package tn.esprit.project;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tn.esprit.project.models.Enfant;


public class EnfantsManageAdapter extends RecyclerView.Adapter<EnfantsManageAdapter.MyViewHolder>  {

    private Context context;
    private Activity activity;
    private ArrayList<Enfant> enfantslist;
    private int pos;

    public EnfantsManageAdapter(Activity activity, Context context, ArrayList<Enfant> liste) {
        this.activity = activity;
        this.context = context;
        this.enfantslist = liste;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.enfants_row, parent, false);
        return new MyViewHolder(view);

    }



    @Override
    public void onBindViewHolder(@NonNull EnfantsManageAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        // this.position = position;

        holder.txtname.setText(String.valueOf(enfantslist.get(position).getPrenom()));
        holder.txtheight.setText(String.valueOf(enfantslist.get(position).getTaille()));
        holder.txtweight.setText(String.valueOf(enfantslist.get(position).getPoids()));
        holder.imageenfant.setImageResource(R.drawable.bebe);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Bundle result = new Bundle();
                result.putString("id_enfant",String.valueOf(enfantslist.get(position).getEnfantId()));
                result.putString("Prenom",enfantslist.get(position).getPrenom());
                result.putString("Age",String.valueOf(enfantslist.get(position).getAge()));
                result.putString("Poids",String.valueOf(enfantslist.get(position).getPoids()));
                result.putString("Taille",String.valueOf(enfantslist.get(position).getTaille()));
                result.putString("Date Naissance",String.valueOf(enfantslist.get(position).getDate_naiss()));


            }
        });


    }


    @Override
    public int getItemCount() {
        return enfantslist.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtname, txtheight, txtweight;
        ImageView imageenfant;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            // itemView.setOnClickListener(this);
            txtname = itemView.findViewById(R.id.txt_name);
            txtheight = itemView.findViewById(R.id.txtheight);
            txtweight = itemView.findViewById(R.id.txt_weight);
            imageenfant = itemView.findViewById(R.id.imageViewItemVaccinChild);
            mainLayout = itemView.findViewById(R.id.mainLayout);


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
