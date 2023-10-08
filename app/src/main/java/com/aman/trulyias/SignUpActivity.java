package com.aman.trulyias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    EditText            email, password, name, std;
    Button              register;
    TextView            go_to_login;
    SharedPreferences   sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email       = findViewById(R.id.email_edit_text);
        password    = findViewById(R.id.password_edit_text);
        name        = findViewById(R.id.name_edit_text);
        std         = findViewById(R.id.class_edit_text);
        register    = findViewById(R.id.register_btn);
        go_to_login = findViewById(R.id.go_to_login);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        go_to_login.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        register.setOnClickListener(v -> {
            if(isValid())
            {
                registerUser();
            }else {
                Toast.makeText(SignUpActivity.this, "Please fill all the details", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void registerUser() {
        //getting edit text values
        String Name = name.getText().toString();
        String Email = email.getText().toString();
        String pass = password.getText().toString();
        String standard = std.getText().toString();

        //save the values to shared preferences
        sharedPreferences.edit().putString("name",Name).apply();
        sharedPreferences.edit().putString("email",Email).apply();
        sharedPreferences.edit().putString("password",pass).apply();
        sharedPreferences.edit().putString("standard",standard).apply();

        //call the Login activity after signup successfull
        Toast.makeText(this, "Registration Completed", Toast.LENGTH_LONG).show();
        Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(i);
    }

    boolean isValid()
    {
        return !email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()
                && !name.getText().toString().isEmpty() && !std.getText().toString().isEmpty();
    }
}