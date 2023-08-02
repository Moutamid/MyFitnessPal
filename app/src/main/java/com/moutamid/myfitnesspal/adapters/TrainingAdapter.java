package com.moutamid.myfitnesspal.adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moutamid.myfitnesspal.R;
import com.moutamid.myfitnesspal.models.TrainingModel;

import java.util.ArrayList;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.TrainingVH> {
    Context context;
    ArrayList<TrainingModel> list;

    public TrainingAdapter(Context context, ArrayList<TrainingModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public TrainingVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TrainingVH(LayoutInflater.from(context).inflate(R.layout.training_item_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingVH holder, int position) {
        TrainingModel model = list.get(holder.getAdapterPosition());

        holder.gif.setImageResource(model.getGif());
        holder.name.setText(model.getName());

        if (model.isDuration()){
           if (model.getTime() >= 60){
               int s = model.getTime();
               int sec = s % 60;
               int min = (s / 60) % 60;
               holder.time.setText(min + " : " + sec);
           } else if (model.getTime() >= 10) {
               holder.time.setText("00 : " + model.getTime());
           } else {
               holder.time.setText("00 : 0" + model.getTime());
           }
        } else {
            holder.time.setText("x" + model.getTime());
        }

        holder.itemView.setOnClickListener(v -> {
            showDetail(model);
        });

    }

    private void showDetail(TrainingModel model) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.training_detail_dialog);
        dialog.setCancelable(false);

        Button close = dialog.findViewById(R.id.close);
        TextView desc = dialog.findViewById(R.id.desc);
        TextView heading = dialog.findViewById(R.id.heading);
        ImageView gif = dialog.findViewById(R.id.gif);

        RecyclerView focusAreaRC = dialog.findViewById(R.id.focusAreaRC);
        focusAreaRC.setHasFixedSize(false);

        FocusAreaAdapter adapter = new FocusAreaAdapter(context, model.getFocusAreas());
        focusAreaRC.setAdapter(adapter);
        heading.setText(model.getName());
        desc.setText(model.getDescription());
        gif.setImageResource(model.getGif());

        close.setOnClickListener(v -> dialog.dismiss());

        dialog.getWindow().clearFlags( WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TrainingVH extends RecyclerView.ViewHolder{
        TextView name, time;
        ImageView gif;
        public TrainingVH(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            time = itemView.findViewById(R.id.time);
            gif = itemView.findViewById(R.id.gif);

        }
    }

}

