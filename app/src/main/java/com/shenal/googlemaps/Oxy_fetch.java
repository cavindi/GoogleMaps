package com.shenal.googlemaps;

import android.os.AsyncTask;

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

public class Oxy_fetch extends AsyncTask<Void, Void, Void> {
    String data = "";
    String singleParsed = "";
    String dataParsed = "";
    String dataSent = "";
    String date = "";
    String temperature = "";

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://script.googleusercontent.com/macros/echo?user_content_key=379PgeliL2VVP4hWBeg1rhBgPj3zn8QKNDILaoWampSQ77aPFIosWgXpNzR6184ARZgiQIAKF-dabDCzY_cBKfmdArsZyp1POJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMWojr9NvTBuBLhyHCd5hHa1GhPSVukpSQTydEwAEXFXgt_wltjJcH3XHUaaPC1fv5o9XyvOto09QuWI89K6KjOu0SP2F-BdwUdEm9OpiitxukVyy6QwQNhn-YfRm6BvCFE71MHL4U9NNWSqFVhNwcYroR1j2UCw3q5y7FLqOV0Tk27B8Rh4QJTQ&lib=MnrE7b2I2PjfH799VodkCPiQjIVyBAxva"); //get from which website
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
                singleParsed = "Day: " + JO.get("Day") + "/" + JO.get("Month") + "/" + JO.get("Year") + "\n" +
                        "Temperature: " + JO.get("Temperature") + "\n";
                dataParsed = dataParsed + singleParsed + "\n";
                temperature = (String) JO.get("Temperature");
                date = (String) JO.get("Month");
                //date = JO.get("Day") + "/" + JO.get("Month") + "/" + JO.get("Year");
                dataSent = dataSent + temperature + "\n";
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Oxy_Data.oxydata.setText(this.dataSent);
        LineActivity.date.equals(this.date);
        LineActivity.temperature.equals(this.temperature);
    }
}
