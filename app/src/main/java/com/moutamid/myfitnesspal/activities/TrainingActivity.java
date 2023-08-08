package com.moutamid.myfitnesspal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.fxn.stash.Stash;
import com.moutamid.myfitnesspal.R;
import com.moutamid.myfitnesspal.databinding.ActivityTrainingBinding;
import com.moutamid.myfitnesspal.models.TrainingModel;
import com.moutamid.myfitnesspal.utili.Constants;

public class TrainingActivity extends AppCompatActivity {
    ActivityTrainingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTrainingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Constants.changeTheme(this);

        initClickListners();

        binding.back.setOnClickListener(v -> onBackPressed());

    }


    private void initClickListners() {

        binding.abs.setOnClickListener(v -> {
            Stash.put(Constants.TRAINING_TYPE , Constants.ABS);
            startActivity(new Intent(this, TrainingExerciseActivity.class));
        });
        binding.chest.setOnClickListener(v -> {
            Stash.put(Constants.TRAINING_TYPE , Constants.CHEST);
            startActivity(new Intent(this, TrainingExerciseActivity.class));
        });
        binding.forearm.setOnClickListener(v -> {
            Stash.put(Constants.TRAINING_TYPE , Constants.FOREARM);
            startActivity(new Intent(this, TrainingExerciseActivity.class));
        });
        binding.biceps.setOnClickListener(v -> {
            Stash.put(Constants.TRAINING_TYPE , Constants.BICEPS);
            startActivity(new Intent(this, TrainingExerciseActivity.class));
        });
        binding.triceps.setOnClickListener(v -> {
            Stash.put(Constants.TRAINING_TYPE , Constants.TRICEPS);
            startActivity(new Intent(this, TrainingExerciseActivity.class));
        });
        binding.shoulder.setOnClickListener(v -> {
            Stash.put(Constants.TRAINING_TYPE , Constants.SHOULDER);
            startActivity(new Intent(this, TrainingExerciseActivity.class));
        });
        binding.calves.setOnClickListener(v -> {
            Stash.put(Constants.TRAINING_TYPE , Constants.CALVES);
            startActivity(new Intent(this, TrainingExerciseActivity.class));
        });
        binding.hip.setOnClickListener(v -> {
            Stash.put(Constants.TRAINING_TYPE , Constants.HIP);
            startActivity(new Intent(this, TrainingExerciseActivity.class));
        });
        binding.backExercise.setOnClickListener(v -> {
            Stash.put(Constants.TRAINING_TYPE , Constants.BACK);
            startActivity(new Intent(this, TrainingExerciseActivity.class));
        });

    }
}