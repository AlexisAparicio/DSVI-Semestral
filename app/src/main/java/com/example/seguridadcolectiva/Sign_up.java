package com.example.seguridadcolectiva;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;


public class Sign_up extends AppCompatActivity {
    private EditText etUsername, etPassword, etUsermail, etUserapellido, etProvincia, etCorregimiento;
    private Button btnRegister;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etUsermail = findViewById(R.id.inp_mail);
        etUsername = findViewById(R.id.inp_nombre);
        etUserapellido = findViewById(R.id.inp_apellido);
        etProvincia = findViewById(R.id.inp_provincia);
        etCorregimiento = findViewById(R.id.inp_corregimiento);
        etPassword = findViewById(R.id.inp_passcode);
        btnRegister = findViewById(R.id.inp_button_register);
        btnRegister.setEnabled(false);

        databaseHelper = new DatabaseHelper(this);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No se requiere implementación
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // No se requiere implementación
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Verificar si todos los campos requeridos están completos
                String usermail = etUsermail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                boolean allFieldsCompleted = !usermail.isEmpty() && !password.isEmpty();

                // Habilitar o deshabilitar el botón de registro según el estado de los campos de entrada
                btnRegister.setEnabled(allFieldsCompleted);
            }
        };

// Agregar el TextWatcher a los campos de entrada de texto
        etUsermail.addTextChangedListener(textWatcher);
        etPassword.addTextChangedListener(textWatcher);


        btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = etUsermail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String nombre = etUsername.getText().toString().trim();
                String apellido = etUserapellido.getText().toString().trim();
                String provincia = etProvincia.getText().toString().trim();
                String corregimiento = etCorregimiento.getText().toString().trim();

                boolean success = databaseHelper.registerUser(email, password, nombre, apellido, provincia, corregimiento);

                if (success) {
                    Toast.makeText(Sign_up.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    // Registro exitoso, redirigir a la actividad de inicio de sesión
                    Intent intent = new Intent(Sign_up.this, Login.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    // Error de registro, mostrar mensaje de error
                    Toast.makeText(Sign_up.this, "Este usuario ya existe", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    public void back(View view){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);

    }
}