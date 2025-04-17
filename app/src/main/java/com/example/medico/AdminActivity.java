package com.example.medico;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

// AdminActivity.java (Admin Page)
public class AdminActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    TextView txtClientDetails;
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> userList;
    ArrayList<Integer> userIds;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        //  Initialize Views
        txtClientDetails = findViewById(R.id.txtClientDetails);
        //  Initialize Database
        db = new Database(getApplicationContext(), "Medico", null, 1);
        txtClientDetails.setText(db.getClientDetails());
        //  Debug: Log all users in the database
        db.logAllUsers();

        listView = findViewById(R.id.listView);
        databaseHelper = new DatabaseHelper(this);

        //  Initialize User List
        userList = new ArrayList<>();
        userIds = new ArrayList<>();
        loadUsers();

        // Handle User Deletion
        listView.setOnItemClickListener((parent, view, position, id) -> {
            int userId = userIds.get(position);
            databaseHelper.deleteUser(userId);
            Toast.makeText(this, "User Deleted", Toast.LENGTH_SHORT).show();
            loadUsers();
        });
    }
    private void loadUsers() {
        userList.clear();
        userIds.clear();
        Cursor cursor = databaseHelper.getAllUsers();
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String username = cursor.getString(1);
                userList.add(id + ". " + username);
                userIds.add(id);
            } while (cursor.moveToNext());
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userList);
        listView.setAdapter(adapter);
        cursor.close();
    }
}
