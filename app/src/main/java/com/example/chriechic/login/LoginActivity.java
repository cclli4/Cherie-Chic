package com.example.chriechic.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.chriechic.R;
import com.example.chriechic.main.MainActivity;
import com.example.chriechic.register.RegisterActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    MaterialButton btnLogin, btnRegister;
    TextInputEditText inputUser, inputPassword;
    LoginViewModel loginViewModel;
    String strUsername, strPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setInitLayout();
        setInputData();
    }

    private void setInitLayout() {
        inputUser = findViewById(R.id.inputUser);
        inputPassword = findViewById(R.id.inputPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        // Using the updated ViewModelProvider syntax
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
    }

    private void setInputData() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strUsername = inputUser.getText().toString();
                strPassword = inputPassword.getText().toString();

                if (strUsername.isEmpty() || strPassword.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Ups, Form harus diisi semua!",
                            Toast.LENGTH_LONG).show();
                } else {
                    loginViewModel.getDataUser(strUsername, strPassword).observe(LoginActivity.this,
                            modelDatabases -> {
                                if (modelDatabases.size() != 0) {
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(LoginActivity.this,
                                            "Ups, Username atau Password Anda salah!", Toast.LENGTH_LONG).show();
                                }
                            });
                }
            }
        });
    }

}
