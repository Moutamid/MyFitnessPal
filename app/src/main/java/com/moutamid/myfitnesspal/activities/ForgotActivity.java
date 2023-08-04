package com.moutamid.myfitnesspal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.fxn.stash.Stash;
import com.moutamid.myfitnesspal.R;
import com.moutamid.myfitnesspal.databinding.ActivityForgotBinding;
import com.moutamid.myfitnesspal.utili.Constants;

public class ForgotActivity extends AppCompatActivity {
    ActivityForgotBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Constants.changeTheme(this);

        Constants.initDialog(this);

        binding.back.setOnClickListener(v -> onBackPressed());

        binding.email.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.email.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.email.setErrorIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.email.getEditText().setText("");
            }
        });


        binding.login.setOnClickListener(v -> {
            if (valid()) {
                Constants.showDialog();
                reset();
            }
        });

    }

    private void reset() {
        Constants.auth().sendPasswordResetEmail(binding.email.getEditText().getText().toString())
                .addOnSuccessListener(unused -> {
                    Constants.dismissDialog();
                    Toast.makeText(this, "A password reset link sent to your email check your mailbox", Toast.LENGTH_SHORT).show();
                    finish();
                }).addOnFailureListener(e -> {
                    Constants.dismissDialog();
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private boolean valid() {
        if (binding.email.getEditText().getText().toString().isEmpty()){
            binding.email.setError("Email should not be empty");
            binding.email.requestFocus();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(binding.email.getEditText().getText().toString()).matches()){
            binding.email.setError("Email is not valid");
            binding.email.requestFocus();
            return false;
        }
        return true;
    }


}