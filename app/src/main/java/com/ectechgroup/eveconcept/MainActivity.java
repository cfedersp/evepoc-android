package com.ectechgroup.eveconcept;

import android.app.PendingIntent;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ectechgroup.eveconcept.services.DownloadIntentService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getCanonicalName();

    public static String PROD_URL = "https://3i7pml7fpi.execute-api.us-west-1.amazonaws.com/prod/eve/test";
    public static int GET_LOCAL_ACTIVITY_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                .setAction("Action", null).show();
        //    }
        //});

        // Register Wifi Listener

        // give the DownloadService a reference back to this Activity
        PendingIntent pendingResult = createPendingResult(
                GET_LOCAL_ACTIVITY_CODE, new Intent(), 0);
        Intent intent = new Intent(getApplicationContext(), DownloadIntentService.class);
        intent.putExtra(DownloadIntentService.URL_EXTRA, PROD_URL);
        intent.putExtra(DownloadIntentService.PENDING_RESULT_EXTRA, pendingResult);
        startService(intent);


        /*
        try {
            Intent intent = new Intent(getApplicationContext(), DownloadIntentService.class);
            intent.putExtra(DownloadIntentService.URL_EXTRA, PROD_URL);
            PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(), GET_LOCAL_ACTIVITY_CODE, intent, PendingIntent.FLAG_ONE_SHOT);
            pendingIntent.send();
        } catch (PendingIntent.CanceledException exc) {
            Log.i(TAG, "getDoc cancelled", exc);
        }
        */
    }


    @Override
    public void onRestart() {
        super.onRestart();
        // give the DownloadService a reference back to this Activity
        PendingIntent pendingResult = createPendingResult(
                GET_LOCAL_ACTIVITY_CODE, new Intent(), 0);
        Intent intent = new Intent(getApplicationContext(), DownloadIntentService.class);
        intent.putExtra(DownloadIntentService.URL_EXTRA, PROD_URL);
        intent.putExtra(DownloadIntentService.PENDING_RESULT_EXTRA, pendingResult);
        startService(intent);
    }


    public EveLocale loadData(String json) throws JSONException {

        // if this returns an object, how is the screen updated?
        JSONObject jsonObj = new JSONObject(json);

        EveLocale localData = new EveLocale();
        localData.setEstablishmentId(jsonObj.getString("establishmentId"));
        localData.setEstablishmentName(jsonObj.getString("establishmentName"));
        localData.setNetworkId(jsonObj.getString("gatewayId"));
        //localData.setServiceEndpoint(jsonObj.getString("serviceEndpoint"));
        return localData;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GET_LOCAL_ACTIVITY_CODE) {
            try {
                if (resultCode == 200) {
                    EveLocale localData = loadData(data.getStringExtra("json"));
                    EveDatabaseHelper helper = EveDatabaseHelper.getInstance(this);
                    helper.insertVisit(localData);
                    Snackbar.make(findViewById(R.id.hellotext), "Accessed locale service: " + localData.getEstablishmentName(), Snackbar.LENGTH_LONG).setAction("Action", null).show();

                } else if(resultCode == 404) {
                    Snackbar.make(findViewById(R.id.hellotext), "You are not on a recognized network", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else {
                    Snackbar.make(findViewById(R.id.hellotext), "Result Code: " + resultCode, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            } catch (JSONException e) {
                Snackbar.make(findViewById(R.id.hellotext), "Ex: " + e.getMessage(), Snackbar.LENGTH_LONG).setAction("Action", null).show();

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
