package com.moutamid.myfitnesspal.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Toast;

import com.fxn.stash.Stash;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.moutamid.myfitnesspal.R;
import com.moutamid.myfitnesspal.databinding.ActivityWeightBinding;
import com.moutamid.myfitnesspal.models.UserModel;
import com.moutamid.myfitnesspal.utili.Constants;

import java.util.ArrayList;
import java.util.List;

public class WeightActivity extends AppCompatActivity {
    ActivityWeightBinding binding;
    private LineChart chart;
    Typeface tfRegular;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWeightBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Constants.changeTheme(this);

        Constants.initDialog(this);
        Constants.showDialog();

        chart = binding.tempChart;

        binding.tempChart.setTouchEnabled(true);
        binding.tempChart.setPinchZoom(true);

        tfRegular = Typeface.SANS_SERIF;

        binding.back.setOnClickListener(v -> onBackPressed());

        chart(200f, 0f);

        Constants.databaseReference().child(Constants.Users).child(Constants.auth().getCurrentUser().getUid())
                .get().addOnSuccessListener(snapshot -> {
                    if (snapshot.exists()) {
                        UserModel userModel = snapshot.getValue(UserModel.class);
                        Stash.put(Constants.Users, userModel.getName());
                        Constants.dismissDialog();



                    }
                }).addOnFailureListener(e -> {
                    Constants.dismissDialog();
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                });

    }

    private void chart(float max, float min) {
        chart.getDescription().setEnabled(false);

        // enable touch gestures
        chart.setTouchEnabled(true);

        chart.setDragDecelerationFrictionCoef(0.9f);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setVisibleXRangeMaximum(6);
        chart.setDrawGridBackground(false);
        chart.setHighlightPerDragEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(true);

        // set an alternative background color
        chart.setBackgroundColor(getResources().getColor(R.color.dark));

        chart.animateX(1500);

        // get the legend (only possible after setting data)
        Legend l = chart.getLegend();

        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
//        l.setTypeface(tfLight);
        l.setTextSize(11f);
        l.setTextColor(Color.WHITE);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
//        l.setYOffset(11f);

        XAxis xAxis = chart.getXAxis();
//        xAxis.setTypeface(tfLight);
        xAxis.setTextSize(11f);
        xAxis.setTextColor(getResources().getColor(R.color.grey));
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis leftAxis = chart.getAxisLeft();
//        leftAxis.setTypeface(tfLight);
        leftAxis.setTextColor(getResources().getColor(R.color.yellow));
        leftAxis.setAxisMaximum(max);
        leftAxis.setAxisMinimum(min);
        leftAxis.setDrawGridLines(false);
        leftAxis.setGranularityEnabled(true);


        setData();
    }

    private void setData() {
        ArrayList<Entry> values1 = new ArrayList<>();
        Constants.databaseReference().child(Constants.WEIGHT).child(Constants.auth().getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        float weight = 0f;
                        int ind=0;
                        for (DataSnapshot child : snapshot.getChildren()) {
                            String name = child.getKey();
                            weight = snapshot.child(name).child(Constants.WEIGHT).getValue(Float.class);
                            values1.add(new Entry(ind, weight));
                            ind=ind+1;

                        }

                        LineDataSet set1;

                        if (chart.getData() != null && chart.getData().getDataSetCount() > 0) {
                            set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);
                            set1.setValues(values1);

                            chart.getData().notifyDataChanged();
                            chart.notifyDataSetChanged();
                            chart.invalidate();
                        } else {
                            // create a dataset and give it a type
                            set1 = new LineDataSet(values1, "Weight Dataset");
                            set1.setAxisDependency(YAxis.AxisDependency.LEFT);
                            set1.setColor(getResources().getColor(R.color.yellow));
                            set1.setCircleColor(getResources().getColor(R.color.white));
                            set1.setLineWidth(2f);
                            set1.setCircleRadius(3f);
                            set1.setFillAlpha(65);
                            set1.setHighlightEnabled(true);
                            set1.setFillColor(ColorTemplate.getHoloBlue());
                            set1.setHighLightColor(Color.rgb(244, 117, 117));
                            set1.setDrawCircleHole(false);
                            set1.setDrawFilled(true);
                            // create a data object with the data sets
                            LineData data = new LineData(set1);
                            data.setValueTextColor(getResources().getColor(R.color.grey));
                            data.setValueTextSize(9f);

                            // set data
                            chart.setData(data);

                            List<ILineDataSet> sets = chart.getData()
                                    .getDataSets();

                            for (ILineDataSet iSet : sets) {

                                LineDataSet set = (LineDataSet) iSet;
                                set.setMode(set.getMode() == LineDataSet.Mode.CUBIC_BEZIER
                                        ? LineDataSet.Mode.LINEAR
                                        :  LineDataSet.Mode.CUBIC_BEZIER);
                            }
                            chart.invalidate();

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

}