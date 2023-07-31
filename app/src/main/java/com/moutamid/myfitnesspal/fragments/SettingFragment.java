package com.moutamid.myfitnesspal.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fxn.stash.Stash;
import com.moutamid.myfitnesspal.R;
import com.moutamid.myfitnesspal.SplashScreenActivity;
import com.moutamid.myfitnesspal.activities.EditProfileActivity;
import com.moutamid.myfitnesspal.databinding.FragmentSettingBinding;
import com.moutamid.myfitnesspal.utili.Constants;

public class SettingFragment extends Fragment {
    FragmentSettingBinding binding;
    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSettingBinding.inflate(getLayoutInflater(), container, false);

        binding.name.setText(Stash.getString(Constants.Users));

        binding.editProfile.setOnClickListener(v -> {
            startActivity(new Intent(requireContext(), EditProfileActivity.class));
        });

        binding.logout.setOnClickListener(v -> {
            new AlertDialog.Builder(requireContext(), R.style.AlertDialogStyle).setMessage("Do you really want to sign out?").setTitle("Sign out")
                    .setNegativeButton("No", ((dialog, which) -> dialog.dismiss()))
                    .setPositiveButton("Yes", ((dialog, which) -> {
                        Constants.auth().signOut();
                        startActivity(new Intent(requireContext(), SplashScreenActivity.class));
                        requireActivity().finish();
                    }))
                    .show();
        });

        return binding.getRoot();
    }
}