package com.ectechgroup.eveconcept;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    public static String USER_AGENT = "EveConcept/android";

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

        // call checkLocale manually anyway
        try {
            EveLocale localData = checkLocale();
            Snackbar.make(findViewById(R.id.hellotext), "Accessed locale service: " + localData.getEstablishmentName(), Snackbar.LENGTH_LONG).setAction("Action", null).show();

        } catch (Exception e) {
            Snackbar.make(findViewById(R.id.hellotext), "Ex: " + e.getClass().getName(), Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
    }

    public EveLocale checkLocale() throws Exception {

        // if this returns an object, how is the screen updated?
        JsonGetter getter = new JsonGetter();
        JSONObject jsonObj = getter.execute("https://3i7pml7fpi.execute-api.us-west-1.amazonaws.com/prod/eve/test").get();
        if(getter.getException() != null) {
            throw getter.getException();
        }
        EveLocale localData = new EveLocale();
        localData.setEstablishmentId(jsonObj.getString("establishmentId"));
        localData.setEstablishmentName(jsonObj.getString("establishmentId"));

        return localData;

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
