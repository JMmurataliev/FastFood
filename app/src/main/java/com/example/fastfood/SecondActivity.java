package com.example.fastfood;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText, confirmPasswordEditText;
    private Button loginButton, googleButton, facebookButton;
    private ImageView backButton, eyeButton;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        initViews();

        setupListeners();
    }

    private void initViews() {
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.conf_passw);
        loginButton = findViewById(R.id.login);
        googleButton = findViewById(R.id.google);
        facebookButton = findViewById(R.id.facebook);
        backButton = findViewById(R.id.ic_back);
        eyeButton = findViewById(R.id.ic_back);
    }

    private void setupListeners() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInput()) {

                    Intent intent = new Intent(SecondActivity.this, Menu.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(SecondActivity.this, "Вход через Google", Toast.LENGTH_SHORT).show();
            }
        });

        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(SecondActivity.this, "Вход через Facebook", Toast.LENGTH_SHORT).show();
            }
        });

        eyeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility();
            }
        });
    }

    private boolean validateInput() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();

        if (email.isEmpty()) {
            emailEditText.setError("Введите Email");
            return false;
        }

        if (password.isEmpty()) {
            passwordEditText.setError("Введите пароль");
            return false;
        }

        if (confirmPassword.isEmpty()) {
            confirmPasswordEditText.setError("Повторите введенный пароль");
            return false;
        }

        if (!password.equals(confirmPassword)) {
            confirmPasswordEditText.setError("Неправильный введен пароль");
            return false;
        }

        return true;
    }

    private void togglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible;

    }
    public void go_Back(View view) {
        onBackPressed();
    }
} 