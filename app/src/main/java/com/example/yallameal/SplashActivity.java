package com.example.yallameal;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.example.yallameal.Register.View.IntroActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
//        LottieAnimationView animationView = findViewById(R.id.splash1);
//        animationView.setAnimation("animation1.json");
//        animationView.playAnimation();

        new Handler().postDelayed(()->{
            Intent intent = new Intent(SplashActivity.this, IntroActivity.class);
            startActivity(intent);
            finish();
        },1000);
//        ImageView gifView = findViewById(R.id.splashGif);
//        Glide.with(this)
//                .asGif()
//                .load(R.drawable.animation2) // No need for .gif extension
//                .into(gifView);
//
    }
}