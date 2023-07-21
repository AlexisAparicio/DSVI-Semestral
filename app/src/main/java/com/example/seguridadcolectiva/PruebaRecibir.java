package com.example.seguridadcolectiva;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;

import android.widget.TextView;


public class PruebaRecibir extends AppCompatActivity {

    // Variables para mostrar los datos
    private TextView tvNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_recibir_parametros);

        tvNombre = findViewById(R.id.tvNombre);

        // Obtener los datos del intent
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");

        // Mostrar el nombre y apellido en los TextViews
        tvNombre.setText(nombre);

    }
}
