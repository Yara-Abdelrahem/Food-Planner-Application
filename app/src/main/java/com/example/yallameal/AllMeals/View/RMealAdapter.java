package com.example.yallameal.AllMeals.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.yallameal.Model.Meal;

import java.util.ArrayList;
import java.util.List;

/*
public class RMealAdapter extends RecyclerView.Adapter<RMealAdapter.ViewHolder> {

    private final Context context;
    private List<Meal> values;
    private Meal favprod;
    private IallMealClickListener listener;
    private static final String TAG = "RecyclerView";

    public RMealAdapter(Context context, List<Meal>myDataset, IallMealClickListener ondataupdate) {
        values=new ArrayList<Meal>();
        listener=ondataupdate;
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
        View view = inflator.inflate(R.layout.Meal_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        Log.i(TAG , "ON CREATEVIEWHOLDER");

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txttitle.setText(values.get(position).getTitle());
        holder.txtDescription.setText(values.get(position).getDescription());
        holder.Txtprice.setText(String.valueOf(values.get(position).getPrice()));
        holder.ratingBar.setRating(values.get(position).getRate());
        Glide.with(context).load(values.get(position).getImg())
        .apply(new RequestOptions().override(200,200))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.imageview);
        holder.add_fav_btn_adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG , "Value ; "+position+" "+values.size());
                listener.AddMealToFav(values.get(position));
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
        public TextView txttitle , txtDescription , Txtprice;
        public RatingBar ratingBar;
        public ImageView imageview;
        public Button add_fav_btn_adapter;
        public ConstraintLayout constraintLayout;
        public View layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView;
            txttitle=itemView.findViewById(R.id.titleTxt2);
            txtDescription =itemView.findViewById(R.id.descriptionTxt2);
            Txtprice = itemView.findViewById(R.id.priceTxt2);
            ratingBar = itemView.findViewById(R.id.ratingBar2);
            imageview = itemView.findViewById(R.id.imageView2);
            add_fav_btn_adapter = itemView.findViewById(R.id.add_fav_btn);
            constraintLayout  =itemView.findViewById(R.id.row_layout);
        }


    }
}
*/