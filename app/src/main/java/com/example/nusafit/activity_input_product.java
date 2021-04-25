package com.example.nusafit;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nusafit.auth.AuthLoginActivity;
import com.example.nusafit.entity.Produk;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

public class activity_input_product extends AppCompatActivity {

    //Firebase
    private FirebaseStorage storage;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseDatabase database;
    private FirebaseUser usernf;
    private DatabaseReference references;
    private String getUserID;

    private Button btn_delete, btn_save;
    private TextInputLayout txt_namaProduk, txt_deskripsi, txt_harga, txt_warna, txt_ukuran;
    private EditText edt_naprod, edt_desk, edt_harga, edt_warna, edt_ukuran;
    Produk produk;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_product);

        // Firebase
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();
        references = database.getReference();
        auth = FirebaseAuth.getInstance();
        usernf = auth.getCurrentUser();
        getUserID = usernf.getUid();

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

//        references = FirebaseDatabase.getInstance().getReference().child("Produk");
        references.child("usernf").child(getUserID).child("jualan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Produk detailProduk = snapshot.getValue(Produk.class);

                if (detailProduk != null) {
                    detailProduk.setKey(snapshot.getKey());

                    edt_naprod.setText(detailProduk.getNama_produk());
                    edt_desk.setText(detailProduk.getDeskripsi());
                    edt_harga.setText(detailProduk.getHarga());
                    edt_warna.setText(detailProduk.getWarna());
                    edt_ukuran.setText(detailProduk.getUkuran());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Data Produk Gagal Dimuat", Toast.LENGTH_LONG).show();
                Log.e("MyData", error.getDetails() + " " + error.getMessage());
            }
        });

        // Button save Jualan
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validasiInput()) {
                    return;
                }
                addJualan();
                Toast.makeText(getApplicationContext(),"Produk sukses di Jual", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        // Button delete Jualan
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hapusJualan();
                Toast.makeText(getApplicationContext(), "Produk berhasil dihapus", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    private void hapusJualan() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setTitle("Apakah anda yakin untuk menghapus produk jualan anda ?");

        builder.setPositiveButton("HAPUS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                hapusDataJualan();
            }
        });

        builder.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
    }

    private void hapusDataJualan() {
        references.child("usernf").child(getUserID).child("jualan").removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.e("MyData", ": Jualan Berhasil dihapus");
            }
        });
    }

    private boolean validasiInput() {
        Boolean result = false;

        if (edt_naprod.getText().toString().trim().isEmpty()) {
            edt_naprod.setError("Mohon isi nama produk yang akan dijual");
        } else if (edt_desk.getText().toString().trim().isEmpty()) {
            edt_desk.setError("Mohon isi deskripsi produk yang akan dijual");
        } else if (edt_harga.getText().toString().trim().isEmpty()) {
            edt_harga.setError("Mohon isi harga produk yang akan dijual");
        } else if (edt_warna.getText().toString().trim().isEmpty()) {
            edt_warna.setError("Mohon isi warna produk yang akan dijual");
        } else if (edt_ukuran.getText().toString().trim().isEmpty()) {
            edt_ukuran.setError("Mohon isi ukuran produk yang akan dijual");
        } else {
            result = true;
        }

        return result;
    }

    private void addJualan() {
        String idUserNF = getUserID;
        String namaProduk = edt_naprod.getText().toString().trim();
        String deskProduk = edt_desk.getText().toString().trim();
        String hargaProduk = edt_harga.getText().toString().trim();
        String warnaProduk = edt_warna.getText().toString().trim();
        String ukuranProduk = edt_ukuran.getText().toString().trim();

        Produk detailProduk = new Produk(namaProduk, deskProduk, hargaProduk, warnaProduk, ukuranProduk);

        references.child("usernf").child(idUserNF).child("jualan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Produk dataDetailProduk = snapshot.getValue(Produk.class);

                references.child("barangjualan").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Produk detailProduk1 = dataSnapshot.getValue(Produk.class);
                            if (detailProduk1.getKey() != null && dataDetailProduk.getKey() != null) {
                                if (detailProduk1.getKey().equals(dataDetailProduk.getKey())) {
                                    references.child("barangjualan").child(detailProduk1.getKey()).child("data").setValue(detailProduk);
                                }
                            } else {
                                return;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("MyData", error.getDetails() + " " + error.getMessage());
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("MyData", error.getDetails() + " " + error.getMessage());
            }
        });

        references.child("barangjualan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Produk dataDetailProduk = dataSnapshot.getValue(Produk.class);
                    dataDetailProduk.setKey(dataSnapshot.getKey());

                    if (dataDetailProduk.equals(getUserID)) {
                        references.child("barangjualan").child(dataDetailProduk.getKey()).child("data").setValue(detailProduk);

                        detailProduk.setKey(dataDetailProduk.getKey());
                        references.child("usernf").child(getUserID).child("jualan").setValue(detailProduk);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("MyData", error.getDetails() + " " + error.getMessage());
            }
        });
    }

    public void back(View view) {
        finish();
    }
}
