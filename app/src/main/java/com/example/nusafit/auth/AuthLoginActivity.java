package com.example.nusafit.auth;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nusafit.MainActivity;
import com.example.nusafit.R;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shobhitpuri.custombuttons.GoogleSignInButton;

public class AuthLoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;

    private TextView register, forgotpass;

    private EditText txtEmail, txtPassword;
    private TextInputLayout inputEmail, inputPassword;
    private Button BtnLogin;

//    GoogleSignInButton signInButton;
//    private GoogleApiClient googleApiClient;
//    private static final int SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_login);

        // Firebase
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference();
        mAuth = FirebaseAuth.getInstance();

        txtEmail = findViewById(R.id.edtEmail);
        inputEmail = findViewById(R.id.inputEmail);
        txtEmail.addTextChangedListener(new ValidationTextWatcher(txtEmail));

        txtPassword = findViewById(R.id.edtPassword);
        inputPassword = findViewById(R.id.inputPassword);

        // Login Email
        BtnLogin = (Button) findViewById(R.id.btnLogin);
        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtEmail.getText().toString().trim().isEmpty()) {
                    inputEmail.setError("Mohon isi username dengan email yang benar");
                    return;
                }

                if (txtPassword.getText().toString().trim().isEmpty()) {
                    inputPassword.setError("Mohon isi password");
                    return;
                }
                login();
            }
        });

        // Login Akun Google
//        GoogleSignInOptions gso = new GoogleSignInOptions().Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
//
//        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();

        // Ke Register Activity
        register = (TextView) findViewById(R.id.tvRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AuthRegisterActivity.class));
                kosongkanEdit();
                finish();
            }
        });

        // Ke Forgot Password
        forgotpass = (TextView) findViewById(R.id.tvForgotPassword);
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AuthForgotPasswordActivity.class));
            }
        });
    }

    private void kosongkanEdit() {
        txtEmail.setText("");
        txtPassword.setText("");
    }

    private void login() {
        Log.d(TAG, "login");
        if (!validateForm()) {
            return;
        }

        String email = txtEmail.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            onAuthSuccess();
                        } else {
                            inputPassword.setError("Password yang anda masukkan salah");
                        }
                    }
                });
    }

    private boolean validateForm() {
        boolean result = true;

        if (TextUtils.isEmpty(txtEmail.getText().toString())) {
            txtEmail.setError("Email harus diisi");
            result = false;
        } else {
            txtEmail.setError(null);
        }

        if (TextUtils.isEmpty(txtPassword.getText().toString())) {
            txtPassword.setError("Password harus diisi");
        } else {
            txtPassword.setError(null);
        }

        return result;
    }

    private void onAuthSuccess() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            return;
        } else {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }

    // Validasi Email
    private class ValidationTextWatcher implements TextWatcher {
        private View view;

        public ValidationTextWatcher(View view) {
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

    private boolean validasiEmail() {
        if (txtEmail.getText().toString().trim().isEmpty()) {
            inputEmail.setErrorEnabled(false);
        } else {
            String emailId = txtEmail.getText().toString();
            Boolean isValid = Patterns.EMAIL_ADDRESS.matcher(emailId).matches();
            if (!isValid) {
                inputEmail.setError("Email tidak valid");
                requestFocus(txtEmail);
                return false;
            } else {
                inputEmail.setErrorEnabled(false);
            }
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Apakah anda yakin ingin keluar Aplikasi ?");
         builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialogInterface, int i) {
                 finish();
             }
         });
         builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialogInterface, int i) {
             }
         });
         AlertDialog alertDialog = builder.create();
         alertDialog.show();
    }
}
