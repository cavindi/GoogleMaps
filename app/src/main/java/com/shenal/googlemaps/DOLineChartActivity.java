package com.shenal.googlemaps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DOLineChartActivity extends AppCompatActivity {

    LineChart lineChart;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;//database
    LineDataSet lineDataSet = new LineDataSet(null, null);
    ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
    LineData lineData;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doline_chart);

        lineChart = findViewById(R.id.line_chart);
        spinner = findViewById(R.id.spinner);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("masterSheet");

        retrieveData();
    }

    private void retrieveData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Entry> dataVals = new ArrayList<Entry>();
                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot myDataSnapshot : dataSnapshot.getChildren()) {
                        DODataPoint doDataPoint = myDataSnapshot.getValue(DODataPoint.class);
                        dataVals.add(new Entry(doDataPoint.getDay(), doDataPoint.getDissolvedOxygen()));
                    }
                    showChart(dataVals);
                } else {
                    lineChart.invalidate();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showChart(ArrayList<Entry> dataVals) {
        lineDataSet.setValues(dataVals);
        lineDataSet.setLabel("Data Set 1");
        iLineDataSets.clear();
        iLineDataSets.add(lineDataSet);
        lineData = new LineData(iLineDataSets);
        lineChart.clear();
        lineChart.setData(lineData);
        lineChart.invalidate();

    }
}
