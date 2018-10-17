package com.compubase.mhmd.digitalatlas;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class usserNotificAdapter extends RecyclerView.Adapter<usserNotificAdapter.ViewHolder> {
    ArrayList<NotificListUser> notificList;

    public usserNotificAdapter(Context context, ArrayList<NotificListUser> listnotific) {listnotific = notificList;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView sender , title , body;
        public ViewHolder(View itemView) {
            super(itemView);
            sender = itemView.findViewById(R.id.sender);
            title = itemView.findViewById(R.id.titile);
            body = itemView.findViewById(R.id.body);

        }
    }
    {

    }

    @NonNull
    @Override
    public usserNotificAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlistnotificatoin , parent
                ,false);
        return new ViewHolder(v);    }

    @Override
    public void onBindViewHolder(@NonNull usserNotificAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(notificList.get(position));
        holder.sender.setText(notificList.get(position).getSender());
        holder.title.setText(notificList.get(position).getTitle());
        holder.body.setText(notificList.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return notificList.size();
    }
}
