package com.moutamid.myfitnesspal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
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
import com.moutamid.myfitnesspal.databinding.ActivityStartTrainingBinding;
import com.moutamid.myfitnesspal.models.TrainingModel;
import com.moutamid.myfitnesspal.utili.Constants;

import java.util.ArrayList;

public class StartTrainingActivity extends AppCompatActivity {
    ActivityStartTrainingBinding binding;
    String level, type;
    ArrayList<TrainingModel> trainingList;
    boolean isTimerRunning = false, run = true;
    int counter = 0;
    int inputSeconds;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStartTrainingBinding.inflate(getLayoutInflater());
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
                    if (run) {
                        inputSeconds = model.getTime();
                    }
                    startTimer(model);
                }
            } else {
                showRestDialog();
            }

        });

    }

    private void pauseTimer(TrainingModel model) {
        if (isTimerRunning) {
            isTimerRunning = false;
            binding.start.setText("Start");
            countDownTimer.cancel();
        }
    }

    private void showRestDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.rest_dialog);
        dialog.setCancelable(false);

        int count = this.counter;

        Log.d("CHECKINH" , "this Counter " + this.counter);
        Log.d("CHECKINH" , "count " + count);

        TextView counter = dialog.findViewById(R.id.counter);
        TextView position = dialog.findViewById(R.id.position);
        TextView name = dialog.findViewById(R.id.name);
        TextView time = dialog.findViewById(R.id.time);
        ImageView gif = dialog.findViewById(R.id.gif);
        Button skip = dialog.findViewById(R.id.skip);

        new CountDownTimer(25 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int secondsLeft = (int) (millisUntilFinished / 1000);
                if (secondsLeft >= 60) {
                    int s = secondsLeft;
                    int sec = s % 60;
                    int min = (s / 60) % 60;
                    counter.setText(min + ":" + sec);
                } else if (secondsLeft >= 10) {
                    counter.setText("00:" + secondsLeft);
                } else {
                    counter.setText("00:0" + secondsLeft);
                }
            }

            @Override
            public void onFinish() {
                dialog.dismiss();
                StartTrainingActivity.this.counter += 1;
                initView(StartTrainingActivity.this.counter);
            }
        }.start();

        if (trainingList.size()-1 <= count){
            count = trainingList.size() -1 ;
            Log.d("CHECKINH" , "IF count " + count);
        } else {
            Log.d("CHECKINH" , "else before count " + count);
            count += 1;
            Log.d("CHECKINH" , "else after count " + count);
        }

        Log.d("CHECKINH" , "this Counter " + this.counter);
        Log.d("CHECKINH" , "count " + count);

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
        isTimerRunning = true;
        countDownTimer = new CountDownTimer((inputSeconds* 1000L), 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int secondsLeft = (int) (millisUntilFinished / 1000);
                inputSeconds = secondsLeft;
                run = false;
                if (secondsLeft >= 60) {
                    int s = secondsLeft;
                    int sec = s % 60;
                    int min = (s / 60) % 60;
                    binding.trainingDuration.setText(min + ":" + sec);
                } else if (secondsLeft >= 10) {
                    binding.trainingDuration.setText("00:" + secondsLeft);
                } else {
                    binding.trainingDuration.setText("00:0" + secondsLeft);
                }
            }

            @Override
            public void onFinish() {
                run = true;
                showRestDialog();
            }
        }.start();
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

        if(counter >= trainingList.size()){
            finish();
        } else {

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
}