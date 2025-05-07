package com.example.yallameal.HomeYallaMeal.ui.search;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yallameal.HomeYallaMeal.Categories.Presenter.CategoryPresenter;
import com.example.yallameal.HomeYallaMeal.Categories.Presenter.CategoryPresenterImp;
import com.example.yallameal.HomeYallaMeal.Categories.View.RCategoryAdapter;
import com.example.yallameal.HomeYallaMeal.Countries.Presenter.CountriesPresenter;
import com.example.yallameal.HomeYallaMeal.Countries.Presenter.CountriesPresenterImp;
import com.example.yallameal.HomeYallaMeal.Countries.View.RCountryAdapter;
import com.example.yallameal.HomeYallaMeal.Meals.Presenter.MealsPresenter;
import com.example.yallameal.HomeYallaMeal.Meals.Presenter.MealsPresenterImp;
import com.example.yallameal.HomeYallaMeal.Meals.View.AllMealsView;
import com.example.yallameal.HomeYallaMeal.Meals.View.IallMealClickListener;
import com.example.yallameal.HomeYallaMeal.Meals.View.RIngredientAdapter;
import com.example.yallameal.HomeYallaMeal.Meals.View.RMealAdapter;
import com.example.yallameal.MealDetailFragment;
import com.example.yallameal.Model.Category;
import com.example.yallameal.Model.Country;
import com.example.yallameal.Model.Ingredient;
import com.example.yallameal.Model.Meal;
import com.example.yallameal.Model.MealRepositryImp;
import com.example.yallameal.Network.MealsRemoteDataSourceImp;
import com.example.yallameal.R;
import com.example.yallameal.db.MealsLocalDataSourceImp;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements AllMealsView , IallMealClickListener {

    private enum Mode { CATEGORY, NAME, INGREDIENT, COUNTRY }
    private Mode currentMode = Mode.CATEGORY;

    private AppCompatButton btnCategory, btnName, btnIngredient, btnCountry;
    private EditText editTextSearch;
    private ImageView searchIcon;
    private RecyclerView searchRecycler;

    // Adapters
    private RCategoryAdapter categoryAdapter;
    private RMealAdapter     mealAdapter;
    private RIngredientAdapter ingredientAdapter;
    private RCountryAdapter  countryAdapter;

    // Presenters
    private CategoryPresenter   categoryPresenter;
    private MealsPresenter      mealsPresenter;
    private MealsPresenter ingredientsPresenter;
    private CountriesPresenter  countriesPresenter;

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_serach, container, false);

        // find views
        btnCategory   = root.findViewById(R.id.search_category_btn);
        btnName       = root.findViewById(R.id.search_name_btn);
        btnIngredient = root.findViewById(R.id.btnIngredient);
        btnCountry    = root.findViewById(R.id.btnCountry);
        editTextSearch= root.findViewById(R.id.editTextSearch);
        searchIcon    = root.findViewById(R.id.searchIcon);
        searchRecycler= root.findViewById(R.id.searchRecycler);

        // RecyclerView
        searchRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        categoryAdapter   = new RCategoryAdapter(requireContext(),   new ArrayList<>());
        mealAdapter       = new RMealAdapter(requireContext(),       new ArrayList<>(), this);
        ingredientAdapter = new RIngredientAdapter(requireContext(), new ArrayList<>());
        countryAdapter    = new RCountryAdapter(requireContext(),    new ArrayList<>());

        // Presenters (assume you’ve created these)
        categoryPresenter   = new CategoryPresenterImp(this,
                MealRepositryImp.getInstance(
                        MealsRemoteDataSourceImp.getInstance(requireContext()),
                        MealsLocalDataSourceImp.getInstance(requireContext())
                )
        );
        mealsPresenter      = new MealsPresenterImp(this,                   MealRepositryImp.getInstance(
                MealsRemoteDataSourceImp.getInstance(requireContext()),
                MealsLocalDataSourceImp.getInstance(requireContext())
        ));
        ingredientsPresenter = new MealsPresenterImp(this,                 MealRepositryImp.getInstance(
                MealsRemoteDataSourceImp.getInstance(requireContext()),
                MealsLocalDataSourceImp.getInstance(requireContext())
        ));
        countriesPresenter  = new CountriesPresenterImp(this,                 MealRepositryImp.getInstance(
                MealsRemoteDataSourceImp.getInstance(requireContext()),
                MealsLocalDataSourceImp.getInstance(requireContext())
        ));

        // Button click listeners
        btnCategory.setOnClickListener(v -> switchMode(Mode.CATEGORY));
        btnName.setOnClickListener(v -> switchMode(Mode.NAME));
        btnIngredient.setOnClickListener(v -> switchMode(Mode.INGREDIENT));
        btnCountry.setOnClickListener(v -> switchMode(Mode.COUNTRY));

        // Search icon
        searchIcon.setOnClickListener(v -> {
            String q = editTextSearch.getText().toString().trim();
            if (q.isEmpty()) return;
            switch (currentMode) {
                case CATEGORY:
                    categoryPresenter.getMealWithCategory(q);      break;
                case NAME:
                    mealsPresenter.getMealWithName(q);            break;
                case INGREDIENT:
                    ingredientsPresenter.getMealWithIngredient(q);break;
                case COUNTRY:
                    countriesPresenter.getMealWithArea(q);    break;
            }
        });

        // Start in CATEGORY mode
        switchMode(Mode.CATEGORY);

        return root;
    }

    private void switchMode(Mode m) {
        currentMode = m;
        // reset all buttons...
        btnCategory   .setSelected(m==Mode.CATEGORY);
        btnName       .setSelected(m==Mode.NAME);
        btnIngredient .setSelected(m==Mode.INGREDIENT);
        btnCountry    .setSelected(m==Mode.COUNTRY);

        // change hint
        switch (m) {
            case CATEGORY:   editTextSearch.setHint("Search by Category");   break;
            case NAME:       editTextSearch.setHint("Search by Meal Name"); break;
            case INGREDIENT: editTextSearch.setHint("Search by Ingredient"); break;
            case COUNTRY:    editTextSearch.setHint("Search by Country");   break;
        }

        // show the corresponding adapter immediately (may be empty)
        RecyclerView.Adapter adapter;
        switch (m) {
            case CATEGORY:   adapter = categoryAdapter;   break;
            case NAME:       adapter = mealAdapter;       break;
            case INGREDIENT: adapter = ingredientAdapter; break;
            default:         adapter = countryAdapter;    break;
        }
        searchRecycler.setAdapter(adapter);
    }

    @Override
    public void showData(List<Meal> meals) {
        mealAdapter = new RMealAdapter(requireContext(), meals, this);
        searchRecycler.setAdapter(mealAdapter);
        mealAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCatregoriesData(List<Category> categories) {
        categoryAdapter = new RCategoryAdapter(requireContext(), categories);
        searchRecycler.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void ShowCountriesData(List<Country> countries) {
        countryAdapter = new RCountryAdapter(requireContext(),countries);
        searchRecycler.setAdapter(countryAdapter);
        countryAdapter.notifyDataSetChanged();

    }

    @Override
    public void ShowIngredientData(List<Ingredient> ingredients) {
        ingredientAdapter = new RIngredientAdapter(requireContext(),ingredients);
        searchRecycler.setAdapter(ingredientAdapter);
        ingredientAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnClick_ShowMealDetails(String mealid) {
        MealDetailFragment detailFragment = new MealDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("meal_id", mealid);
        detailFragment.setArguments(bundle);
        NavController navController = Navigation.findNavController(getView());
        navController.navigate(R.id.navigation_meal_detail,bundle);
    }


}


