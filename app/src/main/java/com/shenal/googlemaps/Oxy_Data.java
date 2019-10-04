package com.shenal.googlemaps;

import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

public class Oxy_Data extends AppCompatActivity {
    public static TextView oxydata;
    public static TextView xValue;
    public static TextView yValue;
    public static SwipeRefreshLayout pullToRefresh;
    String data = "";
    String singleParsed = "";
    String dataParsed = "";
    String dataSent = "";
    String date = "";
    String temperature = "";
    int montharray[] = {};
    int oxygenvaluearray[] = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxy__data);
        oxydata = findViewById(R.id.data);
        xValue = (TextView) findViewById(R.id.x);
        yValue = (TextView) findViewById(R.id.y);
        date = xValue.getText().toString();
        temperature = yValue.getText().toString();

        pullToRefresh = findViewById(R.id.refresh);
        Oxy_fetch process = new Oxy_fetch();
        process.execute();
        pullToRefresh.setRefreshing(false);

        LineChartView lineChartView = findViewById(R.id.oxy_chart);

        int yAxisData[] = {};
        String[] axisData = {};
        String[] yaxisData = {};
        List axisValues = new ArrayList();
        List yAxisValues = new ArrayList();
        Line line = new Line(yAxisValues);

        for (int i = 0; i < axisData.length; i++) {
            axisValues.add(date);
        }

        for (int i = 0; i < yaxisData.length; i++) {
            yAxisValues.add(temperature);
        }
/*
        for (int i = 0; i < yAxisData.length; i++) {
            yAxisValues.add(new PointValue(i, yAxisData[i]));
        }*/

        List lines = new ArrayList();
        lines.add(line);
        LineChartData datas = new LineChartData();
        datas.setLines(lines);
        lineChartView.setLineChartData(datas);

        Axis axis = new Axis();
        axis.setValues(axisValues);
        datas.setAxisXBottom(axis);

        Axis yAxis = new Axis();
        datas.setAxisYLeft(yAxis);

        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Oxy_fetch process = new Oxy_fetch();
                process.execute();
                pullToRefresh.setRefreshing(false);
            }
        });

    }
}