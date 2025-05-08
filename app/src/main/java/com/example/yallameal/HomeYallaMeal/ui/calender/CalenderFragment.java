package com.example.yallameal.HomeYallaMeal.ui.calender;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.yallameal.HomeYallaMeal.ui.calender.view.CalenderSchedAdapter;
import com.example.yallameal.HomeYallaMeal.ui.favorite.IallMealClickListener;
import com.example.yallameal.MealDetailFragment;
import com.example.yallameal.Model.Category;
import com.example.yallameal.Model.Country;
import com.example.yallameal.Model.Ingredient;
import com.example.yallameal.Model.Meal;
import com.example.yallameal.Model.MealRepositryImp;
import com.example.yallameal.Model.MealSchedule;
import com.example.yallameal.Network.MealsRemoteDataSourceImp;
import com.example.yallameal.R;
import com.example.yallameal.databinding.FragmentCalenderBinding;
import com.example.yallameal.db.MealsLocalDataSourceImp;

import java.util.List;

public class CalenderFragment extends Fragment implements AllMealsView, IallMealClickListener {

    private FragmentCalenderBinding binding;
    MealsPresenter mealsPresenter;
    RecyclerView recyclerView;

    LinearLayoutManager linearLayoutManager;
    CalenderSchedAdapter calenderSchedAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CalenderViewModel dashboardViewModel =
                new ViewModelProvider(this).get(CalenderViewModel.class);

        binding = FragmentCalenderBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mealsPresenter = new MealsPresenterImp(this,
                MealRepositryImp.getInstance(
                        MealsRemoteDataSourceImp.getInstance(requireContext()),
                        MealsLocalDataSourceImp.getInstance(requireContext())
                )
        );
        // Access SharedPreferences using requireContext()
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefsFile", MODE_PRIVATE);
        String userEmail = sharedPreferences.getString("user_email", null);
        linearLayoutManager = new LinearLayoutManager(requireContext());

        if (userEmail != null) {
            // Use the email as needed
            Toast.makeText(requireContext(), "Welcome back, " + userEmail, Toast.LENGTH_SHORT).show();
            binding.calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
                // Convert date to your required format
                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                Toast.makeText(requireContext(), "Selected date: " + selectedDate, Toast.LENGTH_SHORT).show();

                // Get meals for that date
                LiveData<List<MealSchedule>> listLiveData = mealsPresenter.getAllMealsSchedDate(selectedDate);
                listLiveData.observe(getViewLifecycleOwner(), mealSchedules -> {
                    if (mealSchedules != null && mealSchedules.size() > 0) {
                        calenderSchedAdapter = new CalenderSchedAdapter(mealSchedules, this);
                        recyclerView.setAdapter(calenderSchedAdapter);
                        recyclerView.setLayoutManager(linearLayoutManager);
                    } else {
                        mealSchedules.clear();
                        calenderSchedAdapter = new CalenderSchedAdapter(mealSchedules, this);
                        recyclerView.setAdapter(calenderSchedAdapter);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        Toast.makeText(requireContext(), "No favorite meals found", Toast.LENGTH_SHORT).show();
                    }
                });
            });

        } else {
            Toast.makeText(requireContext(), "Please log in to enable this feature", Toast.LENGTH_SHORT).show();
        }
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.calendarrecyclerview);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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
}