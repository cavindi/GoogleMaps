package com.shenal.googlemaps;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PageAdapter adapter = new PageAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
/*

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button button = findViewById(R.id.btnMap);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                navigateToMap();
            }
        });


        Button tempbutton = findViewById(R.id.temperature);
        tempbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showtempdata();
            }
        });

        Button oxygenbutton = findViewById(R.id.oxygen);
        oxygenbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showoxydata();
            }
        });

        Button mpLineChartButton = findViewById(R.id.lineChart);
        mpLineChartButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showMpLineChart();
            }
        });

    }

    private void showMpLineChart() {
        Intent intent = new Intent(this, FirebaseActivity.class);
        startActivity(intent);
    }

    public void showtempdata() {
        Intent intent = new Intent(this, Temp_Data.class);
        startActivity(intent);
    }

    public void showoxydata() {
        Intent intent = new Intent(this, Oxy_Data.class);
        startActivity(intent);
    }

    public void navigateToMap() {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
*/

}

