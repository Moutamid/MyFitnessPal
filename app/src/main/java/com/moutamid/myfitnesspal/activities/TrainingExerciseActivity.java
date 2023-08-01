package com.moutamid.myfitnesspal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.fxn.stash.Stash;
import com.moutamid.myfitnesspal.R;
import com.moutamid.myfitnesspal.databinding.ActivityTrainingExerciseBinding;
import com.moutamid.myfitnesspal.utili.Constants;

public class TrainingExerciseActivity extends AppCompatActivity {
    ActivityTrainingExerciseBinding binding;
    String level, type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTrainingExerciseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        type = Stash.getString(Constants.TRAINING_TYPE);
        level = Stash.getString(Constants.Level);
        String heading = type + " " + level;
        binding.heading.setText(heading);

        binding.back.setOnClickListener(v -> onBackPressed());

        if (level.equals(Constants.Beginner)){
            initBeginnersViews();
        } else if (level.equals(Constants.Intermediate)) {
            initInternViews();
        } else if (level.equals(Constants.Advance)) {
            initAdvanceViews();
        }

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