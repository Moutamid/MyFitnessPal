package com.moutamid.myfitnesspal.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fxn.stash.Stash;
import com.moutamid.myfitnesspal.MainActivity;
import com.moutamid.myfitnesspal.R;
import com.moutamid.myfitnesspal.activities.LoginActivity;
import com.moutamid.myfitnesspal.activities.PerformanceActivity;
import com.moutamid.myfitnesspal.activities.RankedActivity;
import com.moutamid.myfitnesspal.activities.SubCatActivity;
import com.moutamid.myfitnesspal.activities.SubmitProofActivity;
import com.moutamid.myfitnesspal.databinding.FragmentCompBinding;
import com.moutamid.myfitnesspal.models.UserModel;
import com.moutamid.myfitnesspal.utili.Constants;

public class CompFragment extends Fragment {
    FragmentCompBinding binding;

    public CompFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCompBinding.inflate(getLayoutInflater(), container, false);

        Constants.initDialog(requireContext());
        Constants.showDialog();

        Constants.databaseReference().child(Constants.Users).child(Constants.auth().getCurrentUser().getUid())
                .get().addOnSuccessListener(snapshot -> {
                    if (snapshot.exists()) {
                        UserModel userModel = snapshot.getValue(UserModel.class);
                        Stash.put(Constants.Users, userModel.getName());
                        Stash.put(Constants.isRankedAvailable, userModel.isRankedAvailable());
                    }
                    Constants.dismissDialog();
                }).addOnFailureListener(e -> {
                    Constants.dismissDialog();
                });

        binding.ranked.setOnClickListener(v -> {
            if (Stash.getBoolean(Constants.isRankedAvailable, false)) {
                startActivity(new Intent(requireContext(), RankedActivity.class));
            } else {
                new AlertDialog.Builder(requireContext()).setMessage("You are not qualified for ranked yet send us a video of proof to be in the rank")
                        .setPositiveButton("Ok", ((dialog, which) -> dialog.dismiss()))
                        .show();
            }
        });
        binding.performance.setOnClickListener(v -> {
            if (Stash.getBoolean(Constants.isRankedAvailable, false)) {
                startActivity(new Intent(requireContext(), PerformanceActivity.class));
            } else {
                new AlertDialog.Builder(requireContext()).setMessage("You are not qualified for ranked yet send us a video of proof to be in the rank")
                        .setPositiveButton("Ok", ((dialog, which) -> dialog.dismiss()))
                        .show();
            }
        });
        binding.proof.setOnClickListener(v -> {
            startActivity(new Intent(requireContext(), SubmitProofActivity.class));
        });


        return binding.getRoot();
    }
}