package com.moutamid.myfitnesspal.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Toast;

import com.fxn.stash.Stash;
import com.google.android.gms.tasks.OnFailureListener;
import com.moutamid.myfitnesspal.MainActivity;
import com.moutamid.myfitnesspal.R;
import com.moutamid.myfitnesspal.databinding.ActivitySignupBinding;
import com.moutamid.myfitnesspal.models.UserModel;
import com.moutamid.myfitnesspal.utili.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding binding;
    final Calendar calendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Constants.changeTheme(this);

        Constants.initDialog(this);

        DatePickerDialog.OnDateSetListener date = (datePicker, year, month, day) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            updateLabel();
        };

        binding.age.getEditText().setOnClickListener(v -> {
            new DatePickerDialog(SignupActivity.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        binding.create.setOnClickListener(v -> {
            if (valid()){
                createAccount();
            }
        });

        binding.login.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });


    }

    private void createAccount() {
        Constants.showDialog();
        Constants.auth().createUserWithEmailAndPassword(
                binding.email.getEditText().getText().toString(),
                binding.password.getEditText().getText().toString()
        ).addOnSuccessListener(authResult -> {
            UserModel userModel = new UserModel(
                    Constants.auth().getCurrentUser().getUid(),
                    binding.name.getEditText().getText().toString(),
                    binding.email.getEditText().getText().toString(),
                    binding.password.getEditText().getText().toString(),
                    binding.male.isChecked() ? "Male" : "Female",
                    binding.height.getEditText().getText().toString(),
                    binding.weight.getEditText().getText().toString(),
                    binding.age.getEditText().getText().toString(), false
            );

            Constants.databaseReference().child(Constants.Users).child(Constants.auth().getCurrentUser().getUid())
                    .setValue(userModel).addOnSuccessListener(unused -> {
                        Stash.put(Constants.Users, userModel.getName());
                        Constants.dismissDialog();
                        startActivity(new Intent(SignupActivity.this, MainActivity.class));
                        finish();
                    }).addOnFailureListener(e -> {
                        Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        Constants.dismissDialog();
                    });

        }).addOnFailureListener(e -> {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            Constants.dismissDialog();
        });
    }

    private boolean valid() {
        if (binding.name.getEditText().getText().toString().isEmpty()){
            binding.name.setError("Name should not be empty");
            binding.name.requestFocus();
            return false;
        }
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
        if (!binding.male.isChecked() && !binding.female.isChecked()) {
            Toast.makeText(this, "Gender is not selected", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.age.getEditText().getText().toString().isEmpty()){
            binding.age.setError("Age should not be empty");
            binding.age.requestFocus();
            return false;
        }
        if (binding.height.getEditText().getText().toString().isEmpty()){
            binding.height.setError("Height should not be empty");
            binding.height.requestFocus();
            return false;
        }
        if (binding.weight.getEditText().getText().toString().isEmpty()){
            binding.weight.setError("Weight should not be empty");
            binding.weight.requestFocus();
            return false;
        }
        return true;
    }

    private void updateLabel() {
        binding.age.getEditText().setText(Constants.getFormatedDate(calendar.getTime().getTime()));
    }
}