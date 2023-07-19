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
   /* Location mLastLocation;
    TextView mLatitude;
    TextView mLongitude;
    private GoogleApiClient mGoogleApiClient;*/

    //Variables para mostrar los datos
    private TextView tv1, tvProvincia, tvCorregimiento,tvubicacion;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_main);

        tv1 = findViewById(R.id.name_main);
        tvubicacion=findViewById(R.id.ubicacion);
        //tvProvincia= findViewById(R.id.tvhProvincia);
        //tvCorregimiento= findViewById(R.id.tvhCorregimiento);

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

                //tvProvincia.setText(provincia);
                //tvCorregimiento.setText(corregimiento);
            } else {
                tv1.setText("Hola Null Null");
            }
        } else {
            tvubicacion.setText("Hola Null Null");
        }
        /*
        //mLatitude=(TextView)findViewById(R.id.ubicacion);
        //mLongitude=(TextView)findViewById(R.id.mLongitude);

        // Establecer punto de entrada para la API de ubicación
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) this)
                .addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) this)
                .addApi(LocationServices.API)
                .build();*/


    }
/*
    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    public class LocationActivity extends AppCompatActivity implements
            GoogleApiClient.ConnectionCallbacks,
            GoogleApiClient.OnConnectionFailedListener {
        @Override
        public void onConnected(@Nullable Bundle bundle) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (mLastLocation != null) {
                mLatitude.setText(String.valueOf(mLastLocation.getLatitude()));
                mLongitude.setText(String.valueOf(mLastLocation.getLongitude()));
            } else {
                Toast.makeText(this, "Ubicación no encontrada", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onConnectionSuspended(int i) {

        }

        @Override
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        }
    }   */
}