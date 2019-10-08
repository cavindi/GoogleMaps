package com.shenal.googlemaps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
    String fetchDate;
    String fetchTemperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);

        Button btnGetData = findViewById(R.id.btnGetData);
        btnGetData.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getData();
            }
        });

        setDataToLineChart();

    }

    private ArrayList<Entry> dataValues1() {
        ArrayList<Entry> dataVals = new ArrayList<>();

        //casting the string variables to float, so that they can be passed into Entry in line 49
        fDate = Float.parseFloat(fetchDate);
        fTemp = Float.parseFloat(fetchTemperature);
        for (int i = 0; i < dataVals.size(); i++) {
            //Entry will only accept float values
            //(new Entry(x, y))
            dataVals.add(new Entry(fDate, fTemp));
        }
        return dataVals;
    }

    public void getData(){
        //getting the values from FetchActivity
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        fetchTemperature = extras.getString("temperature");
        fetchDate = extras.getString("date");
    }

    public void setDataToLineChart(){
        lineChart = (LineChart) findViewById(R.id.line_chart);
        //calling dataValues1() method and assigning the data to the LineDataSet
        LineDataSet lineDataSet1 = new LineDataSet(dataValues1(), "Data From Sensor 1");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet1);
        LineData lineData = new LineData(dataSets);
        lineChart.setData(lineData);
        lineChart.invalidate();
    }
}

