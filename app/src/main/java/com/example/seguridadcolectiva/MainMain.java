package com.example.seguridadcolectiva;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class MainMain extends AppCompatActivity {

    //Variables para mostrar los datos
    private TextView tv1, tvProvincia, tvCorregimiento,tvubicacion,tvproblema;
    private Button btnparametros;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_main);

        tv1 = findViewById(R.id.name_main);
        tvubicacion=findViewById(R.id.ubicacion);
        btnparametros = findViewById(R.id.parametros);
        tvproblema = findViewById(R.id.text_link_reportar_problema);


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

        TextView tvRealizarReporte = findViewById(R.id.text_link_reportar_problema);
        tvRealizarReporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para abrir la actividad Formulario
                Intent intent = new Intent(MainMain.this, Formulario.class);

                // Guardar los valores actuales de tv1 y tvubicacion en el Bundle
                Bundle bundle = new Bundle();
                bundle.putString("nombreApellido", tv1.getText().toString());
                bundle.putString("ubicacion", tvubicacion.getText().toString());
                intent.putExtras(bundle);

                // Iniciar la actividad Formulario
                startActivity(intent);
            }
        });
        TextView tvVerReporte = findViewById(R.id.text_link_ver_problema);
        tvVerReporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para abrir la actividad VerReporte
                Intent intent = new Intent(MainMain.this, VerReporte.class);

                // Iniciar la actividad VerReporte
                startActivity(intent);
            }
        });
        btnparametros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el nombre y apellido de los TextViews
                String nombre = tv1.getText().toString();

                // Iniciar la actividad PruebaRecibir y pasar el nombre y apellido como extras
                Intent intent = new Intent(MainMain.this, PruebaRecibir.class);
                intent.putExtra("nombre", nombre);
                startActivity(intent);
            }
        });
    }
}