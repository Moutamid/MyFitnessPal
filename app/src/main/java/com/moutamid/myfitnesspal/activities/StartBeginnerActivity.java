package com.moutamid.myfitnesspal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fxn.stash.Stash;
import com.moutamid.myfitnesspal.R;
import com.moutamid.myfitnesspal.databinding.ActivityStartBeginnerBinding;
import com.moutamid.myfitnesspal.models.TrainingModel;
import com.moutamid.myfitnesspal.utili.Constants;

import java.util.ArrayList;

public class StartBeginnerActivity extends AppCompatActivity {
    ActivityStartBeginnerBinding binding;
    String level, type;
    ArrayList<TrainingModel> trainingList;
    boolean isTimerRunning = false;
    int counter = 0;
    private long startTime = 0L;
    private long pausedTime = 0L;
    private Handler handler = new Handler();
    int inputSeconds;

    private Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int secondsRemaining = (int) (inputSeconds  - millis / 1000);

            if (secondsRemaining >= 0) {
                int minutes = secondsRemaining / 60;
                int seconds = secondsRemaining % 60;
                String time = String.format("%02d:%02d", minutes, seconds);
                binding.trainingDuration.setText(time);
                handler.postDelayed(this, 1000);
            } else {
                binding.trainingDuration.setText("00:00");
                handler.removeCallbacks(timerRunnable);
                showRestDialog();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStartBeginnerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Constants.changeTheme(this);

        binding.back.setOnClickListener(v -> onBackPressed());

        trainingList = Stash.getArrayList(Constants.START_LIST, TrainingModel.class);

        type = Stash.getString(Constants.TRAINING_TYPE);
        level = Stash.getString(Constants.Level);

        initView(counter);

        binding.previous.setOnClickListener(v -> initView(counter - 1));
        binding.skip.setOnClickListener(v -> initView(counter + 1));

        binding.start.setOnClickListener(v -> {
            TrainingModel model = trainingList.get(counter);

            if (model.isDuration()) {
                if (isTimerRunning){
                    pauseTimer(model);
                } else {
                    startTimer(model);
                }
            } else {
                showRestDialog();
            }

        });

    }

    private void pauseTimer(TrainingModel model) {
        if (isTimerRunning) {
            handler.removeCallbacks(timerRunnable);
            pausedTime = System.currentTimeMillis() - startTime;
            isTimerRunning = false;
            binding.start.setText("Start");
        }
    }

    private void showRestDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.rest_dialog);
        dialog.setCancelable(false);

        int count = this.counter;

        TextView counter = dialog.findViewById(R.id.counter);
        TextView position = dialog.findViewById(R.id.position);
        TextView name = dialog.findViewById(R.id.name);
        TextView time = dialog.findViewById(R.id.time);
        ImageView gif = dialog.findViewById(R.id.gif);
        Button skip = dialog.findViewById(R.id.skip);

        if (trainingList.size()-1 >= count){
            count = trainingList.size() -1 ;
        } else {
            count += 1;
        }

        position.setText("NEXT " + (count+1) + "/" + trainingList.size());

        TrainingModel model = trainingList.get(count);

        Glide.with(this).load(model.getGif()).into(gif);
        name.setText(model.getName());

        if (model.isDuration()) {
            if (model.getTime() >= 60) {
                int s = model.getTime();
                int sec = s % 60;
                int min = (s / 60) % 60;
                time.setText(min + ":" + sec);
            } else if (model.getTime() >= 10) {
                time.setText("00:" + model.getTime());
            } else {
                time.setText("00:0" + model.getTime());
            }
        } else {
            time.setText("x " + model.getTime());
        }

        skip.setOnClickListener(v -> {
            dialog.dismiss();
            this.counter += 1;
            initView(this.counter);
        });


        dialog.getWindow().clearFlags( WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }

    private void startTimer(TrainingModel model) {
        binding.start.setText("Stop");
        inputSeconds = model.getTime();
        startTime = System.currentTimeMillis() - model.getTime() * 1000;
        isTimerRunning = true;
        handler.postDelayed(timerRunnable, 0);
    }

    private void initView(int count) {
        this.counter = count;
        if (counter == 0) {
            binding.previous.setVisibility(View.GONE);
        } else {
            binding.previous.setVisibility(View.VISIBLE);
        }

        if (counter == trainingList.size() - 1) {
            binding.skip.setVisibility(View.GONE);
        } else {
            binding.skip.setVisibility(View.VISIBLE);
        }

        TrainingModel model = trainingList.get(counter);
        Glide.with(this).load(model.getGif()).into(binding.gif);
        binding.trainingName.setText(model.getName());

        if (model.isDuration()) {
            if (model.getTime() >= 60) {
                int s = model.getTime();
                int sec = s % 60;
                int min = (s / 60) % 60;
                binding.trainingDuration.setText(min + ":" + sec);
            } else if (model.getTime() >= 10) {
                binding.trainingDuration.setText("00:" + model.getTime());
            } else {
                binding.trainingDuration.setText("00:0" + model.getTime());
            }
            binding.start.setText("Start");
        } else {
            binding.trainingDuration.setText("x " + model.getTime());
            binding.start.setText("Done");
        }

    }
}