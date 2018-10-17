package com.compubase.mhmd.digitalatlas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ForgetPassword extends AppCompatActivity {
    Button recover ;
    String GET_JSON_DATA_HTTP_URL;
    EditText emailpss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        recover = findViewById(R.id.recover);
        emailpss = findViewById(R.id.emailpass);
    }
    private void volleyConnection()
    {
        GET_JSON_DATA_HTTP_URL = "http://atlas.alosboiya.com.sa/atlas.atlas.asmx/forgete_password?";
        // http://atlas.alosboiya.com.sa/atlas.asmx?op=login_campany

        StringRequest stringRequest = new StringRequest(Request.Method.POST, GET_JSON_DATA_HTTP_URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showMessage(response);
                        if (Objects.equals(response, "False"))
                        {

                            showMessage("Invalid user name or password ");

                        }else {
                            Intent intent = new Intent(ForgetPassword.this,SignIn.class);
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
                params.put("mail", emailpss.getText().toString());

                return params;
            }



        };

        RequestHandler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
    private void showMessage(String _s) {
        Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_LONG).show();
    }
}
