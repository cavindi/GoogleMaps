package com.shenal.googlemaps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.TextView;

public class Humidity_Data extends AppCompatActivity {
    public static TextView oxydata;
    public static SwipeRefreshLayout pullToRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_humidity__data);
        oxydata = findViewById(R.id.data);

        pullToRefresh = findViewById(R.id.refresh);
        Oxy_fetch process = new Oxy_fetch();
        process.execute();
        pullToRefresh.setRefreshing(false);

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
