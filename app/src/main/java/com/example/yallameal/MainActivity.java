//package com.example.yallameal;
//
//import android.os.Bundle;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.yallameal.HomeYallaMeal.Meals.Presenter.MealsPresenter;
//import com.example.yallameal.HomeYallaMeal.Meals.View.AllMealsView;
//import com.example.yallameal.Model.Country;
//import com.example.yallameal.Model.Ingredient;
//import com.example.yallameal.Model.Meal;
//import com.example.yallameal.Model.Category;
//import com.example.yallameal.Network.MealsRemoteDataSourceImp;
//
//import java.util.List;
//
//public class MainActivity extends AppCompatActivity  implements AllMealsView {
//
//
//    RecyclerView recyclerview;
//    List<Meal> meals;
//    MealsRemoteDataSourceImp apiClientRetrofit;
//
//    MealsPresenter allMealsPresenter;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
//
////        allMealsPresenter= new AllMealsPresenterImp(this,
////                MealRepositryImp.getInstance(MealsRemoteDataSourceImp.getInstance(this),
////                        MealsLocalDataSourceImp.getInstance(this))
////        );
////        allMealsPresenter.getAllMeals();
//
//    }
//
//
//    @Override
//    public void showData(List<Meal> meals) {
//
//    }
//
//    @Override
//    public void showCatregoriesData(List<Category> categories) {
//
//    }
//
//    @Override
//    public void ShowCountriesData(List<Country> countries) {
//
//    }
//
//    @Override
//    public void ShowIngredientData(List<Ingredient> ingredients) {
//
//    }
//}