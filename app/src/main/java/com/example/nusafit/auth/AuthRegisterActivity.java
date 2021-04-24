package com.example.nusafit.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nusafit.MainActivity;
import com.example.nusafit.R;
import com.example.nusafit.entity.UserNF;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import static android.text.Html.fromHtml;

public class AuthRegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private EditText edtName,edtEmail, edtPassword, edtConPassword;
    private TextInputLayout inputNama, inputEmail, inputPassword, inputConPassword;

    private TextView tvBackToLogin;
    private Button BtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_register);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        edtName = findViewById(R.id.masukNama);
        inputNama = findViewById(R.id.inputNama);
        edtEmail = findViewById(R.id.masukEmail);
        inputEmail = findViewById(R.id.inputEmail);
        edtPassword = findViewById(R.id.masukPassword);
        inputPassword = findViewById(R.id.inputPassword);
        edtConPassword = findViewById(R.id.masukConPassword);
        inputConPassword = findViewById(R.id.inputConPassword);

        BtnRegister = (Button) findViewById(R.id.btnRegister);
        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();

                // Cek Inputan Register
                if (edtName.getText().toString().trim().isEmpty()) {
                    inputNama.setError("Nama tidak boleh kosong");
                    return;
                }

                if (edtEmail.getText().toString().trim().isEmpty()) {
                    inputEmail.setError("Email tidak boleh kosong");
                    return;
                }

                if (edtPassword.getText().toString().trim().isEmpty()) {
                    inputPassword.setError("Password tidak boleh kosong");
                    return;
                } String pass = edtPassword.getText().toString();
                if (TextUtils.isEmpty(pass) || pass.length() < 6) {
                    inputPassword.setError("Password minimal membutuhkan 6 karakter");
                    return;
                }

                if (edtConPassword.getText().toString().trim().isEmpty()) {
                    inputConPassword.setError("Masukkan Konfirmasi Password");
                }
            }
        });
        tvBackToLogin = (TextView) findViewById(R.id.tvBackToLogin);
        tvBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AuthLoginActivity.class));
                finish();
            }
        });
    }

    private void register() {
        Log.d(TAG, "register");
        if (!validateForm()) {
            return;
        }

        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "register:onComplete:" + task.isSuccessful());

                        if (task.isSuccessful()) {
                            onAuthSuccess(task.getResult().getUser());
                        } else {
                            Toast.makeText(AuthRegisterActivity.this, "Registrasi Gagal, Mohon coba lagi", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    //Auth success Register
    private void onAuthSuccess(FirebaseUser user) {
        String namalengkap = edtName.getText().toString();

        writeNewUser(user.getUid(), namalengkap, user.getEmail());

        Toast.makeText(AuthRegisterActivity.this, "Registrasi Berhasil !", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), AuthLoginActivity.class));
        finish();
    }

    private void writeNewUser(String userID, String namalengkap, String email) {
        UserNF userNF = new UserNF (namalengkap, email);

        mDatabase.child("userNF").child(userID).setValue(userNF);
    }

    // Validasi register
    private boolean validateForm() {
        boolean result = true;

        // Validasi Nama
        if (TextUtils.isEmpty(edtName.getText().toString())) {
            result = false;
        } else {
            edtName.setError(null);
        }

        // Validasi Email
        if (TextUtils.isEmpty(edtEmail.getText().toString())) {
            result = false;
        } else {
            edtEmail.setError(null);
        }

        // Validasi Password

        // Password
        if (TextUtils.isEmpty(edtPassword.getText().toString())) {
            result = false;
        } else {
            inputPassword.setError(null);
        }

        // Konfirmasi Password
        if (TextUtils.isEmpty(edtConPassword.getText().toString())) {
            inputConPassword.setError("Masukkan Ulang Password untuk Konfirmasi");
            result = false;
        } else {
            inputConPassword.setError(null);
        }

        // Cek kesamaan password dan confirm password
        if (!edtConPassword.getText().toString().equals(edtPassword.getText().toString())) {
            inputConPassword.setError("Password tidak sama");
            result = false;
        } else {
            inputConPassword.setError(null);
        }

        return result;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), AuthLoginActivity.class));
        finish();
    }
}
