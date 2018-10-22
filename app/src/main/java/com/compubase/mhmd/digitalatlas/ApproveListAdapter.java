package com.compubase.mhmd.digitalatlas;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ApproveListAdapter extends RecyclerView.Adapter<ApproveListAdapter.ViewHolder> {
    ArrayList<ApproveList> approveList;
    public ApproveListAdapter (ArrayList<ApproveList> list)
    {
        approveList = list;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView uname;
        Button apprive ;
        String approved;
        public ViewHolder(View itemView) {
            super(itemView);
            uname = itemView.findViewById(R.id.usernameee);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.approvelist, parent
                ,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApproveListAdapter.ViewHolder holder, int position) {
       // holder.itemView.setTag(approveList.get(position));
        holder.uname.setText(approveList.get(position).getUsername());
    }

    @Override
    public int getItemCount() {
        return approveList.size();
    }
}


