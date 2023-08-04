package com.moutamid.myfitnesspal.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.fxn.stash.Stash;
import com.moutamid.myfitnesspal.R;
import com.moutamid.myfitnesspal.adapters.TrainingAdapter;
import com.moutamid.myfitnesspal.databinding.ActivityTrainingExerciseBinding;
import com.moutamid.myfitnesspal.models.TrainingModel;
import com.moutamid.myfitnesspal.utili.Constants;

import java.util.ArrayList;
import java.util.Collections;

public class TrainingExerciseActivity extends AppCompatActivity {
    ActivityTrainingExerciseBinding binding;
    String level, type;
    TrainingAdapter adapter;
    ArrayList<TrainingModel> trainingList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTrainingExerciseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Constants.changeTheme(this);

        trainingList = new ArrayList<>();

        type = Stash.getString(Constants.TRAINING_TYPE);
        level = Stash.getString(Constants.Level);
        String heading = type + " " + level;
        binding.heading.setText(heading);

        binding.back.setOnClickListener(v -> onBackPressed());

        binding.workoutRC.setLayoutManager(new LinearLayoutManager(this));
        binding.workoutRC.setHasFixedSize(false);

        if (level.equals(Constants.Beginner)){
            initBeginnersViews();
        } else if (level.equals(Constants.Intermediate)) {
            initInternViews();
        } else if (level.equals(Constants.Advance)) {
            initAdvanceViews();
        }

        binding.start.setOnClickListener(v -> {
            Stash.put(Constants.START_LIST, trainingList);
            startActivity(new Intent(this, StartTrainingActivity.class));
        });

        ItemTouchHelper.Callback ithCallback = new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                return makeFlag(ItemTouchHelper.ACTION_STATE_DRAG,
                        ItemTouchHelper.DOWN | ItemTouchHelper.UP);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                Collections.swap(trainingList, viewHolder.getAdapterPosition(), target.getAdapterPosition());
                adapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                Log.d("PositionTabs", "holder : " + viewHolder.getAdapterPosition() + "   target : " + target.getAdapterPosition() );

                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            }
        };
        ItemTouchHelper ith = new ItemTouchHelper(ithCallback);
        ith.attachToRecyclerView(binding.workoutRC);


    }

    private void setBeginnersDataAbs() {
        ArrayList<String> focusArea1 = new ArrayList<>();
        focusArea1.add("Shoulders");
        focusArea1.add("Quadriceps");
        focusArea1.add("Adductors");
        focusArea1.add("Chest");
        focusArea1.add("Glutes");
        focusArea1.add("Calves");
        String desc = "Start with your feet togather and your arms by your sides then jump up with your feet apart and your hands overhead. \n\n" +
                "Return to the start position then do the next rep. This exercise provides a full-body workout and works all your large muscle groups";
        trainingList.add(new TrainingModel("Jumping Jacks", desc, R.drawable.house, 20, true, focusArea1));

        ArrayList<String> focusArea2 = new ArrayList<>();
        focusArea2.add("Abs");
        String desc2 = "Lie on your back with knees bent and your arms stretched forward. \n\n" +
                "Then lift your upper off the floor. Hold for a few seconds and slowly return. \n\n" +
                "It primarlily works the rectus abdominis muscle and the obliques.";
        trainingList.add(new TrainingModel("Abdominal Crunches", desc2, R.drawable.house, 16, false, focusArea2));

        ArrayList<String> focusArea3 = new ArrayList<>();
        focusArea3.add("Abs");
        String desc3 = "Sit on the floor with your knees bent, feet lifted a little bit back tilted backwards. \n\n" +
                "Then hold your hands together and twist from side to side. \n\n" +
                "Each Side x 10";
        trainingList.add(new TrainingModel("Russian Twist", desc3, R.drawable.house, 20, false, focusArea3));

        ArrayList<String> focusArea4 = new ArrayList<>();
        focusArea4.add("Abs");
        focusArea4.add("Glutes");
        focusArea4.add("Calves");
        focusArea4.add("Lower back");
        focusArea4.add("Quadriceps");
        String desc4 = "Start in the push-up position. Bend your right knee towards your chest and keep your left leg straight, then quickly witch from one leg to other. \n\n" +
                "This exercise strengthens multiple muscle groups. \n\n" +
                "Each Side x 8";
        trainingList.add(new TrainingModel("Mountain Climber", desc4, R.drawable.house, 16, false, focusArea4));

        adapter = new TrainingAdapter(this, trainingList);
        binding.workoutRC.setAdapter(adapter);

    }

    private void setBeginnersDataChest() {
        ArrayList<String> focusArea1 = new ArrayList<>();
        focusArea1.add("Shoulders");
        focusArea1.add("Quadriceps");
        focusArea1.add("Adductors");
        focusArea1.add("Chest");
        focusArea1.add("Glutes");
        focusArea1.add("Calves");
        String desc = "Start with your feet togather and your arms by your sides then jump up with your feet apart and your hands overhead. \n\n" +
                "Return to the start position then do the next rep. This exercise provides a full-body workout and works all your large muscle groups";
        trainingList.add(new TrainingModel("Jumping Jacks", desc, R.drawable.house, 30, true, focusArea1));

        ArrayList<String> focusArea2 = new ArrayList<>();
        focusArea2.add("Chest");
        focusArea2.add("Triceps");
        focusArea2.add("Shoulder");
        String desc2 = "Start in the regular push-up position but with your hands elevated on a chair or bench. \n\n" +
                "Then push your body up down using your arm strength. \n\n" +
                "Remember to keep your body straight.";
        trainingList.add(new TrainingModel("Incline Push-Ups", desc2, R.drawable.house, 16, false, focusArea2));

        ArrayList<String> focusArea3 = new ArrayList<>();
        focusArea3.add("Chest");
        focusArea3.add("Triceps");
        focusArea3.add("Shoulder");
        String desc3 = "Start in the regular push-up position, then let your knees touch the floor and raise your feet up off the floor.\n\n" +
                "Next push your body up and down.";
        trainingList.add(new TrainingModel("Knee Push-Ups", desc3, R.drawable.house, 12, false, focusArea3));

        ArrayList<String> focusArea4 = new ArrayList<>();
        focusArea4.add("Chest");
        focusArea4.add("Triceps");
        focusArea4.add("Shoulder");
        String desc4 = "Lay prone on the groud with arms supporting your body.\n\n" +
                "keep your body straight while raising and lowering your body with your arms.\n\n" +
                "This exercise work the chest, shoulders, triceps, triceps, back and legs.";
        trainingList.add(new TrainingModel("Push-Ups", desc4, R.drawable.house, 10, false, focusArea4));

        ArrayList<String> focusArea5 = new ArrayList<>();
        focusArea5.add("Chest");
        focusArea5.add("Triceps");
        focusArea5.add("Shoulder");
        String desc5 = "Start in the regular push-up position but with your hands spread wider than your shoulders.\n\n" +
                "Then push your body up and down. Remember to keep your body straight.";
        trainingList.add(new TrainingModel("Push-Ups", desc5, R.drawable.house, 10, false, focusArea5));

        adapter = new TrainingAdapter(this, trainingList);
        binding.workoutRC.setAdapter(adapter);

    }

    private void setBeginnersDataArm() {
        ArrayList<String> focusArea1 = new ArrayList<>();
        focusArea1.add("Shoulders");
        focusArea1.add("Chest");
        String desc = "Stand on the floor with your arms extended straight forward at shoulder height.\n\n" +
                "Raise your arms above your head. Return to the start position and repeat.";
        trainingList.add(new TrainingModel("Arm Raises", desc, R.drawable.house, 30, true, focusArea1));

        ArrayList<String> focusArea2 = new ArrayList<>();
        focusArea2.add("Triceps");
        focusArea2.add("Chest");
        focusArea2.add("Back");
        focusArea2.add("Shoulder");
        String desc2 = "For the start position, sit on the chair. Then move your hip off the chair with your hands holding the edge of the chair.\n\n" +
                "Slowly bend and stretch your arms to make your body go up and dow. This is a great exercise for the triceps";
        trainingList.add(new TrainingModel("Triceps Dips", desc2, R.drawable.house, 10, false, focusArea2));

        ArrayList<String> focusArea3 = new ArrayList<>();
        focusArea3.add("Shoulder");
        String desc3 = "Stand with your feet shoulder width apart.\n\n" +
                "Raise your arms to the sides at the shoulder height, then put them down.\n\n" +
                "Repeat the exercise. Keeps your arms straight during the exercise.";
        trainingList.add(new TrainingModel("Side Arm Raise", desc3, R.drawable.house, 30, true, focusArea3));

        ArrayList<String> focusArea4 = new ArrayList<>();
        focusArea4.add("Shoulder");
        focusArea4.add("Biceps");
        String desc4 = "Stand on the floor with your arms extended straight out to the sides at the shoulder height.\n\n" +
                "Move your arms clockwise in th circles fast.\n\n" +
                "Try to do ot as fast as you can.\n\n" +
                "it\'s a great exercise for the deltoid muscle.";
        trainingList.add(new TrainingModel("Arm Circles Clockwise", desc4, R.drawable.house, 30, true, focusArea4));

        ArrayList<String> focusArea5 = new ArrayList<>();
        focusArea5.add("Shoulder");
        focusArea5.add("Biceps");
        String desc5 = "Stand on the floor with your arms extended straight out to the sides at the shoulder height.\n\n" +
                "Move your arms counter clockwise in th circles fast.\n\n" +
                "Try to do ot as fast as you can.\n\n" +
                "it\'s a great exercise for the deltoid muscle.";
        trainingList.add(new TrainingModel("Arm Circles CounterClockwise", desc5, R.drawable.house, 30, true, focusArea5));

        adapter = new TrainingAdapter(this, trainingList);
        binding.workoutRC.setAdapter(adapter);

    }

    private void initAdvanceViews() {
        if (type.equals(Constants.ABS)){
            binding.totalTime.setText("36 mins");
            binding.totalWorkouts.setText("21 workouts");
        } else if (type.equals(Constants.CHEST)) {
            binding.totalTime.setText("19 mins");
            binding.totalWorkouts.setText("16 workouts");
        } else if (type.equals(Constants.ARM)) {
            binding.totalTime.setText("32 mins");
            binding.totalWorkouts.setText("28 workouts");
        } else if (type.equals(Constants.LEG)) {
            binding.totalTime.setText("53 mins");
            binding.totalWorkouts.setText("43 workouts");
        } else if (type.equals(Constants.SHOULDER)) {
            binding.totalTime.setText("19 mins");
            binding.totalWorkouts.setText("17 workouts");
        }
    }

    private void initInternViews() {
        if (type.equals(Constants.ABS)){
            binding.totalTime.setText("29 mins");
            binding.totalWorkouts.setText("21 workouts");
        } else if (type.equals(Constants.CHEST)) {
            binding.totalTime.setText("15 mins");
            binding.totalWorkouts.setText("14 workouts");
        } else if (type.equals(Constants.ARM)) {
            binding.totalTime.setText("26 mins");
            binding.totalWorkouts.setText("25 workouts");
        } else if (type.equals(Constants.LEG)) {
            binding.totalTime.setText("41 mins");
            binding.totalWorkouts.setText("36 workouts");
        } else if (type.equals(Constants.SHOULDER)) {
            binding.totalTime.setText("19 mins");
            binding.totalWorkouts.setText("17 workouts");
        }
    }

    private void initBeginnersViews() {
        if (type.equals(Constants.ABS)){
            binding.totalTime.setText("20 mins");
            binding.totalWorkouts.setText("16 workouts");
            setBeginnersDataAbs();
        } else if (type.equals(Constants.CHEST)) {
            binding.totalTime.setText("11 mins");
            binding.totalWorkouts.setText("11 workouts");
            setBeginnersDataChest();
        } else if (type.equals(Constants.ARM)) {
            binding.totalTime.setText("17 mins");
            binding.totalWorkouts.setText("19 workouts");
            setBeginnersDataArm();
        } else if (type.equals(Constants.LEG)) {
            binding.totalTime.setText("26 mins");
            binding.totalWorkouts.setText("23 workouts");
        } else if (type.equals(Constants.SHOULDER)) {
            binding.totalTime.setText("17 mins");
            binding.totalWorkouts.setText("17 workouts");
        }
    }
}