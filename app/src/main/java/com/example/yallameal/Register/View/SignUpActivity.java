package com.example.yallameal.Register.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.yallameal.Login.View.LoginActivity;
import com.example.yallameal.R;

public class SignUpActivity extends AppCompatActivity {
    Button LoginnupMailBtn;
    Intent login_intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        LoginnupMailBtn=findViewById(R.id.loginLink_btn);
        LoginnupMailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_intent = new Intent(SignUpActivity.this , LoginActivity.class);
                startActivity(login_intent);
                finish();
            }
        });
    }
}