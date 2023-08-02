package com.moutamid.myfitnesspal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.res.Configuration;
import android.os.Bundle;

import com.fxn.stash.Stash;
import com.moutamid.myfitnesspal.R;
import com.moutamid.myfitnesspal.adapters.FoodItemAdapter;
import com.moutamid.myfitnesspal.databinding.ActivitySubCatBinding;
import com.moutamid.myfitnesspal.models.FoodItemModel;
import com.moutamid.myfitnesspal.utili.Constants;

import java.util.ArrayList;

public class SubCatActivity extends AppCompatActivity {
    ActivitySubCatBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubCatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Constants.changeTheme(this);

        binding.back.setOnClickListener(v -> onBackPressed());

        binding.mainCat.setLayoutManager(new LinearLayoutManager(this));
        binding.mainCat.setHasFixedSize(false);

        String food = Stash.getString(Constants.FOOD);
        binding.heading.setText(food);

        if (food.equals(Constants.BREAKFAST)){
            getBreakfastList();
        } else if (food.equals(Constants.ENTREES)){
            getEntreesList();
        } else if (food.equals(Constants.DESSERT)){
            getDessertList();
        }

    }

    private void getDessertList() {
        ArrayList<FoodItemModel> list = new ArrayList<>();
        list.add(new FoodItemModel("Cookie Butter & Frosting", "Classic Chocolate ChipProtein Cookie Butter"));
        list.add(new FoodItemModel("Cookie Butter & Frosting", "Sugar Cookie Protein Butter"));
        list.add(new FoodItemModel("Cookie Dough Bites", "Protein Cookie Butter Dough in Bulk"));
        list.add(new FoodItemModel("Cookie Dough Bites", "Protein Funfetti Cake Bites"));
        list.add(new FoodItemModel("Crunch Wraps", "Protein Cookie Dough Stuffed Crepe Crunch Wrap"));
        list.add(new FoodItemModel("Crunch Wraps", "Red Velvet Oreo Cookie Butter Cheesecake Dessert Crunch Wrap"));
        list.add(new FoodItemModel("Pies", "Protein Cookie Dough Personal Pan Pie"));
        list.add(new FoodItemModel("Pies", "Low Cal Baked Cherry Pie"));
        FoodItemAdapter adapter = new FoodItemAdapter(this, list);
        binding.mainCat.setAdapter(adapter);
    }

    private void getEntreesList() {
        ArrayList<FoodItemModel> list = new ArrayList<>();
        list.add(new FoodItemModel("Pizza", "Pepperoni Pizza Frittata"));
        list.add(new FoodItemModel("Pizza", "Low Carb Taco Pizza"));
        list.add(new FoodItemModel("Burgers", "Krispy Kreme Donut Burger"));
        list.add(new FoodItemModel("Burgers", "Bacon Wrapped Egg & Cheddar Crunch Wrap Burger"));
        list.add(new FoodItemModel("Hot Pockets & Crunch Wraps", "Low Carb Canadian Bacon Cheeseburger Hot Pocket"));
        list.add(new FoodItemModel("Hot Pockets & Crunch Wraps", "Low Carb Cheesy Broccoli Hot Pocket"));
        list.add(new FoodItemModel("Misc. Entrées", "Salad Gainzzz Bowl"));
        list.add(new FoodItemModel("Misc. Entrées", "The Cheddar Cheese Dog Bread Bowl "));
        list.add(new FoodItemModel("Misc. Entrées", "Homemade Low Carb Oreo Lovers Big Bagel"));
        list.add(new FoodItemModel("Misc. Entrées", "Broccoli & Cheddar Low Carb Chalupa"));
        FoodItemAdapter adapter = new FoodItemAdapter(this, list);
        binding.mainCat.setAdapter(adapter);
    }

    private void getBreakfastList() {
        ArrayList<FoodItemModel> list = new ArrayList<>();
        list.add(new FoodItemModel("Sandwiches & Crunch Wraps", "Breakfast Bacon, Egg & Cheddar Crunch Wrap"));
        list.add(new FoodItemModel("Sandwiches & Crunch Wraps", "Egg White Delight Crunch Wrap"));
        list.add(new FoodItemModel("Donuts", "Oreo Cheescake Protein Donuts"));
        list.add(new FoodItemModel("Donuts", "Giant Homer Simson Donut"));
        list.add(new FoodItemModel("Waffles, Pancakes & French Toast", "Marshmallow Fruity Pebbles Protein Waffle Tower"));
        list.add(new FoodItemModel("Waffles, Pancakes & French Toast", "Reese’s Oreo Protein Waffle Tower"));
        list.add(new FoodItemModel("Bagels", "Low Carb Everything Bagel"));
        list.add(new FoodItemModel("Bagels", "Homemade Low Carb Oreo Lovers Big Bagel"));
        FoodItemAdapter adapter = new FoodItemAdapter(this, list);
        binding.mainCat.setAdapter(adapter);
    }
}