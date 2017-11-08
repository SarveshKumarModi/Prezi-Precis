package com.presishorts16gmail.presi;

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
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private Button btnBack;
    private Button btnReset;
    private EditText inputEmail;
    private ProgressBar progressBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        this.inputEmail = (EditText) findViewById(R.id.email);
        this.btnReset = (Button) findViewById(R.id.btn_reset_password);
        this.btnBack = (Button) findViewById(R.id.btn_back);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.auth = FirebaseAuth.getInstance();
        this.btnBack.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                ResetPasswordActivity.this.finish();
            }
        });
        this.btnReset.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String email = ResetPasswordActivity.this.inputEmail.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(ResetPasswordActivity.this.getApplication(), "Enter your registered email id", 0).show();
                    return;
                }
                ResetPasswordActivity.this.progressBar.setVisibility(0);
                ResetPasswordActivity.this.auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ResetPasswordActivity.this, "We have sent you instructions to reset your password!", 0).show();
                        } else {
                            Toast.makeText(ResetPasswordActivity.this, "Failed to send reset email!", 0).show();
                        }
                        ResetPasswordActivity.this.progressBar.setVisibility(8);
                    }
                });
            }
        });
    }
}
