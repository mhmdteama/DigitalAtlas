package com.company.mhmd.digitalatlas;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ApproveListAdapter extends RecyclerView.Adapter<ApproveListAdapter.ViewHolder> {
    private ArrayList<ApproveList> approveList;
    public ApproveListAdapter(ArrayList<ApproveList> applist, Context context ) {approveList = applist;}
    public class ViewHolder extends RecyclerView.ViewHolder {

        Button approve ;
        TextView user;

        public ViewHolder(View itemView) {
            super(itemView);

            user = itemView.findViewById(R.id.usernamee);
            approve = itemView.findViewById(R.id.approve);
        }
    }

    @NonNull
    @Override
    public ApproveListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.approvelist,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApproveListAdapter.ViewHolder holder, int position) {
        //holder.itemView.setTe(approveList.get(position));
        holder.user.setText(approveList.get(position).getUsername());
    }


    @Override
    public int getItemCount() {

        return approveList.size();
    }
}
