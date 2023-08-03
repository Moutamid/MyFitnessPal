package com.moutamid.myfitnesspal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moutamid.myfitnesspal.R;
import com.moutamid.myfitnesspal.models.CompModel;

import java.util.ArrayList;

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.RankVH> {

    Context context;
    ArrayList<CompModel> list;

    public RankAdapter(Context context, ArrayList<CompModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RankVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RankVH(LayoutInflater.from(context).inflate(R.layout.people_rank_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RankVH holder, int position) {
        CompModel model = list.get(holder.getAdapterPosition());
        holder.name.setText(model.getName());
        holder.rank.setText("Rank # " + model.getRank());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RankVH extends RecyclerView.ViewHolder{
        TextView name, rank;
        public RankVH(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            rank = itemView.findViewById(R.id.rankPosition);
        }
    }

}
