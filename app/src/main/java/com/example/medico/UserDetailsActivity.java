package com.example.medico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserDetailsActivity extends AppCompatActivity {
    TextView txtUsername, txtOrder, txtAddress, txtContact, txtDate, txtPin;
    Button btnDeliverOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        txtUsername = findViewById(R.id.textViewUsername);
        txtOrder = findViewById(R.id.textViewOrder);
        txtAddress = findViewById(R.id.textViewAddress);
        txtContact = findViewById(R.id.textViewContact);
        txtDate = findViewById(R.id.textViewDate);
        txtPin = findViewById(R.id.textViewPincode);
        btnDeliverOrder = findViewById(R.id.buttonDeliverOrder);

        //  Get User Details from Intent
        Intent intent = getIntent();
        txtUsername.setText("Username: " + intent.getStringExtra("username"));
        txtOrder.setText("Order: " + intent.getStringExtra("order"));
        txtAddress.setText("Address: " + intent.getStringExtra("address"));
        txtContact.setText("Contact: " + intent.getStringExtra("contact"));
        txtDate.setText("Date: " + intent.getStringExtra("date"));
        txtPin.setText("Pincode: " + intent.getStringExtra("pincode"));

        //  Handle Delivery Button Click
        btnDeliverOrder.setOnClickListener(view -> {
            Toast.makeText(UserDetailsActivity.this, "Delivery Successful!", Toast.LENGTH_SHORT).show();

            //  Redirect Back to ClientActivity
            Intent backIntent = new Intent(UserDetailsActivity.this, ClientActivity.class);
            startActivity(backIntent);
            finish();
        });
    }
}
