package com.example.nusafit.UI;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nusafit.adapterHomeProduk;
import com.example.nusafit.MyDataProduct;
import com.example.nusafit.R;

public class ViewHome extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        RecyclerView recyclerView = findViewById(R.id.recyclerHome);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        MyDataProduct[] dataProducts = new MyDataProduct[]{
                new MyDataProduct(R.drawable.icon_hoodie, "Hoddie", "150000"),
                new MyDataProduct(R.drawable.cwek, "cwok", "120000"),
                new MyDataProduct(R.drawable.cwok, "cwek", "120000"),
                new MyDataProduct(R.drawable.logo, "logo", "250000"),
                new MyDataProduct(R.drawable.clothes, "clothes", "300000")

        };
//        adapterHomeProduk adapterHomeProduk = new adapterHomeProduk(dataProducts, ViewHome.this);
//        recyclerView.setAdapter(adapterHomeProduk);
    }
}
