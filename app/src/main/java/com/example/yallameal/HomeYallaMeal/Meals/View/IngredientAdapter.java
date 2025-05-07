package com.example.yallameal.HomeYallaMeal.Meals.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yallameal.R;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {
    private List<Ingredient_Dtails> ingredientDtails;
    private Context context;

    public IngredientAdapter(Context context, List<Ingredient_Dtails> ingredientDtails) {
        this.context = context;
        this.ingredientDtails = ingredientDtails;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ingredient, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Ingredient_Dtails ingredientDtails = this.ingredientDtails.get(position);
        holder.textView.setText(ingredientDtails.getName());
        Glide.with(context).load(ingredientDtails.getImageUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return ingredientDtails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_ingredient);
            textView = itemView.findViewById(R.id.text_ingredient);
        }
    }
}

