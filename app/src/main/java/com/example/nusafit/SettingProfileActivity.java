package com.example.nusafit;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nusafit.entity.UserNF;
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

public class SettingProfileActivity extends AppCompatActivity {

    private static final String TAG = "Edit Profile";

    // Firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseDatabase mDatabase;
    private FirebaseUser usernf;
    private DatabaseReference mReference;
    private String getUserID;

    private TextInputLayout inputNamaLengkap, inputJenkel, inputTtl, inputAlamat, inputAgama, inputEmail;
    private EditText edtNamaLengkap, edtJenkel, edtTtl, edtAlamat, edtAgama, edtEmail;

    private Button btnSimpanprof;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_profile);

        // Firebase
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference();
        mAuth = FirebaseAuth.getInstance();
        usernf = mAuth.getCurrentUser();
        getUserID = usernf.getUid();

        inputNamaLengkap = findViewById(R.id.inputNamaLengkap);
        inputJenkel = findViewById(R.id.inputJenkel);
        inputTtl = findViewById(R.id.inputTtl);
        inputAlamat = findViewById(R.id.inputAlamat);
        inputAgama = findViewById(R.id.inputAgama);
        inputEmail = findViewById(R.id.inputEmail);

        edtNamaLengkap = findViewById(R.id.edtNamaLengkap);
        edtJenkel = findViewById(R.id.edtJenkel);
        edtTtl = findViewById(R.id.edtTtl);
        edtAlamat = findViewById(R.id.edtAlamat);
        edtAgama = findViewById(R.id.edtAgama);
        edtEmail = findViewById(R.id.edtEmail);

        btnSimpanprof = (Button) findViewById(R.id.btnSimpanprof);
        btnSimpanprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editData();

                if (edtNamaLengkap.getText().toString().trim().isEmpty()) {
                    inputNamaLengkap.setError("Nama tidak boleh kosong");
                    return;
                }

                if (edtJenkel.getText().toString().trim().isEmpty()) {
                    inputJenkel.setError("Jenis kelamin tidak boleh kosong");
                    return;
                }

                if (edtTtl.getText().toString().trim().isEmpty()) {
                    inputTtl.setError("Tempat tanggal lahir tidak boleh kosong");
                    return;
                }

                if (edtAlamat.getText().toString().trim().isEmpty()) {
                    inputAlamat.setError("Alamat tidak boleh kosong");
                    return;
                }

                if (edtAgama.getText().toString().trim().isEmpty()) {
                    inputAgama.setError("Agama tidak boleh kosong");
                    return;
                }

                if (edtEmail.getText().toString().trim().isEmpty()) {
                    inputEmail.setError("Email tidak boleh kosong");
                    return;
                }
            }
        });

        mReference.child("userNF").child(getUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserNF userNF = snapshot.getValue(UserNF.class);
                if (userNF.getAlamat() == null) {
                    edtNamaLengkap.setText(userNF.getNamalengkap());
                    edtEmail.setText(userNF.getEmail());
                } else {
                    edtNamaLengkap.setText(userNF.getNamalengkap());
                    edtEmail.setText(userNF.getEmail());
                    edtTtl.setText(userNF.getTtl());
                    edtJenkel.setText(userNF.getJenkel());
                    edtAlamat.setText(userNF.getAlamat());
                    edtAgama.setText(userNF.getAgama());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("MyData", error.getDetails() + " " + error.getMessage());
            }
        });
    }

    private void editData() {
        Log.d(TAG, "daftar");
        if (!validateFrom()) {
            return;
        }

        String nama = edtNamaLengkap.getText().toString().trim();
        String jenkel = edtJenkel.getText().toString().trim();
        String ttl = edtTtl.getText().toString().trim();
        String alamat = edtAlamat.getText().toString().trim();
        String agama = edtAgama.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();

        UserNF userNF = new UserNF(nama, jenkel, ttl, alamat, agama, email);

        if (!usernf.getEmail().equals(email)) {
            updateEmail(email);
        } mReference.child("userNF").child(getUserID).setValue(userNF).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Data berhasil diubah", Toast.LENGTH_SHORT).show();
            }
        });
        finish();
    }

    private boolean validateFrom() {
        boolean result = true;

        if (TextUtils.isEmpty(edtNamaLengkap.getText().toString())) {
            result = false;
        } else {
            edtNamaLengkap.setError(null);
        }

        if (TextUtils.isEmpty(edtEmail.getText().toString())) {
            result = false;
        } else {
            edtEmail.setError(null);
        }
        return result;
    }

    private void updateEmail(String email) {
        usernf.updateEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Email berhasil diubah", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onKembali(View view) {
        finish();
    }
}
