package com.example.seguridadcolectiva;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;
import android.widget.TextView;
public class VerReporte extends AppCompatActivity {

    private TextView tvTipoReporte, tvHoraReporte, tvZona, tvNombre, tvProvincia, tvCorregimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_reporte);

        tvTipoReporte = findViewById(R.id.tv_tipo_reporte);
        tvHoraReporte = findViewById(R.id.tv_hora_reporte);
        tvZona = findViewById(R.id.tv_zona);
        tvNombre = findViewById(R.id.tv_nombre);
        tvProvincia = findViewById(R.id.tv_provincia);
        tvCorregimiento = findViewById(R.id.tv_corregimiento);

        // Obtener los datos del Intent
        Intent intent = getIntent();
        if (intent != null) {
            String tipoReporte = intent.getStringExtra("tipoReporte");
            String horaReporte = intent.getStringExtra("horaReporte");
            String zona = intent.getStringExtra("zona");
            String nombre = intent.getStringExtra("nombre");
            String provincia = intent.getStringExtra("provincia");
            String corregimiento = intent.getStringExtra("corregimiento");

            // Mostrar los datos en los TextViews
            tvTipoReporte.setText("Tipo de reporte: " + tipoReporte);
            tvHoraReporte.setText("Hora de reporte: " + horaReporte);
            tvZona.setText("Zona de atención: " + zona);
            tvNombre.setText("Nombre: " + nombre);
            tvProvincia.setText("Provincia: " + provincia);
            tvCorregimiento.setText("Corregimiento: " + corregimiento);
        }

    }

    public void back(View view){
        Intent i = new Intent(this,MainMain.class);
        startActivity(i);

    }
}
