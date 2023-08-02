package com.moutamid.myfitnesspal.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.fxn.stash.Stash;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.moutamid.myfitnesspal.MainActivity;
import com.moutamid.myfitnesspal.R;
import com.moutamid.myfitnesspal.databinding.ActivityLoginBinding;
import com.moutamid.myfitnesspal.models.UserModel;
import com.moutamid.myfitnesspal.utili.Constants;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Constants.changeTheme(this);

        Constants.initDialog(this);

        binding.login.setOnClickListener(v -> {
            if (valid()) {
                login();
            }
        });

        binding.forgotPassword.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, ForgotActivity.class)));

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

        binding.password.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.password.setErrorEnabled(false);
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


        binding.password.setErrorIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.password.getEditText().setText("");
            }
        });

    }

    private void login() {
        Constants.showDialog();
        Constants.auth().signInWithEmailAndPassword(
                binding.email.getEditText().getText().toString(),
                binding.password.getEditText().getText().toString()
        ).addOnSuccessListener(authResult -> {
            Constants.databaseReference().child(Constants.Users).child(Constants.auth().getCurrentUser().getUid())
                            .get().addOnSuccessListener(snapshot -> {
                                if (snapshot.exists()){
                                    UserModel userModel = snapshot.getValue(UserModel.class);
                                    Stash.put(Constants.Users, userModel.getName());
                                    Stash.put(Constants.isRankedAvailable, userModel.isRankedAvailable());
                                    Constants.dismissDialog();
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                }
                    }).addOnFailureListener(e -> {
                        Constants.dismissDialog();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    });
        }).addOnFailureListener(e -> {
            Constants.dismissDialog();
            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
        if (binding.password.getEditText().getText().toString().isEmpty()){
            binding.password.setError("Password should not be empty");
            binding.password.requestFocus();
            return false;
        }

        return true;
    }
}