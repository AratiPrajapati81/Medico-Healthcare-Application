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

public class BuyMedicineActivity extends AppCompatActivity {
    private String[][] packages =
            {
                    {"Dettol Antiseptic Liquid", "", "", "", "195"},
                    {"Dolo 650 Tablet", "", "", "", "30"},
                    {"ORS Electral Powder", "", "", "", "30"},
                    {"Glucon-D Instant Energy Drink Powder", "", "", "", "225"},
                    {"Moov Pain Relief Cream", "", "", "", "199"},
                    {"Iodex Balm", "", "", "", "90"},
                    {"Vicks Vaporub", "", "", "", "130"},
                    {"Himalaya Ashwagandha Tablet", "", "", "", "190"},
                    {"Himalaya Liv 52 DS Tablet", "", "", "", "175"},
                    {"Volini Pain Relief Spray", "", "", "", "250"},
                    {"Benadryl Cough Syrup", "", "", "", "120"},
                    {"A to Z Gold Multivitamin Capsule", "", "", "", "330"},

                    {"Uprise-03 1000IU Capsule", "", "","", "50"},
                    {"HealthVit Chromium Picolinate 200mcg Capsule", "", "", "", "305"},
                    {"Vitamin B Complex Capsules", "", "", "", "448"},
                    {"Inlife Vitamin E Wheat Germ Oil Capsule", "", "", "", "539"},
                    {"Crocin 650 Advance Tablet", "", "", "", "50"},
                    {"Strepsils Medicated Lozenges for Sore Throat", "", "", "", "48"},
                    {"Tata 1mg Calcium Vitamin 03", "", "", "", "38"},
                    {"Feronia -XT Tablet", "", "", "", "130"},

            };

    private String[] package_details = {
            "Dettol Antiseptic Liquid helps in disinfecting wounds, preventing infections, and maintaining hygiene by killing harmful germs.",
            "Dolo 650 Tablet helps relieve pain and fever by blocking the release of certain chemical messengers responsible for fever and pain.",
            "ORS Electral Powder helps replenish lost fluids and electrolytes in the body, preventing dehydration caused by diarrhea, vomiting, or excessive sweating.",
            "Glucon-D Instant Energy Drink Powder provides instant energy and helps in quick recovery from fatigue and dehydration.",
            "Moov Pain Relief Cream helps relieve muscle pain, back pain, and sprains by providing fast and long-lasting relief with its soothing ingredients.",
            "Iodex Balm helps reduce muscle stiffness, sprains, and joint pain by penetrating deep into the skin and providing warmth.",
            "Vicks Vaporub helps provide relief from cough, nasal congestion, and cold symptoms by its menthol and eucalyptus-based vapors.",
            "Himalaya Ashwagandha Tablet helps in reducing stress, improving immunity, and enhancing overall energy and stamina naturally.",
            "Himalaya Liv 52 DS Tablet helps in promoting liver health, detoxifying the liver, and improving digestion and appetite.",
            "Volini Pain Relief Spray provides instant relief from muscle pain, joint pain, and sprains by numbing the affected area and reducing inflammation.",
            "Benadryl Cough Syrup helps relieve cough and throat irritation by soothing the throat and reducing mucus production.",
            "A to Z Gold Multivitamin Capsule helps in fulfilling daily nutritional needs, improving immunity, and boosting overall energy and well-being.",

            "Building and keeping the bones & teeth strong\n" +
                    "Reducing Fatigue/stress and muscular pains\n" +
                    "Boosting immunity and increasing resistance against infection",

            "Chromium is an essential trace mineral that plays an important role in helping insulin regulate blood glucose",
            "Provides relief from vitamin B deficiencies\n" +
                    "Helps in formation of red blood cells\n" +
                    "Maintains healthy nervous system",
            "It promotes health as well as skin benefit.\n" +
                    "It helps reduce skin blemish and pigmentation.\n" +
                    "It act as safeguard the skin from the harsh UVA and UVB sun rays.",

            "Helps relieve fever and bring down a high temperature\n" +
                    "Suitable for people with a heart condition or high blood pressure",
            "Relieves the symptoms of a bacterial throat infection and soothes the recovery process\n" +
                    "Provides a warm and comforting feeling during sore throat",
            "Reduces the risk of calcium deficiency, Rickets, and Osteoporosis\n" +
                    "Promotes mobility and flexibility of joints",
            "Helps to reduce the iron deficiency due to chronic blood loss or low intake of iron"
    };


    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btnBack, btnGoToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        lst = findViewById(R.id.listViewBMCart);
        btnBack = findViewById(R.id.buttonBMCartBack);
        btnGoToCart = findViewById(R.id.buttonBMCartCheckout);

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this, CartBuyMedicineActivity.class));
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this, HomeActivity.class));
            }
        });

        list = new ArrayList();
        for (int i=0;i<packages.length; i++){
            item = new HashMap<String,String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", "Total Cost: "+packages[i][4] +"/-");
            list.add(item);
        }

        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(BuyMedicineActivity.this, BuyMedicineDetailsActivity.class);
                it.putExtra("text1", packages[i][0]);
                it.putExtra("text2", package_details[i]);
                it.putExtra("text3", packages[i][4]);
                startActivity(it);
            }
        });




    }
}