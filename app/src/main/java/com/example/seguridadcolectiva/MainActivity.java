package com.example.seguridadcolectiva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Login(View view){
        Intent i = new Intent(this,Login.class);
        startActivity(i);
    }

    public void Sign(View view){
        Intent i = new Intent(this,Sign_up.class);
        startActivity(i);
    }

    public void End(View view){
        finish();
    }
}