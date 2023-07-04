package com.example.seguridadcolectiva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void Login(View view){
        Intent i = new Intent(this,MainMain.class);
        startActivity(i);
    }

    public void back(View view){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);

    }
    public void Sign(View view){
        Intent i = new Intent(this,Sign_up.class);
        startActivity(i);
    }

    public void resetpass(View view){
        Intent i = new Intent(this,Reset_Pass.class);
        startActivity(i);

    }
}