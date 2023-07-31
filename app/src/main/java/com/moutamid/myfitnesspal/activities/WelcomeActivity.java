package com.moutamid.myfitnesspal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.moutamid.myfitnesspal.R;
import com.moutamid.myfitnesspal.databinding.ActivityWelcomeBinding;
import com.moutamid.myfitnesspal.utili.Constants;

public class WelcomeActivity extends AppCompatActivity {
    ActivityWelcomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Constants.checkApp(this);

        binding.login.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        binding.signup.setOnClickListener(v -> {
            startActivity(new Intent(this, SignupActivity.class));
            finish();
        });

    }
}