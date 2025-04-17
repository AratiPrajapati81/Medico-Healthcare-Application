package com.example.medico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestActivity extends AppCompatActivity {
    private String[][] packages =
            {
                    {"Package 1 : Full Body Checkup","","","","999"},
                    {"Package 2 : Blood Glucose Fasting","","","","299"},
                    {"Package 3 : COVID-19 Antibody - IgG","","","","899"},
                    {"Package 4 : Thyroid Check","","","","499"},
                    {"Package 5 : Immunity Check","","","","699"},
//                    from here
                    {"Package 6 : Kidney Function Test (KFT)", "", "", "", "749"},
                    {"Package 7 : Lipid Profile Test", "", "", "", "599"},
                    {"Package 8 : Vitamin D Test", "", "", "", "899"},
                    {"Package 9 : HbA1c Test (Diabetes Monitoring)", "", "", "", "499"},
                    {"Package 10 : Dengue NS1 Antigen Test", "", "", "", "699"},
                    {"Package 11 : HIV 1 & 2 Test", "", "", "", "999"},
                    {"Package 12 : Testosterone Test", "", "", "", "849"},
            };
    private String[] package_details = {
            "Blood Glucose Fasting\n" +
                    "Complete Hemegram\n" +
                    "HbA1c\n" +
                    "Iron Studies\n" +
                    "Kidney Function Test\n" +
                    "LDH Lactate Dehydrogenase, Serum\n" +
                    "Lipid Profile\n" +
                    "Liver Function Test\n" +
                    "Blood Glucose Fasting",

            "COVID-19 Antibody - IgG",

            "Thyroid Profile=Total (T3,T4 & TSH Ultra-sensitive)",

            "Complete Hemegram\n" +
                    "CRP (C Reactive Protein) Quantitative, Serum\n" +
                    "Iron Studies\n" +
                    "Kidney Function Test\n" +
                    "Vitamin D Total-25 Hydroxy\n" +
                    "Liver Function Test\n" +
                    "Lipid Profile",

            "Immunity Check\n" +
                    "Complete Blood Count (CBC)\n" +
                    "Vitamin B12\n" +
                    "Zinc Levels, Serum\n" +
                    "Ferritin\n" +
                    "Total Immunoglobulin (IgG, IgA, IgM)\n" +
                    "Absolute Lymphocyte Count",
//from here
            "Kidney Function Test\n" +
                    "Electrolytes Test\n" +
                    "Urine Routine & Microscopy\n" +
                    "Complete Blood Count (CBC)\n" +
                    "Blood Glucose Fasting",

            "Lipid Profile\n" +
                    "Liver Function Test\n" +
                    "LDH Lactate Dehydrogenase, Serum\n" +
                    "Complete Blood Count (CBC)",

            "Vitamin D Test\n" +
                    "Vitamin B12 Test\n" +
                    "Calcium & Phosphorus Levels\n" +
                    "Electrolytes Test",

            "HbA1c Test (Diabetes Monitoring)\n" +
                    "Blood Glucose Fasting\n" +
                    "Lipid Profile\n" +
                    "Liver Function Test\n" +
                    "Kidney Function Test",

            "Dengue NS1 Antigen Test\n" +
                    "Malaria Antigen Test\n" +
                    "Complete Blood Count (CBC)\n" +
                    "C-Reactive Protein (CRP) Test",

            "HIV 1 & 2 Test\n" +
                    "Hepatitis B & C Screening\n" +
                    "Complete Blood Count (CBC)",

            "Testosterone Test\n" +
                    "Rheumatoid Factor (RA) Test\n" +
                    "C-Reactive Protein (CRP) Test\n" +
                    "Liver Function Test",
    };
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnGoToCart, btnBack;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);

        btnGoToCart= findViewById(R.id.buttonBMCartCheckout);
        btnBack= findViewById(R.id.buttonBMCartBack);
        listView= findViewById(R.id.listViewBMCart);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this, HomeActivity.class));
            }
        });

        list = new ArrayList();
        for (int i=0;i<packages.length;i++){
            item = new HashMap<String,String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", "Total Cost:"+packages[i][4]+"/-");
            list.add(item);
        }

        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        listView.setAdapter(sa);

        //        when we click on any item it will rediect to an book appointment page
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(LabTestActivity.this, LabTestDetailsActivity.class);
                it.putExtra("text1", packages[i][0]);
                it.putExtra("text2", package_details[i]);
                it.putExtra("text3", packages[i][4]);
                startActivity(it);
            }
        });

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this, CartLabActivity.class));
            }
        });
    }
}