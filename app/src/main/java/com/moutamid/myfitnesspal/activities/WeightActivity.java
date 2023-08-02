package com.moutamid.myfitnesspal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

import com.fxn.stash.Stash;
import com.moutamid.myfitnesspal.R;
import com.moutamid.myfitnesspal.databinding.ActivityWeightBinding;
import com.moutamid.myfitnesspal.models.UserModel;
import com.moutamid.myfitnesspal.utili.Constants;

public class WeightActivity extends AppCompatActivity {
    ActivityWeightBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWeightBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Constants.changeTheme(this);

        Constants.initDialog(this);
        Constants.showDialog();

        binding.back.setOnClickListener(v -> onBackPressed());

        Constants.databaseReference().child(Constants.Users).child(Constants.auth().getCurrentUser().getUid())
                .get().addOnSuccessListener(snapshot -> {
                    if (snapshot.exists()) {
                        UserModel userModel = snapshot.getValue(UserModel.class);
                        Stash.put(Constants.Users, userModel.getName());
                        Constants.dismissDialog();

                        binding.weight.setText(userModel.getWeight() + " Kg");
                        binding.height.setText(userModel.getHeight() + " cm");

                    }
                }).addOnFailureListener(e -> {
                    Constants.dismissDialog();
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                });

    }

}