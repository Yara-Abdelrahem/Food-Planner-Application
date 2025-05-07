package com.example.yallameal.HomeYallaMeal.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
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
import com.example.yallameal.HomeYallaMeal.Meals.View.RMealAdapter;
import com.example.yallameal.MealDetailFragment;
import com.example.yallameal.Model.Country;
import com.example.yallameal.Model.Ingredient;
import com.example.yallameal.Model.Meal;
import com.example.yallameal.Model.MealRepositryImp;
import com.example.yallameal.Model.Category;
import com.example.yallameal.Network.MealsRemoteDataSourceImp;
import com.example.yallameal.R;
import com.example.yallameal.Register.View.IntroActivity;
import com.example.yallameal.databinding.FragmentHomeBinding;
import com.example.yallameal.db.MealsLocalDataSourceImp;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements AllMealsView , IallMealClickListener {

    private FragmentHomeBinding binding;
    RMealAdapter rMealAdapter;
    RCategoryAdapter rCategoryAdapter;
    RCountryAdapter rCountryAdapter;
    List<Meal> suggest_meal = new ArrayList<>();
    List<Category> categories= new ArrayList<>();
    List<Country> countries = new ArrayList<>();
    View root;
    RecyclerView suggestrecyclerview , categoriesrecyclerView , countryrecyclerView;
    LinearLayoutManager suggestlayoutManager , categorieslayoutManager , countrylayoutManager;
    MealsPresenter mealsPresenter;
    CategoryPresenter categoryPresenter;
    CountriesPresenter countriesPresenter;
    Button logout_btn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        return root;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        NavController navController = Navigation.findNavController(view);
//        navController.navigate(R.id.nav_host_fragment_activity_home);

        // Init RecyclerView and layout
        suggestrecyclerview = view.findViewById(R.id.SuggestRecyclerView1);
        categoriesrecyclerView=view.findViewById(R.id.CategoryRecyclerView1);
        countryrecyclerView = view.findViewById(R.id.CountryRecyclerView1);
        logout_btn=view.findViewById(R.id.Logout_btn);

        categorieslayoutManager = new LinearLayoutManager(requireContext());
        suggestlayoutManager = new LinearLayoutManager(requireContext());
        countrylayoutManager = new LinearLayoutManager(requireContext());

        // Init presenter
        mealsPresenter = new MealsPresenterImp(this,
                MealRepositryImp.getInstance(
                        MealsRemoteDataSourceImp.getInstance(requireContext()),
                        MealsLocalDataSourceImp.getInstance(requireContext())
                )
        );

        categoryPresenter = new CategoryPresenterImp(this ,
                MealRepositryImp.getInstance(
                        MealsRemoteDataSourceImp.getInstance(requireContext()),
                        MealsLocalDataSourceImp.getInstance(requireContext())
                )
        );
        countriesPresenter = new CountriesPresenterImp(this ,
                MealRepositryImp.getInstance(
                        MealsRemoteDataSourceImp.getInstance(requireContext()),
                        MealsLocalDataSourceImp.getInstance(requireContext())
                )
                );

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), IntroActivity.class);
                startActivity(intent);

            }
        });

        suggestrecyclerview.setLayoutManager(suggestlayoutManager);
        categoriesrecyclerView.setLayoutManager(categorieslayoutManager);
        countryrecyclerView.setLayoutManager(countrylayoutManager);
        mealsPresenter.getRandomMeal();
        categoryPresenter.getAllCategories();
        countriesPresenter.getAllCountries();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void OnClick_ShowMealDetails(String meal_id) {
        MealDetailFragment detailFragment = new MealDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("meal_id", meal_id);
        detailFragment.setArguments(bundle);
        NavController navController = Navigation.findNavController(getView());
        navController.navigate(R.id.navigation_meal_detail,bundle);
    }

    @Override
    public void showData(List<Meal> meals) {
        rMealAdapter = new RMealAdapter(requireContext(), meals, this);
        suggestrecyclerview.setAdapter(rMealAdapter);
        rMealAdapter.notifyDataSetChanged();

    }

    @Override
    public void showCatregoriesData(List<Category> categories) {
        rCategoryAdapter = new RCategoryAdapter(requireContext(), categories);
        categoriesrecyclerView.setAdapter(rCategoryAdapter);
        rCategoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void ShowCountriesData(List<Country> countries) {
        rCountryAdapter = new RCountryAdapter(requireContext(),countries);
        countryrecyclerView.setAdapter(rCountryAdapter);
        rCountryAdapter.notifyDataSetChanged();

    }

    @Override
    public void ShowIngredientData(List<Ingredient> ingredients) {

    }
}