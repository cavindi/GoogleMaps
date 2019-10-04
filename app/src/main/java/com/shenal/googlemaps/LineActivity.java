package com.shenal.googlemaps;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class LineActivity extends AppCompatActivity {
    float date = 0;
    float temperature = 0;
    String dateString;
    String tempString;
    LineChart lineChart;
    public static TextView xValue;
    public static TextView yValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);

        lineChart = (LineChart) findViewById(R.id.line_chart);
        xValue = (TextView) findViewById(R.id.x);
        yValue = (TextView) findViewById(R.id.y);
        tempString = xValue.getText().toString();
        dateString = yValue.getText().toString();

        LineDataSet lineDataSet1 = new LineDataSet(dataValues1(),"Data Set 1");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet1);
        LineData lineData = new LineData(dataSets);
        lineChart.setData(lineData);
        lineChart.invalidate();
    }

    private ArrayList<Entry> dataValues1() {
        ArrayList<Entry> dataVals = new ArrayList<>();
        temperature = Float.parseFloat(tempString);
        date = Float.parseFloat(tempString);
        dataVals.add(new Entry(temperature, date));

        return dataVals;
    }
}

