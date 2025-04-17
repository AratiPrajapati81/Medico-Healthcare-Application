package com.example.medico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctor_details1=
            {
//                                                                                                                 600 is consultant fee
                    {"Doctor Name : Ajit Saste", "Hospital Address : Pimpri", "Exp : 5yrs", "Mobile No.: 5623272705","600rs"},
                    {"Doctor Name : Prasad Pawar", "Hospital Address : Nigdi", "Exp : 15yrs", "Mobile No.: 5679386053","800rs"},
                    {"Doctor Name : Swapnil Kale", "Hospital Address : Pune", "Exp : 9yrs", "Mobile No.: 89064326786","300rs"},
                    {"Doctor Name : Deepak Deshmukh", "Hospital Address : Chinchwad", "Exp : 6yrs", "Mobile No.: 7249795754","900rs"},
                    {"Doctor Name : Ashok Panda", "Hospital Address : Katraj", "Exp : 10yrs", "Mobile No.: 8983564708","800rs"},
                    {"Doctor Name : Rohit Saxena", "Hospital Address : Khar", "Exp : 8yrs", "Mobile No.: 8988776655", "770rs"},
                    {"Doctor Name : Anusha Gokhale", "Hospital Address : Charni Road", "Exp : 15yrs", "Mobile No.: 7877665544", "1150rs"},
                    {"Doctor Name : Abhishek Pandey", "Hospital Address : Dahisar", "Exp : 10yrs", "Mobile No.: 6766554433", "880rs"},
                    {"Doctor Name : Nisha Arora", "Hospital Address : Bhayandar", "Exp : 7yrs", "Mobile No.: 5655443322", "690rs"},
                    {"Doctor Name : Tarun Bhatia", "Hospital Address : Santacruz", "Exp : 9yrs", "Mobile No.: 4544332211", "820rs"},
                    {"Doctor Name : Vinayak Shinde", "Hospital Address : Versova", "Exp : 6yrs", "Mobile No.: 9090909090", "620rs"},
                    {"Doctor Name : Aditi Bansal", "Hospital Address : Kurla", "Exp : 11yrs", "Mobile No.: 8989898989", "940rs"},
                    {"Doctor Name : Saurabh Nair", "Hospital Address : Goregaon", "Exp : 8yrs", "Mobile No.: 7878787878", "770rs"},
                    {"Doctor Name : Neelam Khanna", "Hospital Address : Mahim", "Exp : 12yrs", "Mobile No.: 6767676767", "990rs"},
                    {"Doctor Name : Pranav Reddy", "Hospital Address : Panvel", "Exp : 5yrs", "Mobile No.: 5656565656", "600rs"}
            };
    private String[][] doctor_details2=
            {
                    {"Doctor Name : Sneha Patil", "Hospital Address : Vashi", "Exp : 6yrs", "Mobile No.: 6543219870", "600rs"},
                    {"Doctor Name : Manish Desai", "Hospital Address : Borivali", "Exp : 9yrs", "Mobile No.: 5432198760", "750rs"},
                    {"Doctor Name : Kavita Rao", "Hospital Address : Dadar", "Exp : 11yrs", "Mobile No.: 4321987650", "850rs"},
                    {"Doctor Name : Sameer Kulkarni", "Hospital Address : Kalyan", "Exp : 7yrs", "Mobile No.: 3219876540", "650rs"},
                    {"Doctor Name : Anjali Joshi", "Hospital Address : Powai", "Exp : 13yrs", "Mobile No.: 2198765430", "1000rs"},
                    {"Doctor Name : Neha Kapoor", "Hospital Address : Mulund", "Exp : 9yrs", "Mobile No.: 4321098765", "780rs"},
                    {"Doctor Name : Ramesh Gupta", "Hospital Address : Navi Mumbai", "Exp : 14yrs", "Mobile No.: 3210987654", "1100rs"},
                    {"Doctor Name : Meenal Dixit", "Hospital Address : Worli", "Exp : 7yrs", "Mobile No.: 2109876543", "670rs"},
                    {"Doctor Name : Akshay Nair", "Hospital Address : Mira Road", "Exp : 11yrs", "Mobile No.: 1098765432", "880rs"},
                    {"Doctor Name : Swati Kulkarni", "Hospital Address : Kandivali", "Exp : 15yrs", "Mobile No.: 1987654321", "1200rs"},
                    {"Doctor Name : Manohar Iyer", "Hospital Address : Dombivli", "Exp : 11yrs", "Mobile No.: 3032121212", "960rs"},
                    {"Doctor Name : Rupal Mehta", "Hospital Address : Wadala", "Exp : 12yrs", "Mobile No.: 2021010101", "980rs"},
                    {"Doctor Name : Vikas Singh", "Hospital Address : Charni Road", "Exp : 7yrs", "Mobile No.: 1012121212", "730rs"},
                    {"Doctor Name : Priyanka Chawla", "Hospital Address : Matunga", "Exp : 13yrs", "Mobile No.: 1122334455", "1040rs"},
                    {"Doctor Name : Rajat Malhotra", "Hospital Address : Seawoods", "Exp : 14yrs", "Mobile No.: 2233445566", "1120rs"}
            };
    private String[][] doctor_details3=
            {
                    {"Doctor Name : Rajesh Mehta", "Hospital Address : Andheri", "Exp : 8yrs", "Mobile No.: 9876543210", "700rs"},
                    {"Doctor Name : Priya Sharma", "Hospital Address : Bandra", "Exp : 10yrs", "Mobile No.: 8765432190", "800rs"},
                    {"Doctor Name : Arvind Nair", "Hospital Address : Thane", "Exp : 12yrs", "Mobile No.: 7654321980", "900rs"},
                    {"Doctor Name : Sneha Patil", "Hospital Address : Vashi", "Exp : 6yrs", "Mobile No.: 6543219870", "600rs"},
                    {"Doctor Name : Manish Desai", "Hospital Address : Borivali", "Exp : 9yrs", "Mobile No.: 5432198760", "750rs"},
                    {"Doctor Name : Megha Choudhary", "Hospital Address : Churchgate", "Exp : 9yrs", "Mobile No.: 4433221100", "800rs"},
                    {"Doctor Name : Sandeep Joshi", "Hospital Address : Jogeshwari", "Exp : 11yrs", "Mobile No.: 3322110099", "890rs"},
                    {"Doctor Name : Pooja Narang", "Hospital Address : Marine Lines", "Exp : 14yrs", "Mobile No.: 2211009988", "1050rs"},
                    {"Doctor Name : Chetan Patil", "Hospital Address : Sewri", "Exp : 5yrs", "Mobile No.: 1100998877", "580rs"},
                    {"Doctor Name : Deepika Menon", "Hospital Address : Byculla", "Exp : 13yrs", "Mobile No.: 9099887766", "990rs"}
            };
    private String[][] doctor_details4=
            {
                    {"Doctor Name : Mohan Gupta", "Hospital Address : Malad", "Exp : 10yrs", "Mobile No.: 5544332211", "950rs"},
                    {"Doctor Name : Rekha Desai", "Hospital Address : Kandivali", "Exp : 12yrs", "Mobile No.: 4433221100", "1000rs"},
                    {"Doctor Name : Aditya Kapoor", "Hospital Address : Santacruz", "Exp : 9yrs", "Mobile No.: 3322110099", "850rs"},
                    {"Doctor Name : Pooja Nair", "Hospital Address : Charni Road", "Exp : 15yrs", "Mobile No.: 2211009988", "1100rs"},
                    {"Doctor Name : Sanjay Thakur", "Hospital Address : Vikhroli", "Exp : 6yrs", "Mobile No.: 1100998877", "550rs"},
                    {"Doctor Name : Aditya Sharma", "Hospital Address : Malad", "Exp : 7yrs", "Mobile No.: 9988776655", "680rs"},
                    {"Doctor Name : Roshni Pillai", "Hospital Address : Vikhroli", "Exp : 10yrs", "Mobile No.: 8877665544", "920rs"},
                    {"Doctor Name : Kunal Tiwari", "Hospital Address : Sion", "Exp : 8yrs", "Mobile No.: 7766554433", "750rs"},
                    {"Doctor Name : Snehal Deshmukh", "Hospital Address : Parel", "Exp : 12yrs", "Mobile No.: 6655443322", "970rs"},
                    {"Doctor Name : Varun Shetty", "Hospital Address : Bhandup", "Exp : 6yrs", "Mobile No.: 5544332211", "640rs"},
                    {"Doctor Name : Kavya Saxena", "Hospital Address : Nerul", "Exp : 6yrs", "Mobile No.: 8087676767", "650rs"},
                    {"Doctor Name : Yashwant Rao", "Hospital Address : Tardeo", "Exp : 15yrs", "Mobile No.: 7076565656", "1180rs"},
                    {"Doctor Name : Smita Jadhav", "Hospital Address : Byculla", "Exp : 10yrs", "Mobile No.: 6065454545", "900rs"},
                    {"Doctor Name : Dinesh Pillai", "Hospital Address : Colaba", "Exp : 8yrs", "Mobile No.: 5054343434", "780rs"},
                    {"Doctor Name : Tanvi Khatri", "Hospital Address : Kanjurmarg", "Exp : 9yrs", "Mobile No.: 4043232323", "850rs"}
            };
    private String[][] doctor_details5=
            {
                    {"Doctor Name : Anjali Joshi", "Hospital Address : Powai", "Exp : 13yrs", "Mobile No.: 2198765430", "1000rs"},
                    {"Doctor Name : Ramesh Iyer", "Hospital Address : Mulund", "Exp : 5yrs", "Mobile No.: 9988776655", "500rs"},
                    {"Doctor Name : Suman Verma", "Hospital Address : Chembur", "Exp : 14yrs", "Mobile No.: 8877665544", "1200rs"},
                    {"Doctor Name : Varun Chatterjee", "Hospital Address : Ghatkopar", "Exp : 16yrs", "Mobile No.: 7766554433", "1300rs"},
                    {"Doctor Name : Neha Agarwal", "Hospital Address : Worli", "Exp : 8yrs", "Mobile No.: 6655443322", "900rs"},
                    {"Doctor Name : Rajesh Verma", "Hospital Address : Thane", "Exp : 8yrs", "Mobile No.: 9876543210", "700rs"},
                    {"Doctor Name : Priya Shah", "Hospital Address : Andheri", "Exp : 10yrs", "Mobile No.: 8765432109", "900rs"},
                    {"Doctor Name : Vikram Mehta", "Hospital Address : Bandra", "Exp : 6yrs", "Mobile No.: 7654321098", "650rs"},
                    {"Doctor Name : Sunita Iyer", "Hospital Address : Chembur", "Exp : 12yrs", "Mobile No.: 6543210987", "950rs"},
                    {"Doctor Name : Arjun Malhotra", "Hospital Address : Ghatkopar", "Exp : 5yrs", "Mobile No.: 5432109876", "600rs"},
                    {"Doctor Name : Karishma Dubey", "Hospital Address : CST", "Exp : 9yrs", "Mobile No.: 4545454545", "830rs"},
                    {"Doctor Name : Anil Chaturvedi", "Hospital Address : Koparkhairane", "Exp : 10yrs", "Mobile No.: 3434343434", "870rs"},
                    {"Doctor Name : Meera Subramanian", "Hospital Address : Grant Road", "Exp : 13yrs", "Mobile No.: 2323232323", "1020rs"},
                    {"Doctor Name : Rohan Pathak", "Hospital Address : Vile Parle", "Exp : 7yrs", "Mobile No.: 1212121212", "720rs"},
                    {"Doctor Name : Pritam Ghosh", "Hospital Address : Ulhasnagar", "Exp : 14yrs", "Mobile No.: 9098787878", "1100rs"},
            };
    TextView tv;
    Button btn;
    String [][] doctor_details = {};
    ArrayList list;
    SimpleAdapter sa;
    HashMap<String,String> item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textViewDDTitle);
        btn = findViewById(R.id.buttonDDBack);


        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physicians")==0)
            doctor_details = doctor_details1;
        else
        if(title.compareTo("Dietician")==0)
            doctor_details = doctor_details2;
        else
        if(title.compareTo("Dentist")==0)
            doctor_details = doctor_details3;
        else
        if(title.compareTo("Surgeon")==0)
            doctor_details = doctor_details4;
        else
            doctor_details = doctor_details5;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });

        list = new ArrayList();
        for (int i=0;i<doctor_details.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5","Cons Fees:"+doctor_details[i][4]+"/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        ListView lst = findViewById(R.id.listViewDD);
        lst.setAdapter(sa);

        //        when we click on any item it will rediect to an book appointment page
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
//                it.putExtra("text5",doctor_details[i][4]);
                it.putExtra("text5", doctor_details[i][4].trim()); // Trim spaces
                startActivity(it);

            }
        });
    }
}