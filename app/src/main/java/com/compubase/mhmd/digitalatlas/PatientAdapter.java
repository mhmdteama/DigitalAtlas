package com.compubase.mhmd.digitalatlas;

import android.content.Context;
import android.content.Intent;
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
    Context context;
    TinyDB tinyDB;

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

        context = parent.getContext();
        tinyDB = new TinyDB(context);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientAdapter.ViewHolder holder, final int position) {
        holder.name.setText(ourPatients.get(position).getName());
        // holder.userimg.setImageResource(Integer.parseInt(ourPatients.get(position).getImgUrl()));
        holder.id.setText(ourPatients.get(position).getId());
        holder.email.setText(ourPatients.get(position).getUseremail());
        if(tinyDB.getString("type").equals("admin"))
        {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,Addfinging1ByAdmin.class);
                    intent.putExtra("userID",ourPatients.get(position).getId());
                    intent.putExtra("userApproval",ourPatients.get(position).getIsApproved());
                    context.startActivity(intent);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return ourPatients.size();
    }

}
