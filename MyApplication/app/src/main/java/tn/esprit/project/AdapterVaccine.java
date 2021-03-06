package tn.esprit.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tn.esprit.project.models.Vaccine;

public class AdapterVaccine extends RecyclerView.Adapter<
        AdapterVaccine.ViewHolder> {
    private Context mContext;
    private List<Vaccine> vaccineList;

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = LayoutInflater.from(mContext).inflate(R.layout.items_vaccine, parent, false);
        return new ViewHolder((mItemView));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterVaccine.ViewHolder holder, int position) {
        Vaccine singleVaccine = vaccineList.get(position);
        holder.txtDescription.setText(singleVaccine.getDescription());
        holder.txtNumberMonth.setText(String.valueOf(singleVaccine.getMonthNumber())+" Month");

       /* if(Add_child_vaccine_Activity.enfant!=null) {

            if (Add_child_vaccine_Activity.enfant.getVaccinToDoList().contains(singleVaccine)) {

                holder.imageVaccin.setImageResource(R.drawable.iconnonproteger);
                holder.txtDescription.setText(singleVaccine.getDescription()+" late !");
            }
        }*/


    }

    @Override
    public int getItemCount() {
        return vaccineList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtDescription;
        private TextView txtNumberMonth;
        private ImageView imageVaccin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNumberMonth = itemView.findViewById(R.id.textViewDateC);
            txtDescription = itemView.findViewById(R.id.textViewDescription);
            imageVaccin=itemView.findViewById(R.id.imageViewItemVaccinChild);
        }
    }

    AdapterVaccine(Context mContext, List<Vaccine> vaccineList) {
        this.mContext = mContext;
        this.vaccineList = vaccineList;
    }
}
