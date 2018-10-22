package com.compubase.mhmd.digitalatlas;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.ViewHolder> {
    ArrayList<Patient> ourPatients;

    public PatientAdapter(Context context, ArrayList<Patient> list) {

        ourPatients = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView userimg;
        TextView name, id, email;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            id = itemView.findViewById(R.id.userid);
            email = itemView.findViewById(R.id.useremail);
            userimg = itemView.findViewById(R.id.userimg);
        }


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patientlists, parent
                ,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientAdapter.ViewHolder holder, int position) {
        holder.name.setText(ourPatients.get(position).getName());
        holder.userimg.setImageResource(Integer.parseInt(ourPatients.get(position).getImgUrl()));
        holder.id.setText(ourPatients.get(position).getId());
        holder.email.setText(ourPatients.get(position).getUseremail());

    }

    @Override
    public int getItemCount() {
        return ourPatients.size();
    }

}
