package com.shenal.googlemaps;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpGetRequest extends AsyncTask<Void, Void, String> {
    String temperature;
    String date;

    @Override
    protected String doInBackground(Void... voids) {
        String stringUrl = "https://script.google.com/macros/s/AKfycbys4tKmBSIB7Eqf-je0PYmHGTgvfnaCPbLmvCo_Pkt9LC84Yg/exec";
        String result = null;
        String inputLine;

        try {
            //Create a URL object holding our url
            URL myUrl = new URL(stringUrl);
            //Create a connection
            HttpURLConnection connection =(HttpURLConnection)
                    myUrl.openConnection();

            //Connect to our url
            connection.connect();

            //Create a new InputStreamReader
            InputStreamReader streamReader = new
                    InputStreamReader(connection.getInputStream());
            //Create a new buffered reader and String Builder
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";
            //Check if the line we are reading is not null
            while((inputLine = reader.readLine()) != null){
                line = String.valueOf(stringBuilder.append(inputLine));
            }
            JSONArray jsonArray = new JSONArray(line);
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                date = jsonObject.getString("Day");
                temperature = jsonObject.getString("Temperature");

            }
            //Close our InputStream and Buffered reader
            reader.close();
            streamReader.close();
            //Set our result equal to our stringBuilder
            //result = stringBuilder.toString();
            result = date + temperature;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}
