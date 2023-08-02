package com.moutamid.myfitnesspal.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
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
        binding.privacy.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://www.google.com");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
        binding.terms.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://www.google.com");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

        if (Stash.getString(Constants.THEME, Constants.LIGHT).equals(Constants.DARK)) {
            binding.theme.setImageResource(R.drawable.nightlight);
        } else if (Stash.getString(Constants.THEME, Constants.LIGHT).equals(Constants.LIGHT)) {
            binding.theme.setImageResource(R.drawable.sunny);
        } else if (Stash.getString(Constants.THEME, Constants.LIGHT).equals(Constants.SYSTEM)) {
            binding.theme.setImageResource(R.drawable.day_and_night);
        }

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

        binding.theme.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(requireContext(), v, 0, 0, R.style.PopupMenu);
            popupMenu.getMenuInflater().inflate(R.menu.theme_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    // Toast message on menu item clicked
                    if (menuItem.getItemId() == R.id.nav_light){
                        binding.theme.setImageResource(R.drawable.sunny);
                        Stash.put(Constants.THEME, Constants.LIGHT);
                        requireActivity().recreate();
                    }
                    if (menuItem.getItemId() == R.id.nav_night){
                        binding.theme.setImageResource(R.drawable.nightlight);
                        Stash.put(Constants.THEME, Constants.DARK);
                        requireActivity().recreate();
                    }
                    if (menuItem.getItemId() == R.id.nav_system){
                        binding.theme.setImageResource(R.drawable.day_and_night);
                        Stash.put(Constants.THEME, Constants.SYSTEM);
                        requireActivity().recreate();
                    }
                    return true;
                }
            });
            popupMenu.show();
        });



        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.name.setText(Stash.getString(Constants.Users));
    }
}