package com.compubase.mhmd.digitalatlas;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class NotificationUserAdapter extends RecyclerView.Adapter<NotificationUserAdapter.ViewHolder> {
     ArrayList<NotificationUser> notificationUsers ;

    public NotificationUserAdapter(ArrayList<NotificationUser> notificationUsers) {
        this.notificationUsers = notificationUsers;
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView sender,title, body;

        ViewHolder(View itemView) {
            super(itemView);
            sender = itemView.findViewById(R.id.sender);
            title = itemView.findViewById(R.id.titile);
            body = itemView.findViewById(R.id.body);
        }


    }

    @NonNull
    @Override
    public NotificationUserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlistnotificatoin,parent,false);

        return new ViewHolder(view);

    }

    @NonNull


    @Override
    public void onBindViewHolder(NotificationUserAdapter.ViewHolder holder, int position) {
        holder.sender.setText(notificationUsers.get(position).getSender());
        holder.title.setText(notificationUsers.get(position).getTitle());
        holder.body.setText(notificationUsers.get(position).getBody());

    }

    @Override
    public int getItemCount() {
        return notificationUsers.size();
    }




}
