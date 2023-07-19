package com.example.seguridadcolectiva;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    private EditText etPassword, etUsermail;
    private Button btnLogin;
    private DatabaseHelper databaseHelper;

    private int loginAttempts = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsermail = findViewById(R.id.etlUsermail);
        etPassword = findViewById(R.id.etlPassword);
        btnLogin = findViewById(R.id.btnLogin);
        TextView textLink = findViewById(R.id.text_link_registro);

        databaseHelper = new DatabaseHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etUsermail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                boolean success = databaseHelper.loginUser(email, password);
                if (success) {
                    // Inicio de sesión exitoso, realizar la acción deseada
                    Toast.makeText(Login.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                    String[] data = databaseHelper.getNameAndLastNameAndFields(email);

                    Intent intent = new Intent(Login.this, MainMain.class);
                    intent.putExtra("email", email);
                    startActivity(intent);

                } else {
                    // Inicio de sesión fallido, incrementar el contador de intentos
                    loginAttempts++;
                    if (loginAttempts >= 3) {
                        Toast.makeText(Login.this, "Por favor, regístrate para continuar", Toast.LENGTH_LONG).show();
                    } else {
                        // Inicio de sesión fallido, mostrar mensaje de error
                        Toast.makeText(Login.this, "Credenciales inválidas, intenta nuevamente", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        textLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción a realizar cuando se hace clic en el texto
                Intent intent = new Intent(Login.this, Sign_up.class);
                startActivity(intent);
            }
        });

    }
}