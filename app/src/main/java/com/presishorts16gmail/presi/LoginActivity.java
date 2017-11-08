package com.presishorts16gmail.presi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private Button btnLogin;
    private Button btnReset;
    private Button btnSignup;
    private EditText inputEmail;
    private EditText inputPassword;
    private ProgressBar progressBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.auth = FirebaseAuth.getInstance();
        if (this.auth.getCurrentUser() != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        setContentView(R.layout.activity_login);
        this.inputEmail = (EditText) findViewById(R.id.email);
        this.inputPassword = (EditText) findViewById(R.id.password);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.btnSignup = (Button) findViewById(R.id.btn_signup);
        this.btnLogin = (Button) findViewById(R.id.btn_login);
        this.btnReset = (Button) findViewById(R.id.btn_reset_password);
        this.auth = FirebaseAuth.getInstance();
        this.btnSignup.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                LoginActivity.this.startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });
        this.btnReset.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                LoginActivity.this.startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
            }
        });
        this.btnLogin.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String email = LoginActivity.this.inputEmail.getText().toString();
                final String password = LoginActivity.this.inputPassword.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(LoginActivity.this.getApplicationContext(), "Enter email address!", 0).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this.getApplicationContext(), "Enter password!", 0).show();
                } else {
                    LoginActivity.this.progressBar.setVisibility(0);
                    LoginActivity.this.auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            LoginActivity.this.progressBar.setVisibility(8);
                            if (task.isSuccessful()) {
                                LoginActivity.this.startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                LoginActivity.this.finish();
                            } else if (password.length() < 6) {
                                LoginActivity.this.inputPassword.setError(LoginActivity.this.getString(R.string.minimum_password));
                            } else {
                                Toast.makeText(LoginActivity.this, LoginActivity.this.getString(R.string.auth_failed), 1).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
