package com.shenal.googlemaps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageButton mapBtn, lineChartBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mapBtn = findViewById(R.id.mapButton);
        lineChartBtn = findViewById(R.id.lineChartButton);

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMap();

            }
        });

        lineChartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLineChart();

            }
        });
    }

    public void goToMap(){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void goToLineChart(){
        Intent intent = new Intent(this, FirebaseActivity.class);
        startActivity(intent);
    }

}