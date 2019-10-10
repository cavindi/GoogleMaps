package com.shenal.googlemaps;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class LineActivity extends AppCompatActivity {
    LineChart lineChart;
    public static ListView listTemperature;
    public static ListView listDate;
    String date;
    String temperature;
    float fDate;
    float fTemp;
    public static String test;

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
        ArrayList<Entry> dataVals = new ArrayList<>();
        fDate = Float.valueOf(date);
        fTemp = Float.valueOf(temperature);
        for (int j = 0; j < dataVals.size(); j++) {
            dataVals.add(new Entry(fDate, fTemp));
        }
        return dataVals;
    }
}

