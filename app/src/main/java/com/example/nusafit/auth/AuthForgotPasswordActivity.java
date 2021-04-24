package com.example.nusafit.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nusafit.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class AuthForgotPasswordActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    private TextInputLayout inputLayoutEmail;
    private EditText edtEmailForgPass;

    private Button btnConfirm;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_forgot_password);

        auth = FirebaseAuth.getInstance();

        edtEmailForgPass = findViewById(R.id.edtEmailForgPass);
        inputLayoutEmail = findViewById(R.id.iLayoutEmail);

        progressBar = findViewById(R.id.progressBar);

        btnConfirm = findViewById(R.id.btnConfirmForPass);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmailForgPass.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    edtEmailForgPass.setError("Please enter your email");
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
                                    Toast.makeText(getApplicationContext(), "Gagal mengirim konfirmasi ubah password", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private boolean validasiEmail() {
        if (edtEmailForgPass.getText().toString().trim().isEmpty()) {
            inputLayoutEmail.setErrorEnabled(false);
        } else {
            String emailId = edtEmailForgPass.getText().toString();
            Boolean isValid = Patterns.EMAIL_ADDRESS.matcher(emailId).matches();
            if (!isValid) {
                inputLayoutEmail.setError("Email tidak valid");
                requestFocus(edtEmailForgPass);
                return false;
            } else {
                inputLayoutEmail.setErrorEnabled(false);
            }
        }
        return true;
    }

    private class ValidationTextWatcher implements TextWatcher {
        private View view;

        private ValidationTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.edtEmail:
                    validasiEmail();
                    break;
            }
        }
    }
}