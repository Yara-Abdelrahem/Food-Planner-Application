package com.example.yallameal.HomeYallaMeal.Categories.View;

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
import com.example.yallameal.Model.Category;
import com.example.yallameal.R;

import java.util.ArrayList;
import java.util.List;

public class RCategoryAdapter extends RecyclerView.Adapter<RCategoryAdapter.ViewHolder> {

    private final Context context;
    private List<Category> values;
    private static final String TAG = "RecyclerView";

    public RCategoryAdapter(Context context, List<Category>myDataset) {
        values=new ArrayList<Category>();
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
        holder.txttitle.setText(values.get(position).getStrCategory());
        Glide.with(context).load(values.get(position).getStrCategoryThumb())
        .apply(new RequestOptions().override(200,200))
                .placeholder(R.drawable.ic_launcher_background)
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

}
