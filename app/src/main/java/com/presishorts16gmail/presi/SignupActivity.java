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

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private Button btnResetPassword;
    private Button btnSignIn;
    private Button btnSignUp;
    private EditText inputEmail;
    private EditText inputPassword;
    private ProgressBar progressBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        this.auth = FirebaseAuth.getInstance();
        this.btnSignIn = (Button) findViewById(R.id.sign_in_button);
        this.btnSignUp = (Button) findViewById(R.id.sign_up_button);
        this.inputEmail = (EditText) findViewById(R.id.email);
        this.inputPassword = (EditText) findViewById(R.id.password);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.btnResetPassword = (Button) findViewById(R.id.btn_reset_password);
        this.btnResetPassword.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                SignupActivity.this.startActivity(new Intent(SignupActivity.this, ResetPasswordActivity.class));
            }
        });
        this.btnSignIn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                SignupActivity.this.finish();
            }
        });
        this.btnSignUp.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String email = SignupActivity.this.inputEmail.getText().toString().trim();
                String password = SignupActivity.this.inputPassword.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(SignupActivity.this.getApplicationContext(), "Enter email address!", 10).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(SignupActivity.this.getApplicationContext(), "Enter password!", 14).show();
                } else if (password.length() < 6) {
                    Toast.makeText(SignupActivity.this.getApplicationContext(), "Password too short, enter minimum 6 characters!", 10).show();
                } else {
                    SignupActivity.this.progressBar.setVisibility(0);
                    SignupActivity.this.auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(SignupActivity.this.getApplicationContext(), "Email Taken" + task.isSuccessful(), 12).show();
                            SignupActivity.this.progressBar.setVisibility(12);
                            if (task.isSuccessful()) {
                                SignupActivity.this.startActivity(new Intent(SignupActivity.this, MainActivity.class));
                                SignupActivity.this.finish();
                                return;
                            }
                            Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException(), 12).show();
                        }
                    });
                }
            }
        });
    }

    protected void onResume() {
        super.onResume();
        this.progressBar.setVisibility(8);
    }
}
