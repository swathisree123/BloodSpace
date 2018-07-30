package com.example.my.newapp;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

public class SixthActivity extends Activity {
    TextView outputHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth);
        outputHolder = findViewById(R.id.outputHolder);
        FourthActivity fa=new FourthActivity();
        if(fa.output.equals(" "))
            Toast.makeText(getApplicationContext(), "No Results Found",  Toast.LENGTH_SHORT).show();
        else {
            outputHolder.setText(fa.output);
            fa.output = " ";
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SixthActivity.this, FourthActivity.class);
        startActivity(intent);

    }
}


