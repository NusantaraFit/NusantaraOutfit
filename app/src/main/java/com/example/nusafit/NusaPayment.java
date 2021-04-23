package com.example.nusafit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nusafit.UI.FragmentProfile;

public class NusaPayment extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_nusapay);

//        ArrayList<Pay> arrayPay = new ArrayList<>();
//        PayAdapter adapter = new PayAdapter(this, arrayPay);
//        ListView listView = (ListView) findViewById(R.id.list);
//        listView.setAdapter(adapter);
//
//        Pay pay = new Pay(R.drawable.logo, "Fill in the balance", "11.12.21", "+ 7.750.000", "Success");
//        adapter.add(pay);
//
//        pay = new Pay(R.drawable.logo, "Payment", "11.12.21", "+ 10.000.000", "Succes");
//        adapter.add(pay);
//
//        pay = new Pay(R.drawable.logo, "Transfer", "11.12.21", "+ 100.000.000", "Failed");
//        adapter.add(pay);
    }
    public void PreviousNP(View view) {
        Intent intent = new Intent(this, FragmentProfile.class);
        startActivity(intent);
    }
}
