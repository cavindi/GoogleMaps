package com.shenal.googlemaps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

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

public class FetchActivity extends AppCompatActivity {
    String data = "";
    String singleParsed = "";
    String dataParsed = "";
    String dataSent = "";
    String date = "";
    String temperature = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            for (int i = 0; i < JA.length(); i++) {
                JSONObject JO = (JSONObject) JA.get(i);
                //montharray[i] = (int) JO.get("Month");
                //tempValueArray[i] = (int) JO.get("Temperature");

                singleParsed = "Day: " + JO.get("Day") + "/" + JO.get("Month") + "/" + JO.get("Year") + "\n" +
                        "Temperature: " + JO.get("Temperature") + "\n";
                dataParsed = dataParsed + singleParsed + "\n";
                temperature = JO.getString("Temperature");
                date = JO.getString("Month");
                //date = JO.get("Day") + "/" + JO.get("Month") + "/" + JO.get("Year");
                dataSent = dataSent + temperature + "\n";

                sendData();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void sendData(){
        Intent intent = new Intent(this, LineActivity.class);
        intent.putExtra("temperature", temperature);
        intent.putExtra("date", date);
        startActivity(intent);
    }
}
