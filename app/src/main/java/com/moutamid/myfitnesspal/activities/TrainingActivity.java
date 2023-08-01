package com.moutamid.myfitnesspal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.fxn.stash.Stash;
import com.moutamid.myfitnesspal.R;
import com.moutamid.myfitnesspal.databinding.ActivityTrainingBinding;
import com.moutamid.myfitnesspal.utili.Constants;

public class TrainingActivity extends AppCompatActivity {
    ActivityTrainingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTrainingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initClickListners();

        binding.back.setOnClickListener(v -> onBackPressed());


    }

    private void initClickListners() {

        binding.absBeginner.setOnClickListener(v -> {
            Stash.put(Constants.Level , Constants.Beginner);
            Stash.put(Constants.TRAINING_TYPE , Constants.ABS);
            startActivity(new Intent(this, TrainingExerciseActivity.class));
        });
        binding.chestBeginner.setOnClickListener(v -> {
            Stash.put(Constants.Level , Constants.Beginner);
            Stash.put(Constants.TRAINING_TYPE , Constants.CHEST);
            startActivity(new Intent(this, TrainingExerciseActivity.class));
        });
        binding.armBeginner.setOnClickListener(v -> {
            Stash.put(Constants.Level , Constants.Beginner);
            Stash.put(Constants.TRAINING_TYPE , Constants.ARM);
            startActivity(new Intent(this, TrainingExerciseActivity.class));
        });
        binding.legBeginner.setOnClickListener(v -> {
            Stash.put(Constants.Level , Constants.Beginner);
            Stash.put(Constants.TRAINING_TYPE , Constants.LEG);
            startActivity(new Intent(this, TrainingExerciseActivity.class));
        });
        binding.shoulderBeginner.setOnClickListener(v -> {
            Stash.put(Constants.Level , Constants.Beginner);
            Stash.put(Constants.TRAINING_TYPE , Constants.SHOULDER);
            startActivity(new Intent(this, TrainingExerciseActivity.class));
        });

        binding.absIntermediate.setOnClickListener(v -> {
            Stash.put(Constants.Level , Constants.Intermediate);
            Stash.put(Constants.TRAINING_TYPE , Constants.ABS);
            startActivity(new Intent(this, TrainingExerciseActivity.class));
        });
        binding.chestIntermediate.setOnClickListener(v -> {
            Stash.put(Constants.Level , Constants.Intermediate);
            Stash.put(Constants.TRAINING_TYPE , Constants.CHEST);
            startActivity(new Intent(this, TrainingExerciseActivity.class));
        });
        binding.armIntermediate.setOnClickListener(v -> {
            Stash.put(Constants.Level , Constants.Intermediate);
            Stash.put(Constants.TRAINING_TYPE , Constants.ARM);
            startActivity(new Intent(this, TrainingExerciseActivity.class));
        });
        binding.legIntermediate.setOnClickListener(v -> {
            Stash.put(Constants.Level , Constants.Intermediate);
            Stash.put(Constants.TRAINING_TYPE , Constants.LEG);
            startActivity(new Intent(this, TrainingExerciseActivity.class));
        });
        binding.shoulderIntermediate.setOnClickListener(v -> {
            Stash.put(Constants.Level , Constants.Intermediate);
            Stash.put(Constants.TRAINING_TYPE , Constants.SHOULDER);
            startActivity(new Intent(this, TrainingExerciseActivity.class));
        });

        binding.absAdvance.setOnClickListener(v -> {
            Stash.put(Constants.Level , Constants.Advance);
            Stash.put(Constants.TRAINING_TYPE , Constants.ABS);
            startActivity(new Intent(this, TrainingExerciseActivity.class));
        });
        binding.chestAdvance.setOnClickListener(v -> {
            Stash.put(Constants.Level , Constants.Advance);
            Stash.put(Constants.TRAINING_TYPE , Constants.CHEST);
            startActivity(new Intent(this, TrainingExerciseActivity.class));
        });
        binding.armAdvance.setOnClickListener(v -> {
            Stash.put(Constants.Level , Constants.Advance);
            Stash.put(Constants.TRAINING_TYPE , Constants.ARM);
            startActivity(new Intent(this, TrainingExerciseActivity.class));
        });
        binding.legAdvance.setOnClickListener(v -> {
            Stash.put(Constants.Level , Constants.Advance);
            Stash.put(Constants.TRAINING_TYPE , Constants.LEG);
            startActivity(new Intent(this, TrainingExerciseActivity.class));
        });
        binding.shoulderAdvance.setOnClickListener(v -> {
            Stash.put(Constants.Level , Constants.Advance);
            Stash.put(Constants.TRAINING_TYPE , Constants.SHOULDER);
            startActivity(new Intent(this, TrainingExerciseActivity.class));
        });

    }
}