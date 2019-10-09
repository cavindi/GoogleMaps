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
    public static String result = null;

    @Override
    protected String doInBackground(Void... voids) {
        String stringUrl = "https://script.googleusercontent.com/macros/echo?user_content_key=379PgeliL2VVP4hWBeg1rhBgPj3zn8QKNDILaoWampSQ77aPFIosWgXpNzR6184ARZgiQIAKF-dabDCzY_cBKfmdArsZyp1POJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMWojr9NvTBuBLhyHCd5hHa1GhPSVukpSQTydEwAEXFXgt_wltjJcH3XHUaaPC1fv5o9XyvOto09QuWI89K6KjOu0SP2F-BdwUdEm9OpiitxukVyy6QwQNhn-YfRm6BvCFE71MHL4U9NNWSqFVhNwcYroR1j2UCw3q5y7FLqOV0Tk27B8Rh4QJTQ&lib=MnrE7b2I2PjfH799VodkCPiQjIVyBAxva";

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
        LineActivity.test.equals(result);
    }
}
