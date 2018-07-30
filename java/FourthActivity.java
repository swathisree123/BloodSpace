package com.example.my.newapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class FourthActivity extends AppCompatActivity {

    Context context;
    private EditText et;
    private EditText et2;
    private Button bt1;
    Activity activity;
    TextView outputHolder;
    private ProgressDialog pd;
    public static String output=" ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        context = getBaseContext();
        activity = this;
        et=findViewById(R.id.editText);
        et2=findViewById(R.id.editText2);
        bt1=findViewById(R.id.button4);
        pd = new ProgressDialog(FourthActivity.this);
        outputHolder = findViewById(R.id.outputHolder);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                   getData();
                }
            }
        });}

            private void getData()
            {

                RequestQueue queue = Volley.newRequestQueue(this);
                String S_URL = "http://192.168.0.103/app/getdata.php";
                pd.show();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, S_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                pd.hide();
                                try {

                                    Log.d("RESPONSE",response);
                                    JSONObject obj = new JSONObject(response);
                                    JSONArray jsonarray = obj.getJSONArray("result");
                                    for (int i = 0; i < jsonarray.length(); i++) {
                                        JSONObject jsonobject = jsonarray.getJSONObject(i);
                                        String name = jsonobject.getString("name");
                                        String phnno = jsonobject.getString("phnno");
                                        output+=name+ "\t\t\t\t\t\t\t\t\t\t" +phnno+"\n\n";

                                    }

                                }
                                catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                //outputHolder.setText(output);
                                Intent intent = new Intent(FourthActivity.this, SixthActivity.class);
                                startActivity(intent);
                            }

                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                if (error != null) {
                                    Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                ){
                    @Override
                    protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("bldgrp", et.getText().toString());
                    params.put("city",et2.getText().toString() );
                    Log.e("PARAMS\t\t\t\t",params.toString());
                    return params;
                }
                };

                queue.add(stringRequest);

            }

            private boolean validate() {
                if (et.getText().toString().equalsIgnoreCase("") ||
                        et2.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (!(Pattern.matches("^(a|b|o|ab|A|B|O|AB)[+ -]$", et.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Incorrect Blood group Format", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (!(Pattern.matches("^[a-z A-Z]{2,15}$", et2.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Incorrect City",  Toast.LENGTH_SHORT).show();
                    return false;
                }


                return true;
            }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FourthActivity.this, Second_Activity.class);
        startActivity(intent);

    }

}

