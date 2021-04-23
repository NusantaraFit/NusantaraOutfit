package com.example.nusafit.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nusafit.MainActivity;
import com.example.nusafit.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AuthLoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;

    private TextView register, forgotpass;

    EditText TxtUsername, TxtPassword;
    Button BtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_login);

        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference();
        mAuth = FirebaseAuth.getInstance();

        TxtUsername = (EditText) findViewById(R.id.txtUsernameLog);
        TxtPassword = (EditText) findViewById(R.id.txtPasswordLog);

        //Login
        BtnLogin = (Button) findViewById(R.id.btnLogin);
        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TxtUsername.getText().toString().trim().isEmpty()) {
                    TxtUsername.setError("Mohon isi username dengan email yang benar");
                    return;
                }

                if (TxtPassword.getText().toString().trim().isEmpty()) {
                    TxtPassword.setError("Mohon isi password");
                    return;
                }
                login();
            }
        });

        //KeRegisterActivity
        register = (TextView) findViewById(R.id.tvRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AuthRegisterActivity.class));
            }
        });

        //KeForgotPassword
        forgotpass = (TextView) findViewById(R.id.tvForgotPassword);
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AuthForgotPasswordActivity.class));
            }
        });
    }

    private void login() {
        String email = TxtUsername.getText().toString().trim();
        String password = TxtPassword.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            onAuthSuccess();
                        } else {
                            TxtPassword.setError("Password yang anda masukkan salah");
                        }
                    }
                });
    }

    private void onAuthSuccess() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}
