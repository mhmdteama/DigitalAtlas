package com.compubase.mhmd.digitalatlas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Admin_Notifications extends AppCompatActivity {
    EditText  title, body;
    EditText person;
    Button send;
    String GET_JSON_DATA_HTTP_URL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__notifications);
        person = findViewById(R.id.person);
        title = findViewById(R.id.title);
        body = findViewById(R.id.body);
        send = findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volleyconnect();
            }
        });
    }
    public void volleyconnect()
    {
        GET_JSON_DATA_HTTP_URL = "http://atlas.alosboiya.com.sa/atlas.asmx?op=note";
        // http://atlas.alosboiya.com.sa/atlas.asmx?op=note
        StringRequest stringRequest = new StringRequest(Request.Method.POST, GET_JSON_DATA_HTTP_URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showMessage(response);
                        showMessage("Success Sending");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                showMessage(error.toString());

            }

        }) {

            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("sender", person.getText().toString());
                params.put("head", title.getText().toString());
                params.put("body", body.getText().toString());

                return params;
            }



        };

    }
    private void showMessage(String _s) {
        Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_LONG).show();
    }

}

