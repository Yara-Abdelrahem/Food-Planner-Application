package com.example.yallameal.HomeYallaMeal.Countries.View;

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
import com.example.yallameal.Model.Country;
import com.example.yallameal.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RCountryAdapter extends RecyclerView.Adapter<RCountryAdapter.ViewHolder> {

    private final Context context;
    private List<Country> values;
    private static final String TAG = "RecyclerView";

    public RCountryAdapter(Context context, List<Country>myDataset) {
        values=new ArrayList<Country>();
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
        holder.txttitle.setText(values.get(position).getStrArea());
        String areaImageUrl = getAreaFlagUrl(values.get(position).getStrArea());
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

    private String getAreaFlagUrl(String areaName) {
        Map<String, String> areaCountryCodeMap = new HashMap<>();
        areaCountryCodeMap.put("American", "US");
        areaCountryCodeMap.put("British", "GB");
        areaCountryCodeMap.put("Canadian", "CA");
        areaCountryCodeMap.put("Chinese", "CN");
        areaCountryCodeMap.put("Croatian", "HR");
        areaCountryCodeMap.put("Dutch", "NL");
        areaCountryCodeMap.put("Egyptian", "EG");
        areaCountryCodeMap.put("Filipino", "PH");
        areaCountryCodeMap.put("French", "FR");
        areaCountryCodeMap.put("Greek", "GR");
        areaCountryCodeMap.put("Indian", "IN");
        areaCountryCodeMap.put("Irish", "IE");
        areaCountryCodeMap.put("Italian", "IT");
        areaCountryCodeMap.put("Jamaican", "JM");
        areaCountryCodeMap.put("Japanese", "JP");
        areaCountryCodeMap.put("Kenyan", "KE");
        areaCountryCodeMap.put("Malaysian", "MY");
        areaCountryCodeMap.put("Mexican", "MX");
        areaCountryCodeMap.put("Moroccan", "MA");
        areaCountryCodeMap.put("Polish", "PL");
        areaCountryCodeMap.put("Portuguese", "PT");
        areaCountryCodeMap.put("Russian", "RU");
        areaCountryCodeMap.put("Spanish", "ES");
        areaCountryCodeMap.put("Thai", "TH");
        areaCountryCodeMap.put("Tunisian", "TN");
        areaCountryCodeMap.put("Turkish", "TR");
        areaCountryCodeMap.put("Ukrainian", "UA");
        areaCountryCodeMap.put("Uruguayan", "UY");
        areaCountryCodeMap.put("Vietnamese", "VN");

        String countryCode = areaCountryCodeMap.get(areaName);
        if (countryCode != null)
        {
            return "https://flagcdn.com/256x192/" + countryCode.toLowerCase() + ".png";  // 80px width
        }
        else
        {
            return "https://via.placeholder.com/80x50.png?text=" + areaName; // fallback placeholder
        }
    }
}
