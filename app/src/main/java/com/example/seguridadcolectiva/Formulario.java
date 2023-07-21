package com.example.seguridadcolectiva;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Formulario extends AppCompatActivity {

    private EditText et_tipo_reporte, et_hora_reporte, etzona, et_fnombre, etprovincia, etcorregimiento;
    private Button btnGuardar;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        et_tipo_reporte = findViewById(R.id.form_tipo_reporte);
        et_hora_reporte = findViewById(R.id.form_hora_reporte);
        etzona = findViewById(R.id.form_zona_atencion);
        et_fnombre = findViewById(R.id.form_nombre);
        etprovincia = findViewById(R.id.form_provincia);
        etcorregimiento = findViewById(R.id.form_corregimiento);
        btnGuardar = findViewById(R.id.btnform_enviar_reporte);

        databaseHelper = new DatabaseHelper(this);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtén los valores de los campos EditText
                String tipoReporte = et_tipo_reporte.getText().toString().trim();
                String horaReporte = et_hora_reporte.getText().toString().trim();
                String zona = etzona.getText().toString().trim();
                String nombre = et_fnombre.getText().toString().trim();
                String provincia = etprovincia.getText().toString().trim();
                String corregimiento = etcorregimiento.getText().toString().trim();

                // Verificar si los datos del formulario ya existen en la base de datos
                boolean dataExists = databaseHelper.checkFormDataExistence(tipoReporte, horaReporte, zona, nombre, provincia, corregimiento);

                if (dataExists) {
                    // Los datos del formulario ya existen en la base de datos
                    Toast.makeText(Formulario.this, "Error: Los datos del formulario ya existen", Toast.LENGTH_SHORT).show();
                } else {
                    // Los datos del formulario no existen en la base de datos, procede a guardarlos
                    boolean success = databaseHelper.saveFormData(tipoReporte, horaReporte, zona, nombre, provincia, corregimiento);

                    if (success) {
                        Toast.makeText(Formulario.this, "Datos del formulario guardados exitosamente", Toast.LENGTH_SHORT).show();

                        // Establecer el resultado y cerrar la actividad
                        Intent intent = new Intent();
                        intent.putExtra("tipoReporte", tipoReporte);
                        intent.putExtra("horaReporte", horaReporte);
                        intent.putExtra("zona", zona);
                        intent.putExtra("nombre", nombre);
                        intent.putExtra("provincia", provincia);
                        intent.putExtra("corregimiento", corregimiento);
                        setResult(RESULT_OK, intent);
                        finish();
                    } else {
                        Toast.makeText(Formulario.this, "Error al guardar los datos del formulario", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
