package com.moutamid.myfitnesspal.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.moutamid.myfitnesspal.R;
import com.moutamid.myfitnesspal.databinding.ActivitySubmitProofBinding;
import com.moutamid.myfitnesspal.models.CompModel;
import com.moutamid.myfitnesspal.utili.Constants;

import java.io.IOException;

public class SubmitProofActivity extends AppCompatActivity {
    ActivitySubmitProofBinding binding;
    private static final int REQUEST_PICK_VIDEO = 1;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubmitProofBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Constants.changeTheme(this);

        Constants.initDialog(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading video...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);

        binding.back.setOnClickListener(v -> onBackPressed());

        binding.upload.setOnClickListener(v -> {
            if (valid()) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("video/*");
                startActivityForResult(intent, REQUEST_PICK_VIDEO);
            }
        });

    }

    private boolean valid() {
        if (binding.squat.getEditText().getText().toString().isEmpty()) {
            Toast.makeText(this, "Add Squat Weight", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.deadlift.getEditText().getText().toString().isEmpty()) {
            Toast.makeText(this, "Add DeadLift Weight", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.bench.getEditText().getText().toString().isEmpty()) {
            Toast.makeText(this, "Add Bench Weight", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PICK_VIDEO && resultCode == RESULT_OK && data != null) {
            Uri selectedVideoUri = data.getData();
            if (isVideoShortEnough(selectedVideoUri, 30)) {
                uploadVideoToFirebase(selectedVideoUri);
            } else {
                Toast.makeText(this, "Please select a video that is 30 seconds or less.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void uploadVideoToFirebase(Uri videoUri) {
        String fileName = "video_" + System.currentTimeMillis() + ".mp4";
        StorageReference videoRef = Constants.storageReference(Constants.auth().getCurrentUser().getUid()).child("videos/" + fileName);
        UploadTask uploadTask = videoRef.putFile(videoUri);
        progressDialog.show();
        uploadTask.addOnSuccessListener(taskSnapshot -> {
            progressDialog.dismiss();
            videoRef.getDownloadUrl().addOnSuccessListener(uri -> {
                Constants.showDialog();
                String videoUrl = uri.toString();
                double squat = Double.parseDouble(binding.squat.getEditText().getText().toString());
                double deadLift = Double.parseDouble(binding.deadlift.getEditText().getText().toString());
                double bench = Double.parseDouble(binding.bench.getEditText().getText().toString());
                CompModel model = new CompModel(Constants.auth().getCurrentUser().getUid(), squat, deadLift, bench, videoUrl);
                Constants.databaseReference().child(Constants.Proofs).child(Constants.auth().getCurrentUser().getUid())
                        .setValue(model).addOnSuccessListener(unused -> {
                            Constants.dismissDialog();
                            Toast.makeText(this, "Video uploaded successfully.", Toast.LENGTH_SHORT).show();
                            finish();
                        }).addOnFailureListener(e -> {
                            Constants.dismissDialog();
                            Toast.makeText(this, "Video upload failed.", Toast.LENGTH_SHORT).show();
                        });
            }).addOnFailureListener(e -> {
                progressDialog.dismiss();
                Toast.makeText(this, "Failed to get video URL.", Toast.LENGTH_SHORT).show();
            });
        }).addOnProgressListener(snapshot -> {
            int progress = (int) (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
            progressDialog.setProgress(progress);
        }).addOnFailureListener(e -> {
            progressDialog.dismiss();
            Toast.makeText(this, "Video upload failed.", Toast.LENGTH_SHORT).show();
        });
    }

    private boolean isVideoShortEnough(Uri videoUri, int maxDurationInSeconds) {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(this, videoUri);
            String durationStr = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            long durationInMillis = Long.parseLong(durationStr);
            long durationInSeconds = durationInMillis / 1000;
            retriever.release();
            return durationInSeconds <= maxDurationInSeconds;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}