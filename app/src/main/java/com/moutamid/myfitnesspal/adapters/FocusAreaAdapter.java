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

public class FocusAreaAdapter extends RecyclerView.Adapter<FocusAreaAdapter.FoodVH> {

    Context context;
    ArrayList<String> list;

    public FocusAreaAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FoodVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FoodVH(LayoutInflater.from(context).inflate(R.layout.focus_area_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FoodVH holder, int position) {
        String model = list.get(holder.getAdapterPosition());
        holder.name.setText(model);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FoodVH extends RecyclerView.ViewHolder{
        TextView name;
        public FoodVH(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.focusArea);
        }
    }

}
