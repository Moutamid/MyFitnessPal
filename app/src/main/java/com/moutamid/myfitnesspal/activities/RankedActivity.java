package com.moutamid.myfitnesspal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.moutamid.myfitnesspal.R;
import com.moutamid.myfitnesspal.adapters.RankAdapter;
import com.moutamid.myfitnesspal.databinding.ActivityRankedBinding;
import com.moutamid.myfitnesspal.models.CompModel;
import com.moutamid.myfitnesspal.utili.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RankedActivity extends AppCompatActivity {
    ActivityRankedBinding binding;
    ArrayList<CompModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRankedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Constants.changeTheme(this);

        Constants.initDialog(this);
        Constants.showDialog();
        binding.back.setOnClickListener(v -> onBackPressed());

        binding.rankRC.setLayoutManager(new LinearLayoutManager(this));
        binding.rankRC.setHasFixedSize(false);

        list = new ArrayList<>();

        Constants.databaseReference().child(Constants.Approved)
                .get().addOnSuccessListener(snapshot -> {
                    if (snapshot.exists()){
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                            CompModel model = dataSnapshot.getValue(CompModel.class);
                            list.add(model);
                        }
                        list.sort((person1, person2) -> Integer.compare(person2.getTotalPoints(), person1.getTotalPoints()));

                        for (int i = 0; i < list.size(); i++) {
                            CompModel person = list.get(i);
                            int rank = i + 1;
                            person.setRank(rank);
                            if (Constants.auth().getCurrentUser().getUid().equals(person.getId())){
                                binding.myRank.setText("#" + rank);
                            }
                        }

                        RankAdapter adapter = new RankAdapter(RankedActivity.this, list);
                        binding.rankRC.setAdapter(adapter);

                    }
                    Constants.dismissDialog();
                }).addOnFailureListener(e -> {
                    Constants.dismissDialog();
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                });

    }
}