package com.shenal.googlemaps;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class LineActivity extends AppCompatActivity {
    LineChart lineChart;
    float fDate;
    float fTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);
        lineChart = (LineChart) findViewById(R.id.line_chart);

        //calling dataValues1() method and assigning the data to the LineDataSet
        LineDataSet lineDataSet1 = new LineDataSet(dataValues1(), "Data From Sensor 1");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet1);
        LineData lineData = new LineData(dataSets);
        lineChart.setData(lineData);
        lineChart.invalidate();
    }

    private ArrayList<Entry> dataValues1() {
        //getting the values from FetchActivity
        Intent intent = getIntent();
        String sDate = intent.getStringExtra("date");
        String sTemp = intent.getStringExtra("temperature");

        ArrayList<Entry> dataVals = new ArrayList<>();

        //casting the string variables to float, so that they can be passed into Entry in line 49
        fDate = Float.parseFloat(sDate);
        fTemp = Float.parseFloat(sTemp);
        for (int i = 0; i < dataVals.size(); i++) {
            //Entry will only accept float values
            //(new Entry(x, y))
            dataVals.add(new Entry(fDate, fTemp));
            dataVals.add(new Entry(1, 10));
            dataVals.add(new Entry(3, 50));
            dataVals.add(new Entry(4, 24));
            dataVals.add(new Entry(5, 16));
        }
        return dataVals;
    }
}

