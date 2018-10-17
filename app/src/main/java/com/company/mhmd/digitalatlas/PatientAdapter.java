package com.company.mhmd.digitalatlas;

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
    ArrayList<Patient> people;
    public PatientAdapter(Context context , ArrayList<Patient> list)
    {
        people = list;
    }
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView userimg;
        TextView name , id , email;
        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            id = itemView.findViewById(R.id.id);
            email = itemView.findViewById(R.id.emails);
            userimg = itemView.findViewById(R.id.userimg);


        }
    }
    @NonNull
    @Override
    public PatientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem , parent
        ,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientAdapter.ViewHolder viewHolder, int i) {
        viewHolder.itemView.setTag(people.get(i));
        viewHolder.name.setText(people.get(i).getName());
        viewHolder.id.setText(people.get(i).getUserId());
        viewHolder.email.setText(people.get(i).getEmail());



    }

    @Override
    public int getItemCount() {
        return people.size();
    }
}
