package com.compubase.mhmd.digitalatlas;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
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


public class PatientListFragment extends Fragment {
    RecyclerView patientRecyclerView ;
    RecyclerView.Adapter myadapter;
    RecyclerView.LayoutManager layoutManager;
    View view;
    ArrayList<ApproveList> approveLists;

    String URL = "http://atlas.alosboiya.com.sa/atlas.asmx/select_all_pat_for_admin?";

    RequestQueue requestQueue;

    ApproveListAdapter adapter;

    public PatientListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_patient_list, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        patientRecyclerView = view.findViewById(R.id.approvelist);
        patientRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getActivity());
        patientRecyclerView.setLayoutManager(layoutManager);


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

                ApproveList GetDataAdapter2 = new ApproveList();

                GetDataAdapter2.setUsername(childJSONObject.getString("name"));

                GetDataAdapter2.setIsApproved(childJSONObject.getString("approval"));


                approveLists.add(GetDataAdapter2);
            }

            adapter = new ApproveListAdapter(approveLists,getContext());
            patientRecyclerView.setAdapter(adapter);

            adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }




    private void showMessage() {
        Toast.makeText(getContext(), "No Connection", Toast.LENGTH_LONG).show();
    }



}
