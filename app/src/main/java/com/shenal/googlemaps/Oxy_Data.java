package com.shenal.googlemaps;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
    String date = "";
    String temperature = "";

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

        String[] xAxisData = {};
        //String[] yAxisData = {};

        String[] axisData = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept",
                "Oct", "Nov", "Dec"};

        int[] yAxisData = {};

        List axisValues = new ArrayList();
        List yAxisValues = new ArrayList();
        Line line = new Line(yAxisValues);

        for (int i = 0; i < axisData.length; i++) {
            axisValues.add(i, new AxisValue(i).setLabel(axisData[i]));
        }

        for (int i = 0; i < yAxisData.length; i++) {
            yAxisValues.add(new PointValue(i, yAxisData[i]));
        }

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