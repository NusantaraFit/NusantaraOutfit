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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.text.Html.fromHtml;

public class AuthRegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private TextView tvBackToLogin;
    private EditText TxtName,TxtEmail, TxtPassword, TxtConPassword;
    private Button BtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_register);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        TxtName = (EditText) findViewById(R.id.txtNameReg);
        TxtEmail = (EditText) findViewById(R.id.txtEmailReg);
        TxtPassword = (EditText) findViewById(R.id.txtPasswordReg);
        TxtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        TxtConPassword = (EditText) findViewById(R.id.txtConPasswordReg);
        TxtConPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        BtnRegister = (Button) findViewById(R.id.btnRegister);
        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
                new Intent(getApplicationContext(), MainActivity.class);
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

        String email = TxtEmail.getText().toString();
        String password = TxtPassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "register:onComplete:" + task.isSuccessful());

                        if (task.isSuccessful()) {
                            onAuthSuccess(task.getResult().getUser());
                        } else {
                            Toast.makeText(AuthRegisterActivity.this, "Register Failed :(, Please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    //Auth success Register
    private void onAuthSuccess(FirebaseUser user) {
        String username = TxtName.getText().toString();

        writeNewUser(user.getUid(), username, user.getEmail());

        Toast.makeText(AuthRegisterActivity.this, "Register Done !!!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), AuthLoginActivity.class));
        finish();
    }

    private void writeNewUser(String userID, String username, String email) {
        UserNF userNF = new UserNF (username, email);

        mDatabase.child("userNF").child(userID).setValue(userNF);
    }

    //Form validasi register
    private boolean validateForm() {
        boolean result = true;

        //Validasi Nama
        if (TextUtils.isEmpty(TxtName.getText().toString())) {
            TxtName.setError("Tolong masukkan nama yang benar");
            result = false;
        } else {
            TxtName.setError(null);
        }

        //Validasi Email
        if (TextUtils.isEmpty(TxtEmail.getText().toString())) {
            TxtEmail.setError("Tolong masukkan alamat email yang benar");
            result = false;
        } else {
            TxtEmail.setError(null);
        }

        //Validasi Password
        String password = TxtPassword.getText().toString().trim();
        String conpassword = TxtConPassword.getText().toString().trim();

        if (password.isEmpty()) {
            TxtPassword.setText("Password tidak boleh kosong");
            result = false;
        } if (password.length()<5) {
            TxtPassword.setText("Password harus lebih dari 5 karakter");
            result = false;
        }

        if (!password.equals(conpassword)) {
            TxtConPassword.setText("Password tidak sama");
            result = false;
        } else {
            TxtConPassword.setText("Password sama");
            result = true;
        }

        return result;
    }
}
