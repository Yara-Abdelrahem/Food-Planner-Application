package com.example.yallameal.HomeYallaMeal.Meals.View;

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
import com.example.yallameal.FavoritMeals.View.IMealClickListener;
import com.example.yallameal.Model.Meal;
import com.example.yallameal.R;

import java.util.ArrayList;
import java.util.List;

public class RMealDetailsAdapter extends RecyclerView.Adapter<RMealDetailsAdapter.ViewHolder> {

    private final Context context;
    private List<Meal> values;
    private Meal favprod;
    IMealClickListener  iMealClickListener;
    private static final String TAG = "RecyclerView";

    public RMealDetailsAdapter(Context context, List<Meal>myDataset , IMealClickListener  iMealClickListener) {
        values=new ArrayList<Meal>();
        this.context = context;
        this.iMealClickListener = iMealClickListener;
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
        View view = inflator.inflate(R.layout.meal_small_view, parent, false);
        ViewHolder vh = new ViewHolder(view);
        Log.i(TAG , "ON CREATEVIEWHOLDER");

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txttitle.setText(values.get(position).getStrMeal());
        Glide.with(context).load(values.get(position).getStrMealThumb())
        .apply(new RequestOptions().override(200,200))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.imageview);
        holder.addmealfavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iMealClickListener.AddMealFav(values.get(position).getIdMeal());
            }
        });
    }
    public Meal getFavProd(){
        if (favprod != null){
            return favprod;
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return values.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txttitle ;
        public ImageView imageview , addmealfavorite;

        public ConstraintLayout constraintLayout;
        public View layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView;
            txttitle=itemView.findViewById(R.id.text_meal_name);
            imageview = itemView.findViewById(R.id.image_meal);
            addmealfavorite = itemView.findViewById(R.id.addmealfavorite);

            constraintLayout  =itemView.findViewById(R.id.home_layout);
        }
    }
}
