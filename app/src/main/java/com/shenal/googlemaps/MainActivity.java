package com.shenal.googlemaps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton btnMap = findViewById(R.id.mapButton);
        btnMap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToMap();
            }
        });

        ImageButton btnLineChart = findViewById(R.id.lineChartButton);
        btnLineChart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToLineChart();
            }
        });
    }


    public void goToMap() {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void goToLineChart() {
        Intent intent = new Intent(this, FirebaseActivity.class);
        startActivity(intent);
    }

}