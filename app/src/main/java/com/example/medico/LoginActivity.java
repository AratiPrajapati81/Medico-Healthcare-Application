package com.example.medico;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText edUsername, edPassword;
    Button btnLogin, btnClientLogin;
    CheckBox cbShowPassword;
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUsername = findViewById(R.id.editTextLoginUsername);
        edPassword = findViewById(R.id.editTextLoginPassword);
        btnLogin = findViewById(R.id.buttonLogin);
        btnClientLogin = findViewById(R.id.buttonClientLogin);
        cbShowPassword = findViewById(R.id.checkBoxShowPassword);
        tvRegister = findViewById(R.id.textViewNewUser);

        //  Toggle Password Visibility
        cbShowPassword.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                edPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            } else {
                edPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
            edPassword.setSelection(edPassword.getText().length());  // ✅ Keep cursor at the end
        });

        //  Handle Normal User Login
        btnLogin.setOnClickListener(view -> {
            String username = edUsername.getText().toString().trim();
            String password = edPassword.getText().toString().trim();
            Database db = new Database(getApplicationContext(), "Medico", null, 1);

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
                return;
            }

            if (db.login(username, password) == 1) {
                Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();

                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", username);
                editor.apply();

                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Invalid username and password", Toast.LENGTH_SHORT).show();
            }
        });
        // Handle Client Login
        btnClientLogin.setOnClickListener(view -> showClientLoginDialog());
        //  Redirect to Register Activity
        tvRegister.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
    }
    //  Prompt User for Client Credentials
    private void showClientLoginDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Client Login");

        final EditText inputUsername = new EditText(this);
        inputUsername.setHint("Enter Client Username");
        final EditText inputPassword = new EditText(this);
        inputPassword.setHint("Enter Client Password");
        inputPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(20, 20, 20, 20);
        layout.addView(inputUsername);
        layout.addView(inputPassword);
        builder.setView(layout);

        builder.setPositiveButton("Login", (dialog, which) -> {
            String enteredUsername = inputUsername.getText().toString().trim();
            String enteredPassword = inputPassword.getText().toString().trim();

            if (enteredUsername.equals("Client") && enteredPassword.equals("Client@123")) {
                Toast.makeText(getApplicationContext(), "Client Login Successful", Toast.LENGTH_SHORT).show();
                //  Store login session for Client
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", "Client");
                editor.apply();
                //  Redirect to ClientActivity
                Intent intent = new Intent(LoginActivity.this, ClientActivity.class);
                startActivity(intent);
                finish(); // Close LoginActivity
            } else {
                Toast.makeText(getApplicationContext(), "Invalid Client Credentials", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", null);
        builder.show();
    }
}
