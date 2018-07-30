package com.example.my.newapp;

import android.app.ProgressDialog;
import android.content.Intent;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ThirdActivity extends AppCompatActivity {

    public String name,bldgrp,dob,phnno,city,state;
    private EditText et1;
    private EditText et2;
    private EditText et3;
    private RadioButton rbt1;
    private RadioButton rbt2;
    private EditText et4, et5, et6;
    private Button bt1;
    private static String S_URL = "http://192.168.0.103/app/register.php";
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        et1 = (EditText) findViewById(R.id.editText13);
        et2 = (EditText) findViewById(R.id.editText14);
        et3 = (EditText) findViewById(R.id.phnno);
        rbt1 = (RadioButton) findViewById(R.id.radioButton2);
        rbt2 = (RadioButton) findViewById(R.id.radioButton3);
        et4 = (EditText) findViewById(R.id.editText15);
        et5 = (EditText) findViewById(R.id.editText16);
        et6 = (EditText) findViewById(R.id.editText17);
        bt1 = (Button) findViewById(R.id.button5);
        pd = new ProgressDialog(ThirdActivity.this);


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate()) {
                    signupRequest();
                }
            }
        });}

    private void signupRequest() {

        pd.show();

        RequestQueue queue = Volley.newRequestQueue(this);
        String response = null;
        final String finalResponse = response;

        StringRequest postRequest = new StringRequest(Request.Method.POST, S_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.hide();

                        if(response!=null)
                            Log.wtf("RESPONSE",response);

                        if (response.equals("Successfully Registered")) {
                            Intent intent = new Intent(ThirdActivity.this, FifthActivity.class);
                            startActivity(intent);

                        }
                        if (response.equals("phonenumber exist")) {
                            Toast.makeText(getApplicationContext(), "Already registered", Toast.LENGTH_SHORT).show();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //if(error.networkResponse.toString()!=null)
                         //   Log.wtf("RESPONSE",error.networkResponse.toString());
                       // pd.hide();
                        // error
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("name", et1.getText().toString());
                params.put("bldgrp",et2.getText().toString() );
                params.put("phnno", et3.getText().toString());
                params.put("dob", et4.getText().toString());
                params.put("city", et5.getText().toString());
                params.put("state", et6.getText().toString());


                return params;
            }
        };

        queue.add(postRequest);

    }





    private boolean validate() {
        if (et1.getText().toString().equalsIgnoreCase("") ||
                et2.getText().toString().equalsIgnoreCase("")
                || et3.getText().toString().equalsIgnoreCase("") ||
                et4.getText().toString().equalsIgnoreCase("")
                || et5.getText().toString().equalsIgnoreCase("") ||
                et6.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(getApplicationContext(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!(Pattern.matches("^[a-z A-Z]{2,15}$", et1.getText().toString()))) {
            Toast.makeText(getApplicationContext(), "Incorrect Name Format", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!(Pattern.matches("^(a|b|o|ab|A|B|O|AB)[+ -]$", et2.getText().toString()))) {
            Toast.makeText(getApplicationContext(), "Incorrect Blood group Format", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!(Pattern.matches("^(6|7|8|9)[0-9]$", et3.getText().toString())) && et3.getText().toString().length() != 10) {
            Toast.makeText(getApplicationContext(), "Incorrect Phonenumber", Toast.LENGTH_SHORT).show();
            return false;

        }
        if (!(Pattern.matches("^(0[1-9]|1[0-9]|2[0-9]|30|31)[-/.](0[1-9]|10|11|12)[-/.](19|20)\\d\\d$", et4.getText().toString()))) {
            Toast.makeText(getApplicationContext(), "Incorrect Date of birth", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!(Pattern.matches("^[a-z A-Z]{2,15}$", et5.getText().toString()))) {
            Toast.makeText(getApplicationContext(), "Incorrect City", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!(Pattern.matches("^[a-z A-Z]{2,15}$", et6.getText().toString()))) {
            Toast.makeText(getApplicationContext(), "Incorrect State", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}



