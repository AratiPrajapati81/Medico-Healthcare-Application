package com.example.medico;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;

public class ClientActivity extends AppCompatActivity {
    Button btnLogout;
    ListView lstOrders;
    SimpleAdapter sa;
    ArrayList<HashMap<String, String>> orderList;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        btnLogout = findViewById(R.id.buttonLogout);
        lstOrders = findViewById(R.id.listViewOrders);
        db = new Database(getApplicationContext(), "Medico", null, 1);

        //  Fetch All Orders for Client
        ArrayList<String> dbData = db.getAllOrders();
        orderList = new ArrayList<>();

        for (String data : dbData) {
            String[] orderData = data.split("\\$");
            HashMap<String, String> item = new HashMap<>();
            item.put("line1", "User: " + orderData[0]);
            item.put("line2", "Order: " + orderData[1]);
            item.put("line3", "Address: " + orderData[2]);
            item.put("line4", "Contact: " + orderData[3]);
            item.put("line5", "Date: " + orderData[5]);
//            item.put("line5", "Date: " + orderData[4] + " " + orderData[5]);
            item.put("line6", "Pincode: " + orderData[4]);
            orderList.add(item);
        }

        sa = new SimpleAdapter(this, orderList,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        lstOrders.setAdapter(sa);

        //  Handle Click on a User Order
        lstOrders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                HashMap<String, String> selectedOrder = orderList.get(position);
                Intent intent = new Intent(ClientActivity.this, UserDetailsActivity.class);
                intent.putExtra("username", selectedOrder.get("line1").replace("User: ", ""));
                intent.putExtra("order", selectedOrder.get("line2").replace("Order: ", ""));
                intent.putExtra("address", selectedOrder.get("line3").replace("Address: ", ""));
                intent.putExtra("contact", selectedOrder.get("line4").replace("Contact: ", ""));
                intent.putExtra("date", selectedOrder.get("line5").replace("Date: ", ""));
                intent.putExtra("pincode", selectedOrder.get("line6").replace("Pincode: ", ""));
                startActivity(intent);
            }
        });

        //  Logout Button
        btnLogout.setOnClickListener(view -> {
            SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("username");
            editor.apply();

            Toast.makeText(ClientActivity.this, "Logged out!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ClientActivity.this, LoginActivity.class));
            finish();
        });
    }
}
