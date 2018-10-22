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

public class PaientList extends Fragment {

    RecyclerView paientList ;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Patient> patients;
    String URL = "http://atlas.alosboiya.com.sa/atlas.asmx/select_pat_by_iduser?";
    RequestQueue requestQueue;
    RecyclerView.Adapter myAdapter;
    View view;
    public PaientList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view = inflater.inflate(R.layout.fragment_paient_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        paientList = view.findViewById(R.id.paientlists);
        paientList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getContext());
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

                GetDataAdapter2.setName(childJSONObject.getString("name"));
                GetDataAdapter2.setId(childJSONObject.getString("id"));
                GetDataAdapter2.setImgUrl(childJSONObject.getString("image"));
                GetDataAdapter2.setUseremail(childJSONObject.getString("email"));
                patients.add(GetDataAdapter2);
            }

            myAdapter = new PatientAdapter(getContext(),patients);
            paientList.setAdapter(myAdapter);
            //myAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }




    private void showMessage() {
        Toast.makeText(getContext(), "No Connection", Toast.LENGTH_LONG).show();
    }

}
