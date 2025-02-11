package com.prm2.miniproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    // View
    private EditText etUsername;
    private EditText etPassword;
    private TextView tvCreateAccount;
    private Button btnSignIn;

    // Notify
    private final String REQUIRE = "Require";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Reference From layout
        etUsername = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);
        tvCreateAccount = findViewById(R.id.create_account);
        btnSignIn = findViewById(R.id.sign_in_button);

        // Register event
        tvCreateAccount.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(etUsername.getText().toString())) {
            etUsername.setError(REQUIRE);
            return false;
        }

        if (TextUtils.isEmpty(etPassword.getText().toString())) {
            etPassword.setError(REQUIRE);
            return false;
        }

        return true;
    }

    private void login() {
        if (checkInput()) {
            String inputUsername = etUsername.getText().toString();
            String inputPassword = etPassword.getText().toString();

            SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
            String storedUsername = sharedPreferences.getString("username", null);
            String storedPassword = sharedPreferences.getString("password", null);

            if (storedUsername != null && storedPassword != null &&
                    storedUsername.equals(inputUsername) && storedPassword.equals(inputPassword)) {
                Toast.makeText(this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                // Navigate to the next activity if needed
                navigateToNextActivity();
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void navigateToSignUp() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    private void navigateToNextActivity() {
        Intent intent = new Intent(this, MainActivity.class); // Replace with your target activity
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.sign_in_button) {
            login();
        } else if (id == R.id.create_account) {
            navigateToSignUp();
        }
    }
}
