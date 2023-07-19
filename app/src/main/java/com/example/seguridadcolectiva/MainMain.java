package com.example.seguridadcolectiva;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class MainMain extends AppCompatActivity {

    //Variables para mostrar los datos
    private TextView tv1, tvProvincia, tvCorregimiento,tvubicacion;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_main);

        tv1 = findViewById(R.id.name_main);
        tvubicacion=findViewById(R.id.ubicacion);

        databaseHelper = new DatabaseHelper(this);

        String email = getIntent().getStringExtra("email");

        if (email != null) {
            String[] data = databaseHelper.getNameAndLastNameAndFields(email);
            String nombre = data[0];
            String apellido = data[1];
            String provincia = data[2];
            String corregimiento = data[3];

            if (nombre != null && apellido != null) {

                String nombreyapellido = nombre + " " + apellido;
                tv1.setText(nombreyapellido);

                // Mostrar los valores de los campos adicionales
                String ubicacion = provincia + " " + corregimiento;
                tvubicacion.setText(ubicacion);

            } else {
                tv1.setText("Hola Null Null");
            }
        } else {
            tvubicacion.setText("Hola Null Null");
        }
    }
}