package com.moutamid.myfitnesspal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moutamid.myfitnesspal.R;
import com.moutamid.myfitnesspal.models.FoodItemModel;

import java.util.ArrayList;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.FoodVH> {

    Context context;
    ArrayList<FoodItemModel> list;

    public FoodItemAdapter(Context context, ArrayList<FoodItemModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FoodVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FoodVH(LayoutInflater.from(context).inflate(R.layout.health_diet_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FoodVH holder, int position) {
        FoodItemModel model = list.get(holder.getAdapterPosition());

        holder.cat.setText(model.getCat());
        holder.name.setText(model.getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FoodVH extends RecyclerView.ViewHolder{
        TextView cat, name;
        public FoodVH(@NonNull View itemView) {
            super(itemView);
            cat = itemView.findViewById(R.id.catName);
            name = itemView.findViewById(R.id.name);
        }
    }

}
