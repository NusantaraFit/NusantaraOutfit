package com.example.nusafit.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.nusafit.R;

public class AuthForgotPasswordConfirm extends AppCompatActivity {

    TextView resendEmail, reLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_forgot_password_confirm);

        resendEmail = findViewById(R.id.txtResend);
        resendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AuthForgotPasswordActivity.class));
            }
        });

        reLogin = findViewById(R.id.txtRelog);
        reLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AuthLoginActivity.class));
            }
        });
    }
}