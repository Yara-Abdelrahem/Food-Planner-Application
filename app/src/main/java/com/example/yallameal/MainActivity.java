package com.example.yallameal;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.yallameal.Model.Meal;
import com.example.yallameal.Network.NetworkCallback;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NetworkCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onSuccessResutl(List<Meal> meals) {

    }

    @Override
    public void onFailure(String errormsg) {

    }
}