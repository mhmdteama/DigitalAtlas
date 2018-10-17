package com.compubase.mhmd.digitalatlas;


import android.os.Bundle;
import android.app.Fragment;
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


public class PatientListAdmin extends Fragment {
    RecyclerView paientList ;
    RecyclerView.Adapter myadapter;
    RecyclerView.LayoutManager layoutManager;
    View view;
    ArrayList<Patient> patients;
    String URL = "http://atlas.alosboiya.com.sa/atlas.asmx/select_all_note?";
    RequestQueue requestQueue;
    PatientAdapter adapter;
    public PatientListAdmin() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       view =  inflater.inflate(R.layout.fragment_patient_list_admin, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        paientList = view.findViewById(R.id.adminpaientlist);
        paientList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getActivity());
        paientList.setLayoutManager(layoutManager);
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

                Patient GetDataAdapter2 = new Patient();

                GetDataAdapter2.setName(childJSONObject.getString("head"));
                GetDataAdapter2.setUserId(childJSONObject.getString("body"));
                GetDataAdapter2.setImage(childJSONObject.getString("body"));
                GetDataAdapter2.setEmail(childJSONObject.getString("body"));





                patients.add(GetDataAdapter2);
            }

            adapter = new PatientAdapter(getContext() , patients);
            paientList.setAdapter(adapter);

            adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }




    private void showMessage() {
        Toast.makeText(getContext(), "No Connection", Toast.LENGTH_LONG).show();
    }


}
