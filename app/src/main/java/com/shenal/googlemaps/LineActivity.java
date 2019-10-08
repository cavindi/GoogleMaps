package com.shenal.googlemaps;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class LineActivity extends AppCompatActivity {
    LineChart lineChart;
    float fDate;
    float fTemp;
    String data = "";
    String date = "";
    String temperature = "";

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

        try {
            //CHANGE THE SCRIPT URL
            URL url = new URL("https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=1Vir4bfLShFvs9JSgUUR4EM6BFlIaiut7jskngEnJy4E&sheet=Sheet1"); //get from which website
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(); //check connection
            InputStream inputStream = httpURLConnection.getInputStream(); //this is input
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((inputStream)));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }
            data = data.substring(10);//delete first 11 character = sheet1
            JSONArray JA = new JSONArray(data);
            for (int i = 0; i < JA.length(); i++) {
                JSONObject JO = (JSONObject) JA.get(i);
                date = JO.get("Date").toString();
                temperature = JO.get("Temperature").toString();
                fDate = Float.valueOf(date);
                fTemp = Float.valueOf(temperature);

                for (int j = 0; j < dataVals.size(); j++) {
                    dataVals.add(new Entry(fDate, fTemp));
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return dataVals;
    }
}

