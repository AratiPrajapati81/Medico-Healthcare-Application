package com.example.medico;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BuyMedicineDetailsActivity extends AppCompatActivity {

    TextView tvPackageName, tvTotalCost;
    EditText edDetails;
    Button btnBack , btnAddToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_details);

        tvPackageName = findViewById(R.id.textViewBMCartTitle);
        edDetails = findViewById(R.id.editTextTextBMDMultiLine);
        edDetails.setKeyListener(null);
        tvTotalCost = findViewById(R.id.textViewBMDTotalCost);
        btnBack = findViewById(R.id.buttonBMCartBack);
        btnAddToCart = findViewById(R.id.buttonBMCartCheckout);

        Intent intent = getIntent();
        tvPackageName.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("Total Cost: "+intent.getStringExtra("text3")+"/-");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineDetailsActivity.this, BuyMedicineActivity.class));
            }
        });

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedpreferences.getString("username", "").toString();
                String product = tvPackageName.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db = new Database(getApplicationContext(), "Medico", null, 1);

                if (db.checkCart(username,product)==1){
                    Toast.makeText(getApplicationContext(), "Product Already Added", Toast.LENGTH_SHORT).show();
                }else{
                     db.addCart(username,product,price, "medicine");
                     Toast.makeText(getApplicationContext(), "Record Inserted to Cart", Toast.LENGTH_SHORT).show();
                     startActivity(new Intent(BuyMedicineDetailsActivity.this, BuyMedicineActivity.class));
                }
            }
        });
    }
}