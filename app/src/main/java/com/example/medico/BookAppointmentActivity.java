package com.example.medico;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {

    EditText ed1, ed2, ed3, ed4;
    TextView tv;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton, timeButton, btnBook, btnBack;
    Database db; //  Added Database reference

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        tv = findViewById(R.id.textViewAppTitle);
        ed1 = findViewById(R.id.editTextAppFullName);
        ed2 = findViewById(R.id.editTextAppAddress);
        ed3 = findViewById(R.id.editTextAppContactNumber);
        ed4 = findViewById(R.id.editTextAppFees);
        dateButton = findViewById(R.id.buttonAppDate);
        timeButton = findViewById(R.id.buttonAppTime);
        btnBook = findViewById(R.id.buttonBookAppointment);
        btnBack = findViewById(R.id.buttonAppBack);

        db = new Database(getApplicationContext(), "Medico", null, 1); // ✅ Initialize Database

        // Make fields non-editable
        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        // Fetch appointment details from Intent
        Intent it = getIntent();
        String title = it.getStringExtra("text1");
        String fullname = it.getStringExtra("text2");
        String address = it.getStringExtra("text3");
        String contact = it.getStringExtra("text4");
        String fees = it.getStringExtra("text5");

        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText("Cons Fees: " + fees + "/-");

        // Initialize date & time pickers
        initDatePicker();
        initTimePicker();

        dateButton.setOnClickListener(view -> datePickerDialog.show());
        timeButton.setOnClickListener(view -> timePickerDialog.show());

        btnBack.setOnClickListener(view -> startActivity(new Intent(BookAppointmentActivity.this, FindDoctorActivity.class)));

        btnBook.setOnClickListener(view -> {
            SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
            String username = sharedpreferences.getString("username", null);

            if (username == null) {
                Toast.makeText(getApplicationContext(), "No username found in SharedPreferences!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Username retrieved: " + username, Toast.LENGTH_LONG).show();
            }

            String selectedDate = dateButton.getText().toString();
            String selectedTime = timeButton.getText().toString();
            final String feeStr = fees; // Create a final copy of fees
            float feeValue;

            if (feeStr == null || feeStr.trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fee value is missing!", Toast.LENGTH_LONG).show();
                    return;
            }
            try {
                    String cleanedFee = feeStr.replaceAll("[^\\d.]", ""); // Remove non-numeric characters
                    feeValue = Float.parseFloat(cleanedFee);
            } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid fee format!", Toast.LENGTH_SHORT).show();
                    return;
            }

            Database db = new Database(getApplicationContext(), "Medico", null, 1);
            int appointmentExists = db.checkAppointmentExits(username, title + " => " + fullname, address, contact, selectedDate, selectedTime);
            if (appointmentExists == 1) {
                Toast.makeText(getApplicationContext(), "Appointment Already Booked", Toast.LENGTH_LONG).show();
            } else {
                db.addOrder(username, title + " => " + fullname, address, contact, 0, selectedDate, selectedTime, feeValue, "appointment");
                Toast.makeText(getApplicationContext(), "Your Appointment is done Successfully", Toast.LENGTH_LONG).show();
                startActivity(new Intent(BookAppointmentActivity.this, HomeActivity.class));
            }
        });
    }
    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, i, i1, i2) -> {
            i1 = i1 + 1;
            dateButton.setText(i2 + "/" + i1 + "/" + i);
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_DARK, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis() + 86400000);
    }
    private void initTimePicker() {
        TimePickerDialog.OnTimeSetListener timeSetListener = (timePicker, i, i1) -> timeButton.setText(i + ":" + i1);

        Calendar cal = Calendar.getInstance();
        int hrs = cal.get(Calendar.HOUR);
        int mins = cal.get(Calendar.MINUTE);
        timePickerDialog = new TimePickerDialog(this, AlertDialog.THEME_HOLO_DARK, timeSetListener, hrs, mins, true);
    }
}
