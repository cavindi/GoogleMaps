package com.shenal.googlemaps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMap = findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToMap();
            }
        });

        Button btnTemperatureLineChart = findViewById(R.id.btnTemperatureLineChart);
        btnTemperatureLineChart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToTemperatureLineChart();
            }
        });

        Button btnDOLineChart = findViewById(R.id.btnDoLineChart);
        btnDOLineChart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToDOLineChart();
            }
        });

        Button btnHumidityLineChart = findViewById(R.id.btnHumidityLineChart);
        btnHumidityLineChart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToHumidityLineChart();
            }
        });
    }


    public void goToMap() {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void goToTemperatureLineChart() {
        Intent intent = new Intent(this, TemperatureLineChart.class);
        startActivity(intent);
    }

    public void goToDOLineChart() {
        Intent intent = new Intent(this, DOLineChartActivity.class);
        startActivity(intent);
    }

    public void goToHumidityLineChart() {
        Intent intent = new Intent(this, HumidityLineChartActivity.class);
        startActivity(intent);
    }

}