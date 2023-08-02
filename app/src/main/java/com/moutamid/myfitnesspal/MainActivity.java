package com.moutamid.myfitnesspal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;

import com.fxn.stash.Stash;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.moutamid.myfitnesspal.databinding.ActivityMainBinding;
import com.moutamid.myfitnesspal.fragments.CompFragment;
import com.moutamid.myfitnesspal.fragments.HomeFragment;
import com.moutamid.myfitnesspal.fragments.SettingFragment;
import com.moutamid.myfitnesspal.utili.Constants;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Constants.checkApp(this);

        Constants.changeTheme(this);

        binding.bottomNav.setItemActiveIndicatorColor(ColorStateList.valueOf(getResources().getColor(R.color.bottomNavIndicator)));
        binding.bottomNav.setItemActiveIndicatorWidth(100);
        binding.bottomNav.setItemActiveIndicatorHeight(100);
//        binding.bottomNav.setItemActiveIndicatorShapeAppearance(new ShapeAppearanceModel().withCornerSize(50f).toBuilder().build());
        binding.bottomNav.setOnNavigationItemSelectedListener(this);
        binding.bottomNav.setSelectedItemId(R.id.nav_home);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_home ){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout , new HomeFragment()).commit();
            return true;
        } else  if (item.getItemId() == R.id.nav_compi ){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout , new CompFragment()).commit();
            return true;
        } else  if (item.getItemId() == R.id.nav_setting ){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout , new SettingFragment()).commit();
            return true;
        }
        return false;
    }
}