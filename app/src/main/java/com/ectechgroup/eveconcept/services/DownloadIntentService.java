package com.ectechgroup.eveconcept.services;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadIntentService extends IntentService {

    private static final String TAG = DownloadIntentService.class.getCanonicalName();

    public static final String PENDING_RESULT_EXTRA = "pending_result";
    public static final String URL_EXTRA = "url";
    public static final String JSON_RESULT_EXTRA = "json";

    public static final int RESULT_CODE = 0;
    public static final int INVALID_URL_CODE = 1;
    public static final int ERROR_CODE = 2;


    private Exception exception;

    public DownloadIntentService() {
        super(TAG);
    }

    public static String USER_AGENT = "android evepoc/DownloadIntentService";

    @Override
    protected void onHandleIntent(Intent intent) {
            PendingIntent reply = intent.getParcelableExtra(PENDING_RESULT_EXTRA);

            String url = intent.getStringExtra(URL_EXTRA);

            try {
                try {
                    URL obj = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                    // optional default is GET
                    con.setRequestMethod("GET");

                    //add request header
                    con.setRequestProperty("User-Agent", USER_AGENT);

                    int responseCode = con.getResponseCode();
                    if(responseCode == 200) {


                        Log.d(TAG, "Sending 'GET' request to URL : " + url);
                        Log.d(TAG, "Response Code : " + responseCode);

                        BufferedReader in = new BufferedReader(
                                new InputStreamReader(con.getInputStream()));
                        String inputLine;
                        StringBuffer response = new StringBuffer();

                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        in.close();
                        Intent result = new Intent();
                        result.putExtra(JSON_RESULT_EXTRA, response.toString());
                        result.putExtra(URL_EXTRA, url);
                        reply.send(this, responseCode, result);
                    } else {
                        Intent result = new Intent();
                        result.putExtra(URL_EXTRA, url);
                        reply.send(this, responseCode, result);
                    }

                } catch (MalformedURLException exc) {
                    reply.send(INVALID_URL_CODE);
                } catch (Exception exc) {
                    // could do better by treating the different sax/xml exceptions individually
                    reply.send(ERROR_CODE);
                }
            } catch (PendingIntent.CanceledException exc) {
                Log.i(TAG, "reply cancelled", exc);
            }
        }
}
