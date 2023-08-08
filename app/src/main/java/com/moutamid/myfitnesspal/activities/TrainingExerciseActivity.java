package com.moutamid.myfitnesspal.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.fxn.stash.Stash;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.moutamid.myfitnesspal.R;
import com.moutamid.myfitnesspal.adapters.TrainingAdapter;
import com.moutamid.myfitnesspal.databinding.ActivityTrainingExerciseBinding;
import com.moutamid.myfitnesspal.models.TrainingModel;
import com.moutamid.myfitnesspal.utili.Constants;

import java.util.ArrayList;
import java.util.Collections;

public class TrainingExerciseActivity extends AppCompatActivity {
    ActivityTrainingExerciseBinding binding;
    String level, type;
    TrainingAdapter adapter;
    ArrayList<TrainingModel> trainingList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTrainingExerciseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Constants.changeTheme(this);

         trainingList = new ArrayList<>();

         Constants.initDialog(this);
         Constants.showDialog();

        type = Stash.getString(Constants.TRAINING_TYPE);
        binding.heading.setText(type);

        binding.back.setOnClickListener(v -> onBackPressed());

        binding.workoutRC.setLayoutManager(new LinearLayoutManager(this));
        binding.workoutRC.setHasFixedSize(false);

        getData();

        ItemTouchHelper.Callback ithCallback = new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                return makeFlag(ItemTouchHelper.ACTION_STATE_DRAG,
                        ItemTouchHelper.DOWN | ItemTouchHelper.UP);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                Collections.swap(trainingList, viewHolder.getAdapterPosition(), target.getAdapterPosition());
                adapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                Log.d("PositionTabs", "holder : " + viewHolder.getAdapterPosition() + "   target : " + target.getAdapterPosition() );

                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            }
        };
        ItemTouchHelper ith = new ItemTouchHelper(ithCallback);
        ith.attachToRecyclerView(binding.workoutRC);


    }

    private void getData() {
        String type = Stash.getString(Constants.TRAINING_TYPE);
        Constants.databaseReference().child(Constants.TRAINING).child(type).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    trainingList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        TrainingModel model = dataSnapshot.getValue(TrainingModel.class);
                        trainingList.add(model);
                    }
                } else {
                    Toast.makeText(TrainingExerciseActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                }
                Constants.dismissDialog();
                adapter = new TrainingAdapter(TrainingExerciseActivity.this,  trainingList);
                binding.workoutRC.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Constants.dismissDialog();
                Toast.makeText(TrainingExerciseActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}