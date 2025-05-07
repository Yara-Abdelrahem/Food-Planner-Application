package com.example.yallameal.HomeYallaMeal.ui.favorite;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yallameal.HomeYallaMeal.Meals.Presenter.MealsPresenter;
import com.example.yallameal.HomeYallaMeal.Meals.Presenter.MealsPresenterImp;
import com.example.yallameal.HomeYallaMeal.Meals.View.AllMealsView;
import com.example.yallameal.MealDetailFragment;
import com.example.yallameal.Model.Category;
import com.example.yallameal.Model.Country;
import com.example.yallameal.Model.Ingredient;
import com.example.yallameal.Model.Meal;
import com.example.yallameal.Model.MealRepositryImp;
import com.example.yallameal.Network.MealsRemoteDataSourceImp;
import com.example.yallameal.R;
import com.example.yallameal.databinding.FragmentFavoriteBinding;
import com.example.yallameal.db.MealsLocalDataSourceImp;

import java.util.List;

public class FavoriteFragment extends Fragment implements AllMealsView, IallMealClickListener {

    private FragmentFavoriteBinding binding;
    MealsPresenter mealsPresenter;
    RecyclerView recyclerView;

    LinearLayoutManager linearLayoutManager;
    FavoriteAdapter favoriteAdapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        FavoriteViewModel favoriteViewModel =
//                new ViewModelProvider(this).get(FavoriteViewModel.class);

        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // Initialize presenter
        mealsPresenter = new MealsPresenterImp(this,
                MealRepositryImp.getInstance(
                        MealsRemoteDataSourceImp.getInstance(requireContext()),
                        MealsLocalDataSourceImp.getInstance(requireContext())
                )
        );


//        final TextView textView = binding.textFavorite;
//        favoriteViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // Access SharedPreferences using requireContext()
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefsFile", MODE_PRIVATE);
        String userEmail = sharedPreferences.getString("user_email", null);

        if (userEmail != null) {
            // Use the email as needed
            Toast.makeText(requireContext(), "Welcome back, " + userEmail, Toast.LENGTH_SHORT).show();
            linearLayoutManager = new LinearLayoutManager(requireContext());
            LiveData<List<Meal>> all_meals = mealsPresenter.getlocalmeas();
            all_meals.observe(getViewLifecycleOwner(), meals -> {
                if (meals != null && meals.size() > 0) {
                    Log.i("jsijjdmkssw---------------", meals.toString());
                    favoriteAdapter = new FavoriteAdapter( meals, this);
                    recyclerView.setAdapter(favoriteAdapter);
                    recyclerView.setLayoutManager(linearLayoutManager);
                } else {
                    Toast.makeText(requireContext(), "No favorite meals found", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(requireContext(), "Please log in to enable this feature", Toast.LENGTH_SHORT).show();
        }

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recycler_favorite);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void showData(List<Meal> meals) {

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
    public void OnClick_ShowMealDetails(String mealid) {
        MealDetailFragment detailFragment = new MealDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("meal_id", mealid);
        detailFragment.setArguments(bundle);
        NavController navController = Navigation.findNavController(getView());
        navController.navigate(R.id.navigation_meal_detail,bundle);
    }
}