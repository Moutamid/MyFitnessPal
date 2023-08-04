package com.moutamid.myfitnesspal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.fxn.stash.Stash;
import com.moutamid.myfitnesspal.R;
import com.moutamid.myfitnesspal.adapters.FoodItemAdapter;
import com.moutamid.myfitnesspal.databinding.ActivitySubCatBinding;
import com.moutamid.myfitnesspal.listners.FoodItemClickListener;
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

        if (food.equals(Constants.BREAKFAST)) {
            getBreakfastList();
        } else if (food.equals(Constants.ENTREES)) {
            getEntreesList();
        } else if (food.equals(Constants.DESSERT)) {
            getDessertList();
        }

    }

    private void getDessertList() {
        ArrayList<FoodItemModel> list = new ArrayList<>();

        String directions = "1. Mix your dry ingredients (except your mini chocolate chips) in a bowl to avoid clumping. \n" +
                "2. Add 75ml of your cold water and mix, then mix in the other 75ml. \n" +
                "You want to get a thick frosting/icing like consistency. Now you \n" +
                "can add in your mini chocolate chips and mix those in. Add all \n" +
                "of your cookie butter to an 8oz mason jar and place a cover \n" +
                "on top. It’s best to let this sit in the ridge for at least 1-2 hours to \n" +
                "thicken up some more. Keeping it in the fridge overnight is best! \n" +
                "Best to let sit in fridge for at least 1-2 hours to thicken up some \n" +
                "more. Overnight is the best. And that’s it!";

        String ingredients = "40g PEScience PB Cookie Select Protein \n" +
                "20g PEScience Gourmet Vanilla Select \n" +
                "18g Powdered PB\n" +
                "18g Coconut Flour\n" +
                "6g Stevia\n" +
                "150ml Cold Water\n" +
                "30g Mini Chocolate Chips";

        list.add(new FoodItemModel("Cookie Butter & Frosting", "Classic Chocolate ChipProtein Cookie Butter", "60", "1.6g", "6.5g", "4.9g", "8.5 Serving Per Recipe", directions, ingredients));

        String directions1 = "1. Mix your dry ingredients (except your sprinkles) in a bowl to avoid clumping. \n" +
                "2. Add 75ml of your cold water and mix, then mix in the other 75ml. \n" +
                "You want to get a thick frosting/icing like consistency. Now \n" +
                "you can add in your sprinkles and mix those in. Add all of your \n" +
                "cookie butter to an 8oz mason jar and place a cover on top. \n" +
                "It’s best to let this sit in the ridge for at least 1-2 hours to thicken \n" +
                "up some more. Keeping it in the fridge overnight is best! Best to \n" +
                "let sit in fridge for at least 1-2 hours to thicken up some more. \n" +
                "Overnight is the best. And that’s it!";

        String ingredients1 = "60g PEScience Gourmet Vanilla Select \n" +
                "18g Powdered PB\n" +
                "18g Coconut Flour\n" +
                "6g Stevia\n" +
                "150ml Cold Water\n" +
                "24g Sprinkles";

        list.add(new FoodItemModel("Cookie Butter & Frosting", "Sugar Cookie Protein Butter", "57.4", "1.4g", "6.5g", "4.7g", "8.5 Serving Per Recipe", directions1, ingredients1));

        String directions2 = "1. Add all dry ingredients to your food processor and pulse to \n" +
                "combine. Add your greek yogurt in evenly (over all of the dry \n" +
                "ingredients) to avoid clumping and pulse 20-30 times. It should \n" +
                "be a little crumbly. Press and form your dough into a ball. Repeat \n" +
                "this process to create 10 dough balls, weighing 24g each.";

        String ingredients2 = "75g PEScience PB Cookie Select Protein \n" +
                "30g Powdered PB\n" +
                "15g Coconut Flour\n" +
                "5g Stevia\n" +
                "115g Plain Nonfat Greek Yogurt (optional/\n" +
                "can sub water)";

        list.add(new FoodItemModel("Cookie Dough Bites", "Protein Cookie Butter Dough in Bulk", "53.6", "0.8g", "8.3g", "3.3g", "10 Serving Per Recipe", directions2, ingredients2));

        String directions3 = "1. Preheat your oven to 350 degrees F. Add all dry ingredients to \n" +
                "one bowl and all wet ingredients to another. Mix the ingredients in \n" +
                "each bowl to prevent clumping. Add the dry ingredients to the wet \n" +
                "ingredients and mix some more.\n" +
                "2. Add the batter to your cupcake holder and let cook in the oven for \n" +
                "about 10-12 minutes. You’ll see the top start to golden.\n" +
                "3. Once it’s done, let that cool for about 20 minutes.\n" +
                "4. Break the cupcake into pieces, then form into a ball shape. Take your \n" +
                "white chocolate, add it to a microwave safe bowl, then microwave that \n" +
                "for 20 seconds. Add the drizzle on top of your cake bites and spread \n" +
                "the white chocolate, with a spoon, all around the bite until it’s covered. \n" +
                "Add some sprinkles on top and place it in the freezer to set. After 15-\n" +
                "20 minutes, take it out and enjoy!";

        String ingredients3 = "8g Pescience Gourmet Vanilla Select Protein \n" +
                "3g Coconut Flour\n" +
                "5g Pure Pumpkin\n" +
                "15g Unsweetened Apple Sauce\n" +
                "20g Egg Whites\n" +
                "1g Stevia\n" +
                "1g Baking Powder\n" +
                "3g Sprinkles\n" +
                "10g White Chocolate for Outside Coating";

        list.add(new FoodItemModel("Cookie Dough Bites", "Protein Funfetti Cake Bites", "360", "4g", "51g", "30g", "1 Serving Per Recipe", directions3, ingredients3));

        String directions4 = "1. First you’ll want to make your protein cookie dough filling. Mix all of \n" +
                "your dry ingredients in a bowl to avoid clumping. Add 20ml of cold \n" +
                "water to your dry ingredients and mix. Repeat this process by adding \n" +
                "10ml of cold water until you get a thick, cookie dough-like consistency. \n" +
                "Add that to your freezer to cool while you cook your crepe. If you \n" +
                "added a little too much water, that’s okay. Just let it sit in freezer a little \n" +
                "longer to thicken up.\n" +
                "2. Preheat your medium size stovetop pan (8in) on medium heat. Whisk \n" +
                "your egg whites, light butter, applesauce and vanilla extract in a bowl. \n" +
                "Add in your protein powder and stevia. Whisk those in. Make sure you \n" +
                "don’t have any chunks.\n" +
                "3. Add the batter to the middle of your stove top pan. In a circular motion \n" +
                "spread your batter all across the pan. You want your crepe to be thin. \n" +
                "4. Cook until you see bubbles on top, then flip it over, and cook \n" +
                "for another 30 seconds. Once it’s done, take it off the pan, and \n" +
                "immediately use it to make your burrito. Take your cookie dough out \n" +
                "of the freezer, mix in your mini chocolate chips, and add that to the \n" +
                "middle of your crepe. Roll your crepe up like a burrito, then add it back \n" +
                "to the pan to seal the bottom edge. If you want it cold, add it back to \n" +
                "the freezer, then enjoy!";

        String ingredients4 = "45g Egg Whites\n" +
                "5g Light Butter\n" +
                "5g Unsweetened Apple Sauce\n" +
                "10g PEScience PB Cookie Select Protein \n" +
                "2g Stevia\n" +
                "1g Vanilla Extract (optional)";

        list.add(new FoodItemModel("Crunch Wraps", "Protein Cookie Dough Stuffed Crepe Crunch Wrap", "242", "6g", "32g", "15g", "1 Serving Per Recipe", directions4, ingredients4));

        String directions5 = "1. Mix your dry cookie butter ingredients in a bowl to avoid clumping. \n" +
                "Slowly add in 15ml of cold water, mix, and see how thick the \n" +
                "consistency is. If you need to, add in another 15ml. Add in your red \n" +
                "food coloring. If you aren’t getting that red velvet color, add a few \n" +
                "more drops, then put that in the freezer. \n" +
                "2. Now you’ll make your cheesecake frosting. Add your greek yogurt \n" +
                "to one bowl, then mix your dry ingredients in another bowl to avoid \n" +
                "clumping. Mix your dry ingredients with your greek yogurt until you get \n" +
                "a thick frosting-like consistency. Put that in the freezer as well. \n" +
                "3. Preheat your stove top pan on medium heat. Lay your tortilla flat. \n" +
                "Add half of your protein frosting to the middle of the tortilla, half of \n" +
                "your cookie butter, your Oreo, then the rest of your cookie butter and \n" +
                "protein frosting. Fold your tortilla up like a crunch wrap.\n" +
                "4. Place your crunch wrap, bottom down, on your pan to seal it. While \n" +
                "that’s cooking, turn your oven on broil. Once the bottom is sealed, take \n" +
                "it off the pan, spray the top with butter spray, and add to your oven on \n" +
                "broil. When golden, take it out, and add to the freezer to cool. \n" +
                "5. When cool, slice your crunch wrap open, and reveal Red Velvet \n" +
                "Heaven!";

        String ingredients5 = "10g PEScience Gourmet Vanilla Select Protein \n" +
                "5g Unsweetened Baking Cocoa\n" +
                "2g Coconut Flour\n" +
                "2g Zero Cal Sweetener of your choice\n" +
                "30ml Cold Water\n" +
                "Natural Red Food Coloring";

        list.add(new FoodItemModel("Crunch Wraps", "Red Velvet Oreo Cookie Butter Cheesecake Dessert Crunch Wrap", "255", "7g", "20g", "28g", "1 Serving Per Recipe", directions5, ingredients5));

        String directions6 = "1. Preheat your oven to 400 degrees F. Add all of your dry pie ingredients to your small \n" +
                "food processor and pulse to combine to avoid clumping. Add in your wet ingredient \n" +
                "and pulse 20-30 times until you get a doughy crumble.\n" +
                "2. Combine and form your dough into a ball. I recommend letting your dough sit in the \n" +
                "fridge, covered in plastic wrap, for 10-15 minutes to cool. \n" +
                "3. Roll out your dough in between 2 pieces of parchment paper. The key is to make sure \n" +
                "that your pieces of parchment paper aren’t too big, so it doesn’t slide around a lot. Roll \n" +
                "your dough out as far tall and wide as you can! Take your mini pie pans and gauge the \n" +
                "size of the circles you should cut out. Once you’ve done that, cut out 4 circles to add to \n" +
                "your pie pans. Gently add your dough pieces to each pie crust mold pressing the sides \n" +
                "and bottom in. If you get a rip, use the extra dough to cover those areas. Spray the tops \n" +
                "with non stick butter spray and let those bake in the oven for 5-7 minutes.\n" +
                "4. While those are baking, make your protein cookie dough. Mix all of your dry \n" +
                "ingredients (except your mini chocolate chips) in a bowl to avoid clumping. Add 20ml \n" +
                "of cold water at a time and mix. Repeat this process until you get a thick cookie dough\u0002like consistency. Place that to fridge to chill. If you added too much liquid, add it to \n" +
                "freezer to thicken up. And if you want to make enough dough for 4 pies, multiply the \n" +
                "ingredient list by 4. \n" +
                "5. When your pie crusts are done, take them out to cool for 10-15 minutes. Once cool, \n" +
                "take your cookie dough out of the fridge/freezer and add your mini chocolate chips. \n" +
                "Add the dough to your pie crust and enjoy! I mean, just look at that bite!";

        String ingredients6 = "6g PEScience Select Protein flavor of your choice \n" +
                "4g Coconut Flour\n" +
                "4g Ultra Fine Almond Flour\n" +
                "4g All Purpose Flour\n" +
                "4g Powdered PB\n" +
                "5.5g Light Butter (this butter has around 5g Fat \n" +
                "per 14g serving)\n" +
                "11g Plain Nonfat Greek Yogurt\n" +
                "1g Stevia";

        list.add(new FoodItemModel("Pies", "Protein Cookie Dough Personal Pan Pie", "156", "4g", "16.5g", "13.5g", "1 Serving Per Recipe", directions6, ingredients6));

        String directions7 = "1. Preheat your oven to 450 degrees F. \n" +
                "2. Slice your lavash bread in half and lay that side flat. Add your cherry \n" +
                "pie filling to the middle, leaving about an inch around the edges. Fold \n" +
                "the long edges over first (to cover the insides), then fold the shorter \n" +
                "edges over to close the whole pie. Flip it over and spray the top with \n" +
                "butter spray. Cut slits along the top with a knife. Add your pie to the \n" +
                "oven to cook until it’s close to golden. \n" +
                "3. Once you take your pie out, let it cool. Sprinkle a little bit of your \n" +
                "sweetener on top, then slice it open. Enjoy every macro friendly bite of \n" +
                "cherry pie heaven! ";

        String ingredients7 = "1/2 Joseph’s Lavash Bread\n" +
                "85g No Sugar Added Cherry Pie Filling\n" +
                "Zero Cal Sweetener of your choice";

        list.add(new FoodItemModel("Pies", "Low Cal Baked Cherry Pie", "106", "2g", "6g", "16g", "1 Serving Per Recipe", directions7, ingredients7));
        FoodItemAdapter adapter = new FoodItemAdapter(this, list, foodClickListener);
        binding.mainCat.setAdapter(adapter);
    }

    private void getEntreesList() {
        ArrayList<FoodItemModel> list = new ArrayList<>();

        String directions = "1. Preheat your stove top pan on medium heat (5/10). Add your \n" +
                "egg whites to a small plate that has a dip in it (to hold the egg \n" +
                "whites) with a few pinches of your pizza seasoning. Let each side \n" +
                "of your bread soak for 30 seconds. \n" +
                "2. Add your slice to the pan and cook each side until golden. Once \n" +
                "it’s done cooking, place on your pizza pan and add your pizza \n" +
                "ingredients . Place that in the oven on broil for 2-3 minutes until \n" +
                "everything is golden. That’s it!";

        String ingredients = "1 slice of Nature’s Own Butterbread 30g Egg Whites\n" +
                "30g Marinara Sauce of your choice \n" +
                "7g Trader Joe’s Light Mozzarella\n" +
                "(any Part Skim Mozzarella will work or \n" +
                "cheese of your choice)\n" +
                "7g Fat Free Shredded Sharp Cheddar Cheese\n" +
                "(or cheese of your choice) \n" +
                "4g Turkey Pepperonis\n" +
                "Homemade Pizza Seasoning";

        list.add(new FoodItemModel("Pizza", "Pepperoni Pizza Frittata", "127", "3g", "11g", "14g", "1 Serving Per Recipe", directions, ingredients));

        String directions1 = "1. Preheat your oven to 550 degrees F and a stovetop pan on \n" +
                "medium to high heat. Whisk your egg whites and coconut flour \n" +
                "in a bowl along with some sea salt. Add that mixture to your \n" +
                "stovetop pan to cook until the top is no longer liquidy, then flip \n" +
                "and cook the other side until golden. While that’s cooking, add \n" +
                "your 2oz ground beef to another stovetop pan to cook. I like to \n" +
                "add some sea salt to my ground beef.\n" +
                "2. Add your pizza ingredients on top of your base (except your \n" +
                "avocadoes or bolthouse farms dressing) and let that cook in the \n" +
                "oven for 5-7 minutes. Once golden, put the oven on broil for a \n" +
                "minute or two so your pizza can really crisp up! \n" +
                "3. Go ahead and take your pizza out once it’s done, then add your \n" +
                "avocado and bolthouse farms dressing. Slice your pizza up and \n" +
                "enjoy every macro friendly bite of pizza. ";
        String ingredients1 = "184g Egg Whites (can sub with a whole egg that \n" +
                "weighs around 50g)\n" +
                "Note: if you do, you’ll subtract 50g from 184g and will still \n" +
                "need to add 134g of Egg Whites\n" +
                "5g Coconut Flour\n" +
                "2g Baking Powder";
        list.add(new FoodItemModel("Pizza", "Low Carb Taco Pizza", "41", "1.4g", "5.5g", "1.8g", "8 Serving Per Recipe", directions1, ingredients1));

        String directions2 = "1. Cook your burger, bacon, and whole egg in a stovetop pan. \n" +
                "Once your burger is almost done, add your cheese slice on top \n" +
                "to melt, and place a cover on top. \n" +
                "2. Slice your donut in half, add your burger to the bottom half, then \n" +
                "your bacon and whole egg. Place the top half of your donut on \n" +
                "top and enjoy the flavor bomb!";
        String ingredients2 = "1 Krispy Kreme Donut\n" +
                "4oz 96/4 Lean Ground Beef\n" +
                "1 Slice Fat Free Sharp Cheddar Cheese\n" +
                "2 Slices Turkey Bacon\n" +
                "1 Whole Egg";
        list.add(new FoodItemModel("Burgers", "Krispy Kreme Donut Burger", "482", "24g", "39g", "23g", "1 Serving Per Recipe", directions2, ingredients2));

        String directions3 = "1. Cook your burger and whole egg in your stovetop pan. Once \n" +
                "both are done, lay your tortilla flat. Add your whole egg (face \n" +
                "down), your cheese slice and shredded cheese, then your burger. \n" +
                "2. Now you’ll lay out your bacon to wrap your crunch wrap \n" +
                "in. Lay 4 slices of your bacon down, long ways, then 2 slices \n" +
                "horizontally. Add your crunch wrap on top and fold how you did \n" +
                "with your tortilla.\n" +
                "3. Add that to your pan on medium heat with the bottom facing \n" +
                "down to make sure it seals. Make sure to add a cover on the pan \n" +
                "to make sure everything cooks evenly. Once sealed, cook the the \n" +
                "other side, as well as all sides to sear the bacon. \n" +
                "4. Slice your wrap open, unveil the beautiful gains, and enjoy!";
        String ingredients3 = "1 Low Carb Tortilla\n" +
                "3 oz 96/4 Lean Ground Beef\n" +
                "1 Whole Egg\n" +
                "1 Slice Fat Free Sharp Cheddar Cheese\n" +
                "14g Shredded Fat Free Cheddar Cheese\n" +
                "6 Slices Turkey Bacon";
        list.add(new FoodItemModel("Burgers", "Bacon Wrapped Egg & Cheddar Crunch Wrap Burger", "461", "22g", "50g", "14g", "1 Serving Per Recipe", directions3, ingredients3));

        String directions4 = "1. Take your 96/4 lean ground beef, add whatever seasonings \n" +
                "you’d like, then form a rectangular patty that’ll fit into your hot \n" +
                "pocket. Cook your burger on a stovetop pan on medium/high \n" +
                "heat. Add your slice of canadian bacon to the pan and cook until \n" +
                "golden. \n" +
                "2. Once your burger is done, lay your ½ lavash bread down, then \n" +
                "add your canadian bacon, 10g shredded cheese, and cheese \n" +
                "slice (save a few grams to add at the end). Add your burger, then \n" +
                "fold. You want to fold long ways first, then your two ends. Add \n" +
                "the rest of your shredded cheese to help seal the bottom. Use a \n" +
                "toothpick to hold everything together until you add it to your pan.\n" +
                "3. Take your tooth pick out and add your hot pocket to the pan to \n" +
                "seal the bottom edges first. Once sealed, flip it over and toast \n" +
                "the other side. If the bottom side won’t stay shut, add a little more \n" +
                "shredded cheese, and flip again. \n" +
                "4. Slice open and enjoy!";
        String ingredients4 = "½ Joseph’s Lavash Bread (can use any \n" +
                "lavash but macros might be different)\n" +
                "4oz 96/4 Lean Ground Beef\n" +
                "1 Slice Canadian Bacon\n" +
                "14g Fat Free Shredded Sharp Cheddar \n" +
                "Cheese\n" +
                "1 Slice Fat Free Sharp Cheddar Cheese";
        list.add(new FoodItemModel("Hot Pockets & Crunch Wraps", "Low Carb Canadian Bacon Cheeseburger Hot Pocket", "242", "5g", "37g", "12g", "1 Serving Per Recipe", directions4, ingredients4));

        String directions5 = "1. Preheat your oven to 545 degrees F. Saute your broccoli, with \n" +
                "some sea salt, on your stovetop pan on medium/high heat.\n" +
                "2. Add your 14g mozzarella, queso, broccoli, and rest of the \n" +
                "mozzarella to the middle of your lavash bread. \n" +
                "3. Fold the long edges over first, then your ends. Add that to an \n" +
                "oven safe pan, with the bottom folds facing down, and spray the \n" +
                "top with butter spray. \n" +
                "4. Place your hot pocket in the oven to cook until golden. Let it cool, \n" +
                "slice it open, and enjoy!";
        String ingredients5 = "½ Joseph’s Lavash Bread\n" +
                "100g Frozen Broccoli\n" +
                "30g Trader Joe’s Light Queso\n" +
                "21g Trader Joe’s Light Mozzarella";
        list.add(new FoodItemModel("Hot Pockets & Crunch Wraps", "Low Carb Cheesy Broccoli Hot Pocket", "160", "4g", "13g", "18g", "1 Serving Per Recipe", directions5, ingredients5));

        String directions6 = "1. Grab the biggest bowl you have in your house because this \n" +
                "salad is a beast! Saute up your broccoli, green peppers and \n" +
                "jalapenos. While those are cooking, chop your lettuce and \n" +
                "add it to the bowl along with your spinach. Then take your \n" +
                "sautéed veggies and add those on top. Then add the rest of the \n" +
                "ingredients in order of the ingredient list! Honestly that’s how \n" +
                "simple it is! Enjoy!";
        String ingredients6 = "300g Lettuce\n" +
                "50g Spinach\n" +
                "200g Frozen Broccoli\n" +
                "200g Green Peppers\n" +
                "20g Jalapenos\n" +
                "100g Mushrooms\n" +
                "1 Medium Sliced Tomato\n" +
                "85g Broccoli Slaw\n" +
                "45g Bolthouse Farm Creamy Roasted Garlic \n" +
                "Dressing\n" +
                "Mustard\n" +
                "Franks Red Hot Buffalo Sauce\n" +
                "Kernal Seasons White Cheddar and Nacho \n" +
                "Cheddar";
        list.add(new FoodItemModel("Misc. Entrées", "Salad Gainzzz Bowl", "300", "5g", "18g", "42g", "1 Serving Per Recipe", directions6, ingredients6));

        String directions7 = "1. Preheat your oven to 550 degrees F. Cook your hot dog on \n" +
                "either a grill or stovetop pan. Poke holes in your hot dog to make \n" +
                "sure it cooks all the way through. \n" +
                "2. Take your hot dog roll and slice off the top with a knife, then \n" +
                "hollow out the bottom piece by taking out the breaded middle. \n" +
                "Add 1 slice of your fat free cheese to the bottom piece, then top \n" +
                "that with your hot dog, second slice of cheese, and shredded \n" +
                "cheese. \n" +
                "3. Put it in the oven for 10 minutes until golden. Enjoy that cheesy \n" +
                "hot dog crunch!";
        String ingredients7 = "97% Fat Free All Beef Frank\n" +
                "Deli Hot Dog Roll (mine has 20g Carbs per \n" +
                "serving)\n" +
                "2 Slice Fat Free Cheddar Cheese\n" +
                "1/2oz (14g) Shredded Fat Free Sharp \n" +
                "Cheddar";
        list.add(new FoodItemModel("Misc. Entrées", "The Cheddar Cheese Dog Bread Bowl ", "193", "2g", "21g", "21g", "1 Serving Per Recipe", directions7, ingredients7));

        String directions8 = "1. Preheat your oven to 450 degrees F. Take your pita and spray \n" +
                "both sides with nonstick butter spray. Take your oven safe taco \n" +
                "pan, add your pita to it, and put that in the oven for 5 minutes \n" +
                "until it starts to golden. Then take it out and flip your taco to have \n" +
                "it facing down/bottom facing up on top of one of the wedges. \n" +
                "Let it cook for another 2-3 minutes. \n" +
                "2. Cook your frozen broccoli on a stovetop pan on medium heat. \n" +
                "Add some sea salt and garlic powder for extra flavor! \n" +
                "3. Add half your broccoli and shredded cheese to your chalupa. \n" +
                "Then add the rest of your broccoli and your cheese slice. Put it in \n" +
                "the oven until the cheese is melted, then enjoy the crunch!";
        String ingredients8 = "1 Joseph’s Low Carb Pita\n" +
                "85g Frozen Broccoli\n" +
                "1 Slice Fat Free Sharp Cheddar Cheese\n" +
                "14g Fat Free Shredded Sharp Cheddar \n" +
                "Cheese";
        list.add(new FoodItemModel("Misc. Entrées", "Broccoli & Cheddar Low Carb Chalupa", "138", "2g", "16g", "15g", "1 Serving Per Recipe", directions8, ingredients8));
        FoodItemAdapter adapter = new FoodItemAdapter(this, list, foodClickListener);
        binding.mainCat.setAdapter(adapter);
    }

    private void getBreakfastList() {
        ArrayList<FoodItemModel> list = new ArrayList<>();

        String directions = "1. Preheat your stovetop pan and add your silicone egg circle to \n" +
                "the pan. This is what you’ll make your egg white patty with. \n" +
                "2. Add your egg whites to your silicone egg circle and once it has \n" +
                "preheated, put a cover on top.\n" +
                "3. Lay your tortilla flat and add your whole egg, face down. Then \n" +
                "add your turkey bacon, cheese, and egg white patty. Fold it up \n" +
                "like a crunch wrap and add some cheese on the bottom to seal \n" +
                "it. \n" +
                "4. Toast up your crunch wrap in your pan. Make sure you get both \n" +
                "the top and bottom, as well as the sides until golden. Slice it \n" +
                "open and unveil all of the macro friendly gainz!";

        String ingredients = "1 Low Carb Tortilla\n" +
                "92g Egg Whites\n" +
                "2 Slices Canadian Bacon\n" +
                "1 Slice Fat Free Sharp Cheddar Cheese";

        list.add(new FoodItemModel("Sandwiches & Crunch Wraps", "Breakfast Bacon, Egg & Cheddar Crunch Wrap", "248", "9g", "28g", "12g", "1 Serving Per Recipe", directions, ingredients));

        String directions1 = "1. Preheat your stovetop pan and add your silicone egg circle to the \n" +
                "pan. You’ll use this to cook your whole egg and egg white patty. \n" +
                "Once both are done cooking, pan fry your ham as well. \n" +
                "2. Lay your tortilla flat and add your canadian bacon. Then add \n" +
                "your slice of fat free cheese and your egg white patty. Fold it up \n" +
                "like a crunch wrap and add it to your pan, the folded side down, \n" +
                "to seal it. Once sealed, flip and toast the top, as well as the sides \n" +
                "to sear. \n" +
                "3. Once that is all done, you will take your crunch wrap, slice it in \n" +
                "half and enjoy!";
        String ingredients1 = "1 Low Carb Tortilla ( I use La Banderita )\n" +
                "92g Egg Whites\n" +
                "14g Fat Free Shredded Sharp Cheddar \n" +
                "Cheese\n" +
                "1 Whole Egg\n" +
                "1 Slice Turkey Bacon";

        list.add(new FoodItemModel("Sandwiches & Crunch Wraps", "Egg White Delight Crunch Wrap", "291", "3g", "31g", "15g", "1 Serving Per Recipe", directions1, ingredients1));

        String directions2 = "1. Preheat your oven to 350 degrees F. Mix all of your wet donut \n" +
                "ingredients in a bowl and your dry ingredients in a separate \n" +
                "bowl. Add your dry ingredients to your wet and mix. Slowly mix \n" +
                "in your almond milk until you get a thick, but not too thick batter. \n" +
                "Think of a pancake batter-like consistency. \n" +
                "2. Spray your donut pan with nonstick butter spray and add the \n" +
                "batter to each donut mold. Make sure not to fill them all the way \n" +
                "up since the donuts will rise when cooking. \n" +
                "3. Let your donuts cook in the oven for 8-10 minutes and watch \n" +
                "them rise. When they’re done, add them to your cooling rack to \n" +
                "ensure they cool evenly to room temperature. \n" +
                "4. Now you’ll make your protein icing. Add your greek yogurt to a \n" +
                "bowl, then add your dry ingredients on top and mix until you get \n" +
                "an extremely thick consistency. Mix in 10ml of cold water until \n" +
                "you get a thick frosting-like consistency. If you need to add more, \n" +
                "that’s okay. If you accidentally add too much, put your bowl in \n" +
                "the freezer for 5-10 minutes to thicken.\n" +
                "5. Drizzle your icing on your donuts and add any other toppings \n" +
                "your heart desires";

        String ingredients2 = "40g All Purpose Flour (can sub with Gluten Free Flour)\n" +
                "31g PEScience Gourmet Vanilla Select Protein \n" +
                "92g Egg Whites\n" +
                "3g Stevia\n" +
                "3g Vanilla Extract\n" +
                "3g Baking Powder\n" +
                "Unsweetened Vanilla Almond Milk as needed";

        list.add(new FoodItemModel("Donuts", "Oreo Cheesecake Protein Donuts", "68.75", "0.75g", "8.5g", "7g", "6 Servings Per Recipe", directions2, ingredients2));

        String directions3 = "1. Preheat your oven to 400 degrees F. Add all dry donut \n" +
                "ingredients to a bowl and mix. Then mix your wet ingredients in \n" +
                "another bowl. Slowly mix your dry ingredients with your wet until \n" +
                "you have a nice batter like consistency. Add more water if you \n" +
                "need to. \n" +
                "2. Add about half the batter to your giant donut pan, then place \n" +
                "that in the oven for 12-15 minutes. You’ll know it’s done when the \n" +
                "top is no longer liquidy. \n" +
                "3. Once your donut is done, add it to a cooling rack to ensure it \n" +
                "cools evenly.\n" +
                "4. While that’s cooling, make your protein icing. Add your greek \n" +
                "yogurt to a bowl, then add your dry ingredients on top and mix \n" +
                "until you get an extremely thick consistency. Slowly mix in your \n" +
                "cold water until you get a thick icing-like consistency. If you need \n" +
                "to add more, that’s okay. If you accidentally add too much, put \n" +
                "your bowl in the freezer for 5-10 minutes to thicken. Add your \n" +
                "food coloring in last to get the strawberry color.\n" +
                "5. Add your frosting using this tool, add some sprinkles on top \n" +
                "(if you want), then enjoy the beautiful Homer Simpson Donut \n" +
                "Gainzzz!";

        String ingredients3 = "1 (31g) Scoop PEScience Gourmet Vanilla \n" +
                "10g Coconut Flour\n" +
                "45g Unsweetened Apple Sauce\n" +
                "15g Pure Pumpkin\n" +
                "70g Egg Whites\n" +
                "90ml Unsweetened Vanilla Almond Milk\n" +
                "2g Vanilla Extract\n" +
                "3g Baking Powder\n" +
                "3g Zero Cal Sweetener of your choice";

        list.add(new FoodItemModel("Donuts", "Giant Homer Simson Donut", "385", "5g", "54g", "31g", "1 Serving Per Recipe", directions3, ingredients3));

        String directions4 = "1. Preheat your waffle maker. Mix your waffle ingredients in a bowl \n" +
                "and add some water if you need to. You want a thick but not too \n" +
                "thick batter. Add most of the batter to your waffle maker and \n" +
                "cook until golden. Repeat that process with the rest of your batter. \n" +
                "It should make up half the waffle maker. \n" +
                "2. While your waffles are cooling, you’ll make your protein icing. \n" +
                "Add your greek yogurt to a bowl, then add your dry ingredients \n" +
                "on top and mix until you get an extremely thick consistency. Mix \n" +
                "in 5ml of cold water until you get a thick icing-like consistency. \n" +
                "You can add in another 5ml if it’s not thick enough. If you \n" +
                "accidentally add too much, put your bowl in the freezer for 5-10 \n" +
                "minutes to thicken.\n" +
                "3. Stack your waffles to form a tower, then add your icing down the \n" +
                "sides. Add your Marshmallow Fruity Pebbles down the sides and \n" +
                "a scoop of Enlightened Vanilla Ice Cream on top. Enjoy!";

        String ingredients4 = "1 Scoop (31g) PEScience Gourmet Vanilla Select \n" +
                "20g Bisquick Heart Healthy Pancake Mix\n" +
                "5g Coconut Flour\n" +
                "46g Egg Whites\n" +
                "3g Zero Cal Sweetener of your choice\n" +
                "2g Vanilla Extract\n" +
                "30g Unsweetened Vanilla Almond Milk";

        list.add(new FoodItemModel("Waffles, Pancakes & French Toast", "Marshmallow Fruity Pebbles Protein Waffle Tower", "357", "5g", "43g", "35g", "1 Serving Per Recipe", directions4, ingredients4));

        String directions5 = "1. Preheat your waffle maker. Mix your waffle ingredients in a bowl \n" +
                "and add some water if you need to. You want a thick but not too \n" +
                "thick batter. Add most of the batter to your waffle maker and \n" +
                "cook until golden. Repeat that process with the rest of your batter. \n" +
                "It should make up half the waffle maker. \n" +
                "2. While your waffles are cooling, you’ll make your protein icing. \n" +
                "Add your greek yogurt to a bowl, then add your dry ingredients \n" +
                "on top and mix until you get an extremely thick consistency. Mix \n" +
                "in 5ml of cold water until you get a thick icing-like consistency. \n" +
                "You can add in another 5ml if it’s not thick enough. If you \n" +
                "accidentally add too much, put your bowl in the freezer for 5-10 \n" +
                "minutes to thicken.\n" +
                "3. Stack your waffles to form a tower, then add your icing down \n" +
                "the sides. Add your Reese’s Oreo down the sides and a scoop of \n" +
                "Enlightened Vanilla Ice Cream on top. Enjoy!";

        String ingredients5 = "1 Scoop (31g) PEScience Gourmet Vanilla Select \n" +
                "20g Bisquick Heart Healthy Pancake Mix\n" +
                "5g Coconut Flour\n" +
                "46g Egg Whites\n" +
                "3g Zero Cal Sweetener of your choice\n" +
                "2g Vanilla Extract\n" +
                "30g Unsweetened Vanilla Almond Milk\n" +
                "2g Baking Powder";

        list.add(new FoodItemModel("Waffles, Pancakes & French Toast", "Reese’s Oreo Protein Waffle Tower", "404", "8g", "43g", "40g", "1 Serving Per Recipe", directions5, ingredients5));

        String directions6 = "1. Preheat your oven to 450 degrees F. Put your light shredded mozzarella \n" +
                "and fat free cream cheese in a microwave safe bowl. Add that to the \n" +
                "microwave for 20 seconds. Take out, mix, add in your whole egg, then \n" +
                "mix some more. \n" +
                "2. Add all dry ingredients (except your seasoning) to a bowl and mix. \n" +
                "Add your dry ingredients with your wet and mix those together. Now, \n" +
                "you can add in your seasoning.\n" +
                "3. Spray your donut pan with non stick cooking spray, then fill two spots \n" +
                "with the batter. Place that in the oven for 10 minutes. Don’t open \n" +
                "the oven while your bagels are baking. When heat escapes, the \n" +
                "temperature of your oven changes. \n" +
                "4. Once your bagels are done, add them to a cooling rack to ensure they \n" +
                "cool evenly to room temperature. Then slice each bagel in half. \n" +
                "5. Add your cream cheese in between your bagel halves, cut in half, then \n" +
                "enjoy!";
        String ingredients6 = "21g Trader Joe’s Light Shredded Mozzarella\n" +
                "21g Fat Free Cream Cheese\n" +
                "1 Whole Egg\n" +
                "15g All Purpose Flour\n" +
                "3g Coconut Flour\n" +
                "3g Baking Powder\n" +
                "Trader Joe’s Everything Bagel Seasoning";

        list.add(new FoodItemModel("Bagels", "Low Carb Everything Bagel", "404", "8g", "43g", "40g", "1 Serving Per Recipe", directions6, ingredients6));

        String directions7 = "1. Preheat your oven to 350 degrees F. Mix all of your dry ingredients in a \n" +
                "bowl. Add your ricotta and mozzarella cheese to a microwave safe bowl. \n" +
                "Microwave that for for 20 seconds so the cheese can melt. \n" +
                "2. Take a spoon and mix, then add in your 46g egg whites and mix some more. \n" +
                "Slowly add your dry ingredients, in 3 increments, to avoid clumping.\n" +
                "3. Take your mini angel food cake pan and spray it with nonstick butter spray. \n" +
                "Add the batter to your pan and place your pan in the oven for 10-12 minutes. \n" +
                "4. While that’s cooking, mix your cream cheese and stevia together in a small \n" +
                "bowl. Add your bowl to the fridge if you want it to stay cold\n" +
                "5. Once your bagel is golden, let it cool for a second before slicing it in half. \n" +
                "Place both halves on a flat, oven safe pan (can be a pizza pan) with the open \n" +
                "sides facing up. Spray the tops with a little butter spray. Add your pan to the \n" +
                "oven so it can toast, like a bagel. Once the top’s are golden on top, let cool on \n" +
                "a cooling rack. Once cool, add your cream cheese and your crushed Oreo \n" +
                "pieces on top. Enjoy! These are the best when eaten fresh out of the oven or \n" +
                "shortly after!";

        String ingredients7 = "30g Trader Joe’s Reduced Fat Ricotta \n" +
                "Cheese (this one has 4.5g Fat per 60g serving)\n" +
                "28g Trader Joe’s Light Mozzarella Cheese\n" +
                "8g PEScience Gourmet Vanilla Select \n" +
                "8g All Purpose Flour (can use gluten free flour)\n" +
                "5g Black Cocoa Powder\n" +
                "3g Coconut Flour\n" +
                "46g Egg Whites\n" +
                "2g Baking Powder";

        list.add(new FoodItemModel("Bagels", "Homemade Low Carb Oreo Lovers Big Bagel", "145.5", "3.5g", "14g", "12g", "1 Serving Per Recipe", directions7, ingredients7));
        FoodItemAdapter adapter = new FoodItemAdapter(this, list, foodClickListener);
        binding.mainCat.setAdapter(adapter);
    }

    FoodItemClickListener foodClickListener = model -> {
        Stash.put(Constants.FOOD_DETAIL, model);
        startActivity(new Intent(SubCatActivity.this, FoodDetailActivity.class));
    };

}