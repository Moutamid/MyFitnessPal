package com.moutamid.myfitnesspal.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fxn.stash.Stash;
import com.moutamid.myfitnesspal.R;
import com.moutamid.myfitnesspal.databinding.FragmentHomeBinding;
import com.moutamid.myfitnesspal.utili.Constants;

import java.util.Calendar;

public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(getLayoutInflater(), container, false);

        greetingMessage();

        return binding.getRoot();
    }

    private void greetingMessage() {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 0 && timeOfDay < 12){
            binding.greeting.setText("Good Morning, " + getUsername());
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            binding.greeting.setText("Good Afternoon, " + getUsername());
        }else if(timeOfDay >= 16 && timeOfDay < 21){
            binding.greeting.setText("Good Evening, " + getUsername());
        }else if(timeOfDay >= 21 && timeOfDay < 24){
            binding.greeting.setText("Good Night, " + getUsername());
        }
    }

    private String getUsername() {
        String name = Stash.getString(Constants.Users, "Suleman");
        String[] words = name.split(" ");

        try {
            if (words.length >= 1) {
                name= words[0];
            }
        } catch (Exception e){}

        return name;
    }

}