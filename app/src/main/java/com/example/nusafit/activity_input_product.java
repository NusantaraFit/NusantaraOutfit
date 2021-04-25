package com.example.nusafit;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nusafit.entity.Produk;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_input_product extends AppCompatActivity {
    Button btn_delete, btn_save;
    TextView txt_namaProduk, txt_deskripsi, txt_harga, txt_warna, txt_ukuran;
    EditText edt_naprod, edt_desk, edt_harga, edt_warna, edt_ukuran;
    DatabaseReference references;
    Produk produk;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_product);
        //EditText
        edt_naprod = findViewById(R.id.edt_naprod);
        edt_desk = findViewById(R.id.edt_desk);
        edt_harga = findViewById(R.id.edt_harga);
        edt_warna = findViewById(R.id.edt_warna);
        edt_ukuran = findViewById(R.id.edt_ukuran);

        //TextView
        txt_namaProduk = findViewById(R.id.txt_namaProduk);
        txt_deskripsi = findViewById(R.id.txt_deskripsi);
        txt_harga = findViewById(R.id.txt_harga);
        txt_warna = findViewById(R.id.txt_warna);
        txt_ukuran = findViewById(R.id.txt_ukuran);

        //Button
        btn_save = findViewById(R.id.btn_save);
        btn_delete = findViewById(R.id.btn_delete);

        references = FirebaseDatabase.getInstance().getReference().child("Produk");

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int harga = Integer.parseInt(txt_harga.getText().toString().trim());
                int ukuran = Integer.parseInt(txt_ukuran.getText().toString().trim());

                produk.setNama_produk(txt_namaProduk.getText().toString().trim());
                produk.setDeskripsi(txt_deskripsi.getText().toString().trim());
                produk.setWarna(txt_warna.getText().toString().trim());
                produk.setHarga(harga);
                produk.setUkuran(ukuran);
                references.push().setValue(produk);
                Toast.makeText(activity_input_product.this, "Produk Berhasil Di Posting", Toast.LENGTH_LONG).show();
            }
        });

        ImageButton back;

    }


    public void back(View view) {
        finish();
    }
}
