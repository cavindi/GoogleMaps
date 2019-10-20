package com.shenal.googlemaps;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class Temp_Data extends AppCompatActivity {
    public static TextView tempdata;
    public static SwipeRefreshLayout pullToRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp__data);
        tempdata = findViewById(R.id.data);
        Temp_fetch process = new Temp_fetch();
        process.execute();

        pullToRefresh = findViewById(R.id.refresh);
        pullToRefresh.setRefreshing(false);

        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Temp_fetch process = new Temp_fetch();
                process.execute();
                pullToRefresh.setRefreshing(false);
            }
        });
    }

}
