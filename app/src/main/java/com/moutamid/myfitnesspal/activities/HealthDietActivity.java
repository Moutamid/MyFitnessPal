package com.moutamid.myfitnesspal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.fxn.stash.Stash;
import com.moutamid.myfitnesspal.R;
import com.moutamid.myfitnesspal.databinding.ActivityHealthDietBinding;
import com.moutamid.myfitnesspal.utili.Constants;

public class HealthDietActivity extends AppCompatActivity {
    ActivityHealthDietBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHealthDietBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.breakfast.setOnClickListener(v -> {
            Stash.put(Constants.FOOD, Constants.BREAKFAST);
            startActivity(new Intent(this, SubCatActivity.class));
        });
        binding.entree.setOnClickListener(v -> {
            Stash.put(Constants.FOOD, Constants.ENTREES);
            startActivity(new Intent(this, SubCatActivity.class));
        });
        binding.dessert.setOnClickListener(v -> {
            Stash.put(Constants.FOOD, Constants.DESSERT);
            startActivity(new Intent(this, SubCatActivity.class));
        });

        binding.back.setOnClickListener(v -> onBackPressed());

    }
}