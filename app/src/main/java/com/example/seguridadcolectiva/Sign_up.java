package com.example.seguridadcolectiva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Sign_up extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void Login(View view){
        Intent i = new Intent(this,Login.class);
        startActivity(i);
    }

    public void back(View view){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);

    }

}