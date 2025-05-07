//package com.example.yallameal.HomeYallaMeal;
//
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.widget.Toast;
//
//import com.example.yallameal.R;
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.navigation.NavController;
//import androidx.navigation.Navigation;
//import androidx.navigation.ui.AppBarConfiguration;
//import androidx.navigation.ui.NavigationUI;
//
//import com.example.yallameal.databinding.ActivityHomeBinding;
//
//public class HomeActivity extends AppCompatActivity {
//
//    private ActivityHomeBinding binding;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityHomeBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        // 👇 This is the missing piece that caused the crash
//
//        BottomNavigationView navView = findViewById(R.id.nav_view);
//
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_Home, R.id.navigation_search, R.id.navigation_Calender, R.id.navigation_Favorite, R.id.navigation_Profile)
//                .build();
//
//        NavController navController = Navigation.findNavController(this, R.id.mealDetailFragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);
//
//        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefsFile", MODE_PRIVATE);
//        String userEmail = sharedPreferences.getString("user_email", null);
//
//        if (userEmail != null) {
//            // Use the email as needed
//            Toast.makeText(this, "Welcome back, " + userEmail, Toast.LENGTH_SHORT).show();
//        }
//
//    }
//
//}
package com.example.yallameal.HomeYallaMeal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.yallameal.R;
import com.example.yallameal.databinding.ActivityHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set the Toolbar as the ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configure BottomNavigationView
        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Set up AppBarConfiguration with top-level destinations
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_Home, R.id.navigation_search, R.id.navigation_Calender,
                R.id.navigation_Favorite, R.id.navigation_Profile)
                .build();

        // Set up NavController
        navController = Navigation.findNavController(this, R.id.mealDetailFragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        // Handle SharedPreferences for user email
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefsFile", MODE_PRIVATE);
        String userEmail = sharedPreferences.getString("user_email", null);

        if (userEmail != null) {
            // Display welcome message
            Toast.makeText(this, "Welcome back, " + userEmail, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Handle Toolbar's back arrow click
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}