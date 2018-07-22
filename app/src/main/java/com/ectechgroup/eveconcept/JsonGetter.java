package com.ectechgroup.eveconcept;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * AsyncTask should not be used for network activity, because it's tied to the activity, but not the activity lifecycle. Rotating the device with this task is running will cause an exception and crash your app. Use an IntentService that drops data in the sqlite database instead.
 */
public class JsonGetter extends AsyncTask<String, Void, JSONObject> {

    public static String USER_AGENT = "android EveConcept/JsonGetter";

    private Exception exception;

    protected JSONObject doInBackground(String... urls) {
        String url = urls[0];
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                throw new Exception(url + ". GET Response: " + responseCode);
            }
            Log.d("MainActivity", "Sending 'GET' request to URL : " + url);
            Log.d("MainActivity", "Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return new JSONObject(response.toString());
        } catch (Exception e) {
            this.exception = e;
        }
        return null;
    }

    protected void onPostExecute(JSONObject json) {
        // TODO: check this.exception
        // TODO: do something with the feed
    }

    public Exception getException() {
        return exception;
    }
}
