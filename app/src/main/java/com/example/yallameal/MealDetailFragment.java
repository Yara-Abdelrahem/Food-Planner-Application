package com.example.yallameal;

import android.app.DatePickerDialog;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yallameal.FavoritMeals.View.IMealClickListener;
import com.example.yallameal.HomeYallaMeal.Meals.Presenter.MealsPresenter;
import com.example.yallameal.HomeYallaMeal.Meals.Presenter.MealsPresenterImp;
import com.example.yallameal.HomeYallaMeal.Meals.View.AllMealsView;
import com.example.yallameal.HomeYallaMeal.Meals.View.IngredientAdapter;
import com.example.yallameal.HomeYallaMeal.Meals.View.Ingredient_Dtails;
import com.example.yallameal.HomeYallaMeal.Meals.View.RMealDetailsAdapter;
import com.example.yallameal.Model.Category;
import com.example.yallameal.Model.Country;
import com.example.yallameal.Model.Ingredient;
import com.example.yallameal.Model.Meal;
import com.example.yallameal.Model.MealRepositryImp;
import com.example.yallameal.Model.MealSchedule;
import com.example.yallameal.Network.MealsRemoteDataSourceImp;
import com.example.yallameal.db.MealsLocalDataSourceImp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MealDetailFragment extends Fragment implements AllMealsView , IMealClickListener {
    private ImageView imageMeal , addfav , addtocalender;
    private TextView textMealName, textMealOrigin, textMealIngredients, textMealInstructions;
    private WebView webViewMealVideo;
    private MealsPresenter mealsPresenter;
    DatabaseReference dbRef;
    FirebaseUser user;
    FirebaseAuth mAuth ;

    View view;
    Meal meal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meal_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
         mAuth = FirebaseAuth.getInstance();
         user = mAuth.getCurrentUser();
        dbRef = FirebaseDatabase.getInstance("https://yalla-meal-69c2d-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference();

        if (user != null) {
            String uid = user.getUid();
            String email = user.getEmail();

            DatabaseReference userRef = dbRef.child("users").child(uid);
            userRef.child("email").setValue(email); // store email
        }


        // Initialize UI components
        imageMeal = view.findViewById(R.id.image_meal);
        textMealName = view.findViewById(R.id.text_meal_name);
        textMealOrigin = view.findViewById(R.id.text_meal_origin);
        textMealInstructions = view.findViewById(R.id.text_meal_instructions);
        webViewMealVideo = view.findViewById(R.id.webview_meal_video);
        addfav = view.findViewById(R.id.addmealfavorite);
        addtocalender = view.findViewById(R.id.addtocalender);
        // Retrieve meal ID from arguments
        String mealId = getArguments().getString("meal_id");

        // Initialize presenter
        mealsPresenter = new MealsPresenterImp(this,
                MealRepositryImp.getInstance(
                        MealsRemoteDataSourceImp.getInstance(requireContext()),
                        MealsLocalDataSourceImp.getInstance(requireContext())
                )
        );
        mealsPresenter.getMealWithID(mealId);
        addfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null && mealId != null) {
                    mealsPresenter.addToFav(meal);
                    dbRef.child("users").child(user.getUid()).child("favorites").child(mealId)
                            .setValue(true)
                            .addOnSuccessListener(aVoid -> Log.d("FIREBASE", "Meal added to favorites"))
                            .addOnFailureListener(e -> Log.e("FIREBASE", "Failed to add meal", e));

                } else {
                    Log.e("FIREBASE", "User or mealId is null");
                }
            }
        });
        addtocalender.setOnClickListener(v -> {
            // Get current date
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    requireContext(),
                    (datePickerView, selectedYear, selectedMonth, selectedDay) -> {
                        // +1 because month index starts from 0
                        String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        mealsPresenter.insertschedulemeal(new MealSchedule(meal, date));
                    },
                    year, month, day
            );
            datePickerDialog.show();

        });


    }

    @Override
    public void showData(List<Meal> meals) {
        if (meals != null && !meals.isEmpty()) {
             meal = meals.get(0);

            // Load meal image
            Glide.with(requireContext())
                    .load(meal.getStrMealThumb())
                    .into(imageMeal);

            // Set meal name and origin
            textMealName.setText(meal.getStrMeal());
            textMealOrigin.setText("Origin: " + meal.getStrArea());

            // Set ingredients and measurements

            RecyclerView recyclerIngredients = view.findViewById(R.id.recycler_ingredients);
            recyclerIngredients.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            List<Ingredient_Dtails> ingredientList = new ArrayList<>();
            try {
                for (int i = 1; i <= 20; i++) {
                    String ingredientName = (String) Meal.class.getMethod("getStrIngredient" + i).invoke(meal);
                    if (ingredientName != null && !ingredientName.isEmpty()) {
                        String imageUrl = "https://www.themealdb.com/images/ingredients/" + ingredientName + ".png";
                        ingredientList.add(new Ingredient_Dtails(ingredientName, imageUrl));
                    }
                }
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
                // Optional: Show a user-friendly message or log error
            }

            IngredientAdapter ingredientAdapter = new IngredientAdapter(getContext(), ingredientList);
            recyclerIngredients.setAdapter(ingredientAdapter);

            // Set preparation steps
            textMealInstructions.setText(meal.getStrInstructions());

            // Load YouTube video
            String videoUrl = meal.getStrYoutube();
            if (videoUrl != null && !videoUrl.isEmpty()) {
                String videoId = Uri.parse(videoUrl).getQueryParameter("v");
                String embedHtml = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"
                        + videoId + "\" frameborder=\"0\" allowfullscreen></iframe>";
                webViewMealVideo.getSettings().setJavaScriptEnabled(true);
                webViewMealVideo.loadData(embedHtml, "text/html", "utf-8");
            }
        }
    }

    @Override
    public void showCatregoriesData(List<Category> categories) {

    }

    @Override
    public void ShowCountriesData(List<Country> countries) {

    }

    @Override
    public void ShowIngredientData(List<Ingredient> ingredients) {

    }

    @Override
    public void AddMealFav(String mealid) {

//        mealsPresenter.addToFav(mealid);
        dbRef.child("users").child(user.getUid()).child("favorites").child(mealid).setValue(true);

    }

    @Override
    public void AddMealCalender(String mealid, String date) {

    }

}
