package com.example.seguridadcolectiva;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class MainMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_main);
    }

    public void Formulario(View view){
        Intent i = new Intent(this,Formulario.class);
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