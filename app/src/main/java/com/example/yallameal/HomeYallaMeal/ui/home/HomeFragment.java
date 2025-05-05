package com.example.yallameal.HomeYallaMeal.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
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
import com.example.yallameal.Model.Country;
import com.example.yallameal.Model.Ingredient;
import com.example.yallameal.Model.Meal;
import com.example.yallameal.Model.MealRepositryImp;
import com.example.yallameal.Model.Category;
import com.example.yallameal.Network.MealsRemoteDataSourceImp;
import com.example.yallameal.R;
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

        // Init RecyclerView and layout
        suggestrecyclerview = view.findViewById(R.id.SuggestRecyclerView1);
        categoriesrecyclerView=view.findViewById(R.id.CategoryRecyclerView1);
        countryrecyclerView = view.findViewById(R.id.CountryRecyclerView1);

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

        suggestrecyclerview.setLayoutManager(suggestlayoutManager);
        categoriesrecyclerView.setLayoutManager(categorieslayoutManager);
        countryrecyclerView.setLayoutManager(countrylayoutManager);
        mealsPresenter.getMealWithID("52772");
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
    public void AddMealToFav(Meal meal) {

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