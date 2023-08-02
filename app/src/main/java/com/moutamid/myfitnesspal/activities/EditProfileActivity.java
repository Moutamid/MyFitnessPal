package com.moutamid.myfitnesspal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Toast;

import com.fxn.stash.Stash;
import com.moutamid.myfitnesspal.MainActivity;
import com.moutamid.myfitnesspal.R;
import com.moutamid.myfitnesspal.databinding.ActivityEditProfileBinding;
import com.moutamid.myfitnesspal.models.UserModel;
import com.moutamid.myfitnesspal.utili.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {
    ActivityEditProfileBinding binding;
    final Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Constants.changeTheme(this);

        Constants.initDialog(this);
        Constants.showDialog();

        binding.back.setOnClickListener(v -> onBackPressed());

        DatePickerDialog.OnDateSetListener date = (datePicker, year, month, day) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            updateLabel();
        };

        binding.age.getEditText().setOnClickListener(v -> {
            new DatePickerDialog(EditProfileActivity.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        Constants.databaseReference().child(Constants.Users).child(Constants.auth().getCurrentUser().getUid())
                .get().addOnSuccessListener(snapshot -> {
                    if (snapshot.exists()) {
                        UserModel userModel = snapshot.getValue(UserModel.class);
                        Stash.put(Constants.Users, userModel.getName());
                        Constants.dismissDialog();

                        binding.name.getEditText().setText(userModel.getName());
                        binding.age.getEditText().setText(userModel.getAge());
                        binding.weight.getEditText().setText(userModel.getWeight());
                        binding.height.getEditText().setText(userModel.getHeight());

                        if (userModel.getGender().equals("Male")) {
                            binding.male.setChecked(true);
                        } else {
                            binding.female.setChecked(true);
                        }
                    }
                }).addOnFailureListener(e -> {
                    Constants.dismissDialog();
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                });

        binding.update.setOnClickListener(v -> {
            if (valid()) {
                Constants.showDialog();
                updateProfile();
            }
        });


    }

    private boolean valid() {
        if (binding.name.getEditText().getText().toString().isEmpty()) {
            binding.name.setError("Name should not be empty");
            binding.name.requestFocus();
            return false;
        }
        if (!binding.male.isChecked() && !binding.female.isChecked()) {
            Toast.makeText(this, "Gender is not selected", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.age.getEditText().getText().toString().isEmpty()) {
            binding.age.setError("Age should not be empty");
            binding.age.requestFocus();
            return false;
        }
        if (binding.height.getEditText().getText().toString().isEmpty()) {
            binding.height.setError("Height should not be empty");
            binding.height.requestFocus();
            return false;
        }
        if (binding.weight.getEditText().getText().toString().isEmpty()) {
            binding.weight.setError("Weight should not be empty");
            binding.weight.requestFocus();
            return false;
        }
        return true;
    }

    private void updateLabel() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATEFORMATE, Locale.getDefault());
        binding.age.getEditText().setText(dateFormat.format(calendar.getTime()));
    }

    private void updateProfile() {
        String gender = binding.male.isChecked() ? "Male" : "Female";

        Map<String, Object> user = new HashMap<>();
        user.put("name", binding.name.getEditText().getText().toString());
        user.put("gender", gender);
        user.put("height", binding.height.getEditText().getText().toString());
        user.put("weight", binding.weight.getEditText().getText().toString());
        user.put("age", binding.age.getEditText().getText().toString());

        Constants.databaseReference().child(Constants.Users).child(Constants.auth().getCurrentUser().getUid())
                .updateChildren(user).addOnSuccessListener(unused -> {
                    Stash.put(Constants.Users, user.get("name").toString());
                    Constants.dismissDialog();
                    Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }).addOnFailureListener(e -> {
                    Toast.makeText(EditProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    Constants.dismissDialog();
                });
    }

}