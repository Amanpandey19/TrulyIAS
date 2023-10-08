package com.aman.trulyias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText email, pass;
    SharedPreferences sharedPreferences;
    TextView signUp;
    Button loginBtn;
    private static final String SHARED_PREF_NAME = "mypref";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email     = findViewById(R.id.email_edit_text);
        pass      = findViewById(R.id.password_edit_text);
        loginBtn  = findViewById(R.id.login_btn);
        signUp    = findViewById(R.id.sign_up_here);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        //check if already logged in
        if(sharedPreferences.getBoolean("login",false))
        {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

        loginBtn.setOnClickListener(v -> {
            if(isValid())
            {
                if(detailsMatch())
                    loginUser();
                else
                    Toast.makeText(LoginActivity.this, "Wrong User Details or the user is not registered", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(LoginActivity.this, "please fill all the details", Toast.LENGTH_SHORT).show();
            }
        });

        signUp.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
            finish();
        });


    }

    public boolean isValid()
    {
        return !email.getText().toString().isEmpty() && !pass.getText().toString().isEmpty();
    }

    public boolean detailsMatch()
    {
        String emailAddress = sharedPreferences.getString("email","");
        String password     = sharedPreferences.getString("password","");
        return email.getText().toString().equals(emailAddress) && pass.getText().toString().equals(password);
    }

    public void loginUser()
    {
        sharedPreferences.edit().putBoolean("login",true).apply();
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}