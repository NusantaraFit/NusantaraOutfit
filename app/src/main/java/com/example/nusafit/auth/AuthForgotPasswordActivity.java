package com.example.nusafit.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nusafit.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class AuthForgotPasswordActivity extends AppCompatActivity {

    private Button btnConfirm;
    private EditText txtEmail;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_forgot_password);

        txtEmail = findViewById(R.id.txtEmailForgot);
        progressBar = findViewById(R.id.progressBar);
        auth = FirebaseAuth.getInstance();

        btnConfirm = findViewById(R.id.btnConfirmForPass);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    txtEmail.setError("Please enter your email");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                progressBar.setVisibility(View.GONE);

                                if (task.isSuccessful()) {
                                    startActivity(new Intent(getApplicationContext(), AuthForgotPasswordConfirm.class));
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Failed to change password", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}