package tn.esprit.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tn.esprit.project.models.EnfantVaccine;
import tn.esprit.project.models.ModelViewEnfantVaccine;
import tn.esprit.project.models.Vaccine;

public class AdapterVaccinEnfant  extends RecyclerView.Adapter<
        AdapterVaccinEnfant.ViewHolder> {


    private Context mContext;
    private List<ModelViewEnfantVaccine> vaccineList;

    @Override
    public AdapterVaccinEnfant.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = LayoutInflater.from(mContext).inflate(R.layout.items_vaccine, parent, false);
        return new AdapterVaccinEnfant.ViewHolder((mItemView));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterVaccinEnfant.ViewHolder holder, int position) {
        ModelViewEnfantVaccine singleVaccine = vaccineList.get(position);
        holder.txtDescription.setText(singleVaccine.getDescription());
        holder.textViewDateC.setText(String.valueOf(singleVaccine.getDateCreation().toString()));
    }

    @Override
    public int getItemCount() {
        return vaccineList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtDescription;
        private TextView textViewDateC;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDateC = itemView.findViewById(R.id.textViewDateC);
            txtDescription = itemView.findViewById(R.id.textViewDescription);
        }
    }

    AdapterVaccinEnfant(Context mContext, List<ModelViewEnfantVaccine> vaccineList) {
        this.mContext = mContext;
        this.vaccineList = vaccineList;
    }



}
