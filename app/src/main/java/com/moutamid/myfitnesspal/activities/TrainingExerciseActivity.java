package com.moutamid.myfitnesspal.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.fxn.stash.Stash;
import com.moutamid.myfitnesspal.R;
import com.moutamid.myfitnesspal.adapters.TrainingAdapter;
import com.moutamid.myfitnesspal.databinding.ActivityTrainingExerciseBinding;
import com.moutamid.myfitnesspal.models.TrainingModel;
import com.moutamid.myfitnesspal.utili.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

        type = Stash.getString(Constants.TRAINING_TYPE);
        level = Stash.getString(Constants.Level);
        String heading = type + " " + level;
        binding.heading.setText(heading);

        binding.back.setOnClickListener(v -> onBackPressed());

        binding.workoutRC.setLayoutManager(new LinearLayoutManager(this));
        binding.workoutRC.setHasFixedSize(false);

        if (level.equals(Constants.Beginner)){
            initBeginnersViews();
        } else if (level.equals(Constants.Intermediate)) {
            initInternViews();
        } else if (level.equals(Constants.Advance)) {
            initAdvanceViews();
        }

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

    private void setBeginnersDataAbs() {
        ArrayList<String> focusArea1 = new ArrayList<>();
        focusArea1.add("Shoulders");
        focusArea1.add("Quadriceps");
        focusArea1.add("Adductors");
        focusArea1.add("Chest");
        focusArea1.add("Glutes");
        focusArea1.add("Calves");
        String desc = "Start with your feet togather and your arms by your sides then jump up with your feet apart and your hands overhead. \n\nReturn to the start position then do the next rep. This exercise provides a full-body workout and works all your large muscle groups";
        trainingList.add(new TrainingModel("Jumping Jacks", desc, R.drawable.house, 20, true, focusArea1));

        ArrayList<String> focusArea2 = new ArrayList<>();
        focusArea2.add("Abs");
        String desc2 = "Lie on your back with knees bent and your arms stretched forward. \n\nThen lift your upper off the floor. Hold for a few seconds and slowly return. \n\nIt primarlily works the rectus abdominis muscle and the obliques.";
        trainingList.add(new TrainingModel("Abdominal Crunches", desc2, R.drawable.house, 16, false, focusArea2));

        ArrayList<String> focusArea3 = new ArrayList<>();
        focusArea3.add("Abs");
        String desc3 = "Sit on the floor with your knees bent, feet lifted a little bit back tilted backwards. \n\nThen hold your hands together and twist from side to side. \n\nEach Side x 10";
        trainingList.add(new TrainingModel("Russian Twist", desc3, R.drawable.house, 20, false, focusArea3));

        adapter = new TrainingAdapter(this, trainingList);
        binding.workoutRC.setAdapter(adapter);

    }

    private void initAdvanceViews() {
        if (type.equals(Constants.ABS)){
            binding.totalTime.setText("36 mins");
            binding.totalWorkouts.setText("21 workouts");
        } else if (type.equals(Constants.CHEST)) {
            binding.totalTime.setText("19 mins");
            binding.totalWorkouts.setText("16 workouts");
        } else if (type.equals(Constants.ARM)) {
            binding.totalTime.setText("32 mins");
            binding.totalWorkouts.setText("28 workouts");
        } else if (type.equals(Constants.LEG)) {
            binding.totalTime.setText("53 mins");
            binding.totalWorkouts.setText("43 workouts");
        } else if (type.equals(Constants.SHOULDER)) {
            binding.totalTime.setText("19 mins");
            binding.totalWorkouts.setText("17 workouts");
        }
    }

    private void initInternViews() {
        if (type.equals(Constants.ABS)){
            binding.totalTime.setText("29 mins");
            binding.totalWorkouts.setText("21 workouts");
        } else if (type.equals(Constants.CHEST)) {
            binding.totalTime.setText("15 mins");
            binding.totalWorkouts.setText("14 workouts");
        } else if (type.equals(Constants.ARM)) {
            binding.totalTime.setText("26 mins");
            binding.totalWorkouts.setText("25 workouts");
        } else if (type.equals(Constants.LEG)) {
            binding.totalTime.setText("41 mins");
            binding.totalWorkouts.setText("36 workouts");
        } else if (type.equals(Constants.SHOULDER)) {
            binding.totalTime.setText("19 mins");
            binding.totalWorkouts.setText("17 workouts");
        }
    }

    private void initBeginnersViews() {
        if (type.equals(Constants.ABS)){
            binding.totalTime.setText("20 mins");
            binding.totalWorkouts.setText("16 workouts");
            setBeginnersDataAbs();
        } else if (type.equals(Constants.CHEST)) {
            binding.totalTime.setText("11 mins");
            binding.totalWorkouts.setText("11 workouts");
        } else if (type.equals(Constants.ARM)) {
            binding.totalTime.setText("17 mins");
            binding.totalWorkouts.setText("19 workouts");
        } else if (type.equals(Constants.LEG)) {
            binding.totalTime.setText("26 mins");
            binding.totalWorkouts.setText("23 workouts");
        } else if (type.equals(Constants.SHOULDER)) {
            binding.totalTime.setText("17 mins");
            binding.totalWorkouts.setText("17 workouts");
        }
    }
}