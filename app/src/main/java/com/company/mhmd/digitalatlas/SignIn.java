package com.company.mhmd.digitalatlas;

import android.content.Intent;
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
import java.util.Objects;

public class SignIn extends AppCompatActivity {
    String GET_JSON_DATA_HTTP_URL;
    TinyDB tinyDB ;
    EditText username , pass;
    Button siginIn , creat , forget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        new TinyDB(getApplicationContext());
        username = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        siginIn = findViewById(R.id.signin);
        forget = findViewById(R.id.forgetpass);
        creat = findViewById(R.id.creat);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this,ForgetPassword.class);
                startActivity(intent);
            }
        });
        creat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this,Regestration.class);
                startActivity(intent);
            }
        });
        siginIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volleyConnection();
            }
        });

    }
    private void volleyConnection()
    {
        GET_JSON_DATA_HTTP_URL = "http://atlas.alosboiya.com.sa/atlas.asmx/login_campany?";
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
                            Intent intent = new Intent(SignIn.this,MainActivity.class);
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
                params.put("username", username.getText().toString());
                params.put("pass", pass.getText().toString());

                return params;
            }



        };

        RequestHandler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
    private void showMessage(String _s) {
        Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_LONG).show();
    }

}
