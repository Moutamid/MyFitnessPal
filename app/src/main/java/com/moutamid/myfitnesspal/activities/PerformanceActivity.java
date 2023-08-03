package com.moutamid.myfitnesspal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.moutamid.myfitnesspal.R;
import com.moutamid.myfitnesspal.adapters.RankAdapter;
import com.moutamid.myfitnesspal.databinding.ActivityPerformanceBinding;
import com.moutamid.myfitnesspal.models.CompModel;
import com.moutamid.myfitnesspal.utili.Constants;

public class PerformanceActivity extends AppCompatActivity {
    ActivityPerformanceBinding binding;
    String videoUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPerformanceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Constants.changeTheme(this);

        Constants.initDialog(this);
        Constants.showDialog();
        binding.back.setOnClickListener(v -> onBackPressed());


        Constants.databaseReference().child(Constants.Proofs).child(Constants.auth().getCurrentUser().getUid())
                .get().addOnSuccessListener(snapshot -> {
                    if (snapshot.exists()){
                        CompModel model = snapshot.getValue(CompModel.class);
                        binding.squat.setText(model.getSquart() + " Kg");
                        binding.deadLift.setText(model.getDeadLift() + " Kg");
                        binding.bench.setText(model.getBench() + " Kg");
                        videoUrl = model.getVideoUrl();

                        Uri videoUri = Uri.parse(videoUrl);
                        binding.video.setVideoURI(videoUri);
                        MediaController mediaController = new MediaController(this);
                        binding.video.setMediaController(mediaController);
                        mediaController.setAnchorView(binding.video);
                        binding.video.start();
                    }
                    Constants.dismissDialog();
                }).addOnFailureListener(e -> {
                    Constants.dismissDialog();
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                });

    }
}