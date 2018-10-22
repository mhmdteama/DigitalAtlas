package com.compubase.mhmd.digitalatlas;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Addfinding3 extends Fragment {
    View view;
    private static final String[] ageOf = { "Newborn" , "Child" , "Adolescence" , "Old Agy"};
    private static final String[] genderType = {"Male" , "Female" };
    private static final String[] sysType = {"Cardiology" , "Respirology" , "Dermatolgy" , "endocrinology" , "General surgery", "Cardit"
            , "Gynecology", "Hematology", "Infectious Diseases", "Medical Genetics", "Medical Imaging", "Nephrology",
            "Neuorology" ,"Obtritrics" ,"Ophthalomology" ,"Orthopedics" ,"Otolaryngology" ,
            "Pediatrics" ,"Plastic surgery" ,"Rheumatology" ,"Urology" ,"Vascular surgery" ,"Oncology"};
    EditText supervision , finding, rq1 ,rq2,rq3,rq4 ,op1 , op2 ,senario , abnormal;
    Button next;
    Spinner age , gender , system;

    public Addfinding3() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_addfinding3, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        finding =view.findViewById(R.id.finding);
        rq1 = view.findViewById(R.id.required1);
        rq2 = view.findViewById(R.id.required2);
        rq3 = view.findViewById(R.id.required3);
        supervision = view.findViewById(R.id.supervision);
        rq4 = view.findViewById(R.id.required4);
        op1 = view.findViewById(R.id.optional1);
        op2 = view.findViewById(R.id.optional2);
        senario = view.findViewById(R.id.senario);
        next = view.findViewById(R.id.next);
        abnormal = view.findViewById(R.id.abnormal);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volleyConnection();
            }
        });


        ageSpiner();
        systemSpinner();
        genderSpinner();


    }
    public void ageSpiner()
    {
        age =view.findViewById(R.id.age);
        ArrayAdapter<String> ageAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item, ageOf);
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        age.setAdapter(ageAdapter);
    }
    public void genderSpinner()
    {
        gender =view.findViewById(R.id.gender);
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item, genderType);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(genderAdapter);
    }
    public void systemSpinner()
    {
        system =view.findViewById(R.id.system);
        ArrayAdapter<String> sysAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item, sysType);
        sysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        system.setAdapter(sysAdapter);

    }
    private void volleyConnection()
    {
        String GET_JSON_DATA_HTTP_URL = "http://atlas.alosboiya.com.sa/atlas.asmx?op=insert_pat?";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, GET_JSON_DATA_HTTP_URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showMessage(response);
                        if (Objects.equals(response, "False"))
                        {

                            showMessage(" Pleas Complete Information ");

                        }else {
                            Intent intent = new Intent(getContext(),MainActivity.class);
                            startActivity(intent);
                            // tinyDB.putString("userID",response);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                showMessage(error.toString());

            }

        }) {

            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("name", age.getSelectedItem().toString());
                params.put("email", gender.getSelectedItem().toString());
                params.put("system", system.getSelectedItem().toString());
                params.put("finding", finding.getText().toString());
                params.put("keywords", rq1.getText().toString());
                params.put("scenario", senario.getText().toString());
                params.put("abnormality",abnormal.getText().toString());
                params.put("keywords1", rq2.getText().toString());
                params.put("keywords2", rq3.getText().toString());
                params.put("keywords3", rq4.getText().toString());
                params.put("keywords4", op1.getText().toString());
                params.put("keywords5", op2.getText().toString());




                return params;
            }



        };

        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

    private void showMessage(String _s) {
        Toast.makeText(getContext(), _s, Toast.LENGTH_LONG).show();
    }


}
