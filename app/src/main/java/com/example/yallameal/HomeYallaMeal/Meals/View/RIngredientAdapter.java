package com.example.yallameal.HomeYallaMeal.Meals.View;

import static android.content.ContentValues.TAG;

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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yallameal.Model.Country;
import com.example.yallameal.Model.Ingredient;
import com.example.yallameal.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RIngredientAdapter extends RecyclerView.Adapter<RIngredientAdapter.ViewHolder> {

    private final Context context;
    private List<Ingredient> values;
    private static final String TAG = "RecyclerView";

    public RIngredientAdapter(Context context, List<Ingredient>myDataset) {
        values=new ArrayList<Ingredient>();
        this.context = context;
        if (myDataset.size()>0){
            values = myDataset;
        }else {
            Toast.makeText(context, "The array is not fill ", Toast.LENGTH_SHORT).show();
        }

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(parent.getContext());
        View view = inflator.inflate(R.layout.item_category, parent, false);
        ViewHolder vh = new ViewHolder(view);
        Log.i(TAG , "ON CREATEVIEWHOLDER");

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txttitle.setText(values.get(position).getStrIngredient());
        String areaImageUrl = getAreaFlagUrl(values.get(position).getStrIngredient());
        Glide.with(context)
                .load(areaImageUrl)
                .placeholder(R.drawable.ic_launcher_background) // Optional: add a placeholder drawable
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.imageview);

    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txttitle;
        public ImageView imageview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttitle = itemView.findViewById(R.id.textViewCategoryName);
            imageview = itemView.findViewById(R.id.imageView);
        }
    }

    private String getAreaFlagUrl(String ingredientName) {
//        Log.i(TAG, ingredientName);
        if (ingredientName != null) {
            return "https://www.themealdb.com/images/ingredients/" + ingredientName + ".png";
        } else {
            return "https://www.themealdb.com/images/ingredients/unknown.png"; // optional: fallback
        }
    }
}
