package com.prm2.miniproject;

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

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    // View
    private EditText etUsername;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private TextView tvAlreadyAccount;
    private Button btnSignUp;

    // Notify
    private final String REQUIRE = "Require";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Reference From layout
        etUsername = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);
        etConfirmPassword = findViewById(R.id.confirm_password);
        tvAlreadyAccount = findViewById(R.id.alreadyHaveAccount);
        btnSignUp = findViewById(R.id.btn_signup);

        // Register event
        tvAlreadyAccount.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
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

        if (TextUtils.isEmpty(etConfirmPassword.getText().toString())) {
            etConfirmPassword.setError(REQUIRE);
            return false;
        }

        if (!TextUtils.equals(etPassword.getText().toString(), etConfirmPassword.getText().toString())) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    private void saveUserDetails(String username, String password) {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.apply();
    }

    private void navigateToSignIn() {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
        finish(); // Close current activity
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.alreadyHaveAccount) {
            navigateToSignIn();
        } else if (id == R.id.btn_signup) {
            if (checkInput()) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                saveUserDetails(username, password);
                Toast.makeText(this, "Signed up successfully", Toast.LENGTH_SHORT).show();
                navigateToSignIn();
            }
        }
    }
}
