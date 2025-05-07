package com.example.yallameal.HomeYallaMeal.ui.favorite;

import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.yallameal.Model.Meal;
import com.example.yallameal.R;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder>  {

    private List<Meal> favmeals;
    private IallMealClickListener listener;
    private static final String TAG = "RecyclerView";

    public FavoriteAdapter(List<Meal> meals , IallMealClickListener listener) {
        this.listener = listener;
        this.favmeals = meals;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(parent.getContext());
        View view = inflator.inflate(R.layout.meal_small_view, parent, false);
        ViewHolder vh = new ViewHolder(view);
        Log.i("=================", "ON CREATEVIEWHOLDER");
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meal meal = favmeals.get(position);
        Log.i("---------------------",meal.toString());
        holder.txttitle.setText(meal.getStrMeal());
        Glide.with(holder.itemView.getContext()).load(meal.getStrMealThumb())
                .apply(new RequestOptions().override(200,200))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.imageview);

        holder.itemView.setOnClickListener(v -> {
//            Log.d("RMealAdapter", "Clicked meal: " + meal.getStrMeal());
            listener.OnClick_ShowMealDetails(meal.getIdMeal()); // Use ID instead of name if needed
        });
    }

    @Override
    public int getItemCount() {
        return favmeals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txttitle ;
        public ImageView imageview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttitle=itemView.findViewById(R.id.text_meal_name);
            imageview = itemView.findViewById(R.id.image_meal);
        }
    }
}
