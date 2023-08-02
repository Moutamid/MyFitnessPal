package com.moutamid.myfitnesspal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.moutamid.myfitnesspal.R;
import com.moutamid.myfitnesspal.databinding.ActivityRankedBinding;
import com.moutamid.myfitnesspal.utili.Constants;

public class RankedActivity extends AppCompatActivity {
    ActivityRankedBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRankedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Constants.changeTheme(this);

        Constants.initDialog(this);

        binding.back.setOnClickListener(v -> onBackPressed());


    }
}