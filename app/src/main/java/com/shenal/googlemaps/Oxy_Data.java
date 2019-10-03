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
    public static SwipeRefreshLayout pullToRefresh;
    String data = "";
    String singleParsed = "";
    String dataParsed = "";
    String dataSent = "";
    String date = "";
    int temperature = 0;
    int montharray[] = {};
    int oxygenvaluearray[] = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxy__data);
        oxydata = findViewById(R.id.data);
        pullToRefresh = findViewById(R.id.refresh);
        Oxy_fetch process = new Oxy_fetch();
        process.execute();
        pullToRefresh.setRefreshing(false);

        try {
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

            List yData = new ArrayList();
            int y[] = new int[]{};
            String x[] = new String[]{};
            for(int i = 0; i < JA.length(); i++){
                JSONObject JO = (JSONObject) JA.get(i);
                int temp = JO.getInt("Temperature");
                String month = JO.getString("Month");
                y[i] = temp;
                x[i] = month;
            }

            /*for (int i = 0; i < JA.length(); i++) {
                JSONObject JO = (JSONObject) JA.get(i);
                singleParsed = "Day: " + JO.get("Day") + "/" + JO.get("Month") + "/" + JO.get("Year") + "\n" +
                        "Temperature: " + JO.get("Temperature") + "\n";
                dataParsed = dataParsed + singleParsed + "\n";
                temperature = (int) JO.get("Temperature");
                date = JO.get("Day") + "/" + JO.get("Month") + "/" + JO.get("Year");
                dataSent = dataSent + temperature + "\n";
                yData.add(temperature);
            }*/

            LineChartView lineChartView = findViewById(R.id.oxy_chart);

/*            int yAxisData[] = {};
            String[] axisData = {};*/
            List axisValues = new ArrayList();
            List yAxisValues = new ArrayList();
            Line line1 = new Line(yAxisValues);

            for (int i = 0; i < x.length; i++) {
                axisValues.add(i, new AxisValue(i).setLabel(x[i]));
            }
            for (int i = 0; i < y.length; i++) {
                yAxisValues.add(new PointValue(i, y[i]));
            }

            List lines = new ArrayList();
            lines.add(line1);
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

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}