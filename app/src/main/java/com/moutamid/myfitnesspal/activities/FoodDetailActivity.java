package com.moutamid.myfitnesspal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.fxn.stash.Stash;
import com.moutamid.myfitnesspal.R;
import com.moutamid.myfitnesspal.databinding.ActivityFoodDetailBinding;
import com.moutamid.myfitnesspal.models.FoodItemModel;
import com.moutamid.myfitnesspal.utili.Constants;

public class FoodDetailActivity extends AppCompatActivity {
    ActivityFoodDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFoodDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Constants.changeTheme(this);

        binding.back.setOnClickListener(v -> onBackPressed());

        FoodItemModel model = (FoodItemModel) Stash.getObject(Constants.FOOD_DETAIL, FoodItemModel.class);

        binding.heading.setText(model.getCat());
        binding.name.setText(model.getName());
        binding.totalCalories.setText(model.getCal());
        binding.totalFat.setText(model.getFat());
        binding.totalProtein.setText(model.getProtein());
        binding.totalCarbohydrates.setText(model.getCarbon());
        binding.totalServings.setText(model.getTotalServings());
        binding.ingridents.setText(model.getIngredients());
        binding.directions.setText(model.getDirections());

    }
}