package com.example.yallameal.Register.View;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yallameal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText emailEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();  // Initialize Firebase Auth:contentReference[oaicite:2]{index=2}.
        emailEditText = findViewById(R.id.email_signup_edit_txt);
        passwordEditText = findViewById(R.id.password_signup_edit_txt);
        Button signupButton = findViewById(R.id.signInBtn);

        signupButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            // Basic input validation
            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(SignUpActivity.this,
                        "Email and password are required.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (password.length() < 6) {
                Toast.makeText(SignUpActivity.this,
                        "Password must be at least 6 characters.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create a new user with email and password
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign-up success: send verification email (next step)
                                FirebaseUser user = mAuth.getCurrentUser();
                                sendEmailVerification(user);
                            } else {
                                // If sign-up fails, show error message
                                String error = task.getException().getMessage();
                                Toast.makeText(SignUpActivity.this,
                                        "Sign-up failed: " + error,
                                        Toast.LENGTH_LONG).show();
                                Log.e("Error in Sign up",error);
                            }
                        }
                    });
        });
    }
    private void sendEmailVerification(FirebaseUser user) {
        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this,
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_LONG).show();
                            mAuth.signOut();  // Optional: sign out until verification.
                        } else {
                            Toast.makeText(SignUpActivity.this,
                                    "Failed to send verification email: " + task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }

}
