package com.compubase.mhmd.digitalatlas;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserNotification extends Fragment {

    View view;
    RecyclerView notificationList ;
    ArrayList<NotificationUser> notifications = new ArrayList<>();
    String URL = "http://atlas.alosboiya.com.sa/atlas.asmx/select_all_note?";
    RequestQueue requestQueue;
    NotificationUserAdapter myAdapter;



    public UserNotification() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_user_notification, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        notificationList = view.findViewById(R.id.notificationlists);

        notificationList.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        notificationList.setLayoutManager(layoutManager);

        JSON_DATA_WEB_CALL();
    }

    public void JSON_DATA_WEB_CALL(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                        JSON_PARSE_DATA_AFTER_WEBCALL(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        showMessage();


                    }
                }
        );

        requestQueue = Volley.newRequestQueue(getContext());

        requestQueue.add(stringRequest);
    }

    public void JSON_PARSE_DATA_AFTER_WEBCALL(String Jobj){

        try {
            JSONArray js = new JSONArray(Jobj);

            for(int i = 0; i<js.length(); i++) {

                JSONObject childJSONObject = js.getJSONObject(i);

                NotificationUser GetDataAdapter2 = new NotificationUser();

                GetDataAdapter2.setSender(childJSONObject.getString("sender"));
                GetDataAdapter2.setTitle(childJSONObject.getString("head"));
                GetDataAdapter2.setBody(childJSONObject.getString("body"));

                notifications.add(GetDataAdapter2);
            }
            myAdapter = new NotificationUserAdapter( notifications);
            notificationList.setAdapter(myAdapter);

            myAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }




    private void showMessage() {
        Toast.makeText(getContext(), "No Connection", Toast.LENGTH_LONG).show();
    }

}
