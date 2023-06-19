package com.example.seguridadcolectiva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Reset_Pass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);
    }

    public void Login(View view){
        Intent i = new Intent(this,Sign_up.class);
        startActivity(i);
    }

    public void back(View view){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);

    }

    public void Reset(View view){
        Intent i = new Intent(this,smsreset.class);
        startActivity(i);
    }
}