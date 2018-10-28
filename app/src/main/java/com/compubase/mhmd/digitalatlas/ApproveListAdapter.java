package com.compubase.mhmd.digitalatlas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ApproveListAdapter extends RecyclerView.Adapter<ApproveListAdapter.ViewHolder> {

    ArrayList<ApproveList> approveList;
    TinyDB tinyDB ;
    RequestQueue requestQueue;
    Context context;

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

        context = parent.getContext();

        tinyDB = new TinyDB(context);

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.approvelist, parent
                ,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        // holder.itemView.setTag(approveList.get(position));
        holder.uname.setText(approveList.get(position).getUsername());
        if (approveList.get(position).getIsapproved().equals("yes")) {

            holder.approve.setEnabled(false);
            holder.approve.setBackground(holder.approve.getContext().getDrawable(R.drawable.greenbutton));
            holder.approve.setText("Approved");

        }else if (approveList.get(position).getIsapproved().equals("no"))
        {
            holder.approve.setBackground(holder.approve.getContext().getDrawable(R.drawable.redbutton));
            holder.approve.setText("Approve");
            holder.approve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String URL = "http://atlas.alosboiya.com.sa/atlas.asmx/update_approval?id="+approveList.get(position).getId()+"&approval=yes";


                    StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,

                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    holder.approve.setBackground(holder.approve.getContext().getDrawable(R.drawable.greenbutton));
                                    holder.approve.setText("Approved");

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {


                            showMessage();

                        }

                    }) {


                        @Override
                        protected Map<String,String> getParams(){
                            Map<String,String> params = new HashMap<>();
                            params.put("id", "6");
                            params.put("approval", "yes");
                            return params;
                        }

                    };

                    requestQueue = Volley.newRequestQueue(context);

                    requestQueue.add(stringRequest);




                }
            });
        }


    }

    private void showMessage() {
        Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
    }


    @Override
    public int getItemCount() {
        return approveList.size();
    }
}


