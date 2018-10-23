package com.compubase.mhmd.digitalatlas;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ApproveListAdapter extends RecyclerView.Adapter<ApproveListAdapter.ViewHolder> {
    ArrayList<ApproveList> approveList;
    TinyDB tinyDB ;
    String GET_JSON_DATA_HTTP_URL;

    public ApproveListAdapter (ArrayList<ApproveList> list)
    {
        approveList = list;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView uname;
        Button approve ;
        public ViewHolder(View itemView) {
            super(itemView);
            uname = itemView.findViewById(R.id.usernameee);
            approve = itemView.findViewById(R.id.approve);
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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
       // holder.itemView.setTag(approveList.get(position));
        holder.uname.setText(approveList.get(position).getUsername());
        if (approveList.get(position).getIsapproved().equals("yes")) {
            holder.approve.setEnabled(false);
            holder.approve.setBackground(holder.approve.getContext().getDrawable(R.drawable.greenbutton));

        }else if (approveList.get(position).getIsapproved().equals("no"))
        {
            holder.approve.setBackground(holder.approve.getContext().getDrawable(R.drawable.redbutton));
            holder.approve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }


    }


    @Override
    public int getItemCount() {
        return approveList.size();
    }
}


