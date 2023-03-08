package com.example.ecm2425.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.ecm2425.app_utils.DataHandler;
import com.example.ecm2425.app_utils.Log;
import com.example.ecm2425.app_utils.MenuFunc;
import com.example.ecm2425.R;
import com.example.ecm2425.app_utils.RecyclerViewInterface;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {

    /* fields */
    private final String API_KEY = "5m1cJmo4lCYar60eRMhm1A==yQMvjeHswVaXi55a";
    TextView mTitle;
    TextView mBody;
    Button mCreateLogButton;
    URL quoteURL;
    TextView quote;
    Thread networkThread;
    static boolean resumed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataHandler dataHandler = new DataHandler();

        if(Log.allLogs.size() == 0 ){
            createPersistentLogs();
        }

        /* boolean to monitor activity state for api data pull scheduling.
         * when this activity is paused, resumed is set to false and the networking thread
         * stops. when onCreate , resumed is set to true and the networking thread resumes */
        resumed = true;

        /* quote generation setup */
        quote = findViewById(R.id.the_quote);
        quoteURL = buildUrl();

        /* networking - anonymous offloaded thread to pull api data every 8 seconds */
        networkThread = new Thread(() -> {
            try {
                while (resumed){
                    JSONObject jsonObj = getJSONResponseFromAPI(quoteURL, API_KEY);
                    if(jsonObj!=null){
                        quote.setText(jsonObj.getString("quote"));
                        Thread.sleep(8000);
                    } else {
                        throw new NullPointerException();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        networkThread.start();




        /* wire widgets */
        mTitle = findViewById(R.id.main_logTitle);
        mBody = findViewById(R.id.main_logBody);
        mCreateLogButton = findViewById(R.id.main_createLog_btn);

        /* when createLogButton is pressed, create Log object with input data,
         * store the log in shared preferences and add the Log to the
         * global log array. The form data is also cleared in preperation
         * for a new log to be entered. */
        mCreateLogButton.setOnClickListener( v -> {
            Log newLog = new Log();
            String title = mTitle.getText().toString();
            String body = mBody.getText().toString();
            newLog.setTitle(title);
            newLog.setBody(body);
            Log.allLogs.add(newLog);
            clearFormData();
            dataHandler.addToSharedPref(newLog, dataHandler.getSharedPref(MainActivity.this));  // add log to persistent storage

            Intent intent = new Intent(MainActivity.this, RecordedLogs.class);
            startActivity(intent);
        });
    }

    /* sets resumed to false in order to stop networking thread when activity
     * is not in foreground */
    @Override
    public void onPause(){
        super.onPause();
        resumed = false;
    }


    @Override
    public void onItemClick(int position) {

    }

    /* inflates the menu widgets */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    /* deals with menu functionality */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        DataHandler dataHandler = new DataHandler();
        SharedPreferences sharedPreferences = dataHandler.getSharedPref(MainActivity.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(MenuFunc.menuFunctionality(editor,item, MainActivity.this)){
            return true;
        }
        return false;
    }

    /* clear form views */
    void clearFormData(){
        mBody.setText("");
        mTitle.setText("");
    }


    /* build url */
    public static URL buildUrl() {
        URL url = null;
        try {
            url = new URL("https://api.api-ninjas.com/v1/quotes?category=happiness");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    /* returns a json object from the api pull */
    public static JSONObject getJSONResponseFromAPI(URL url, String api_key) throws IOException {
        try{
            //make connection
            HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
            urlc.setRequestMethod("GET");
            // set the content type
            urlc.setRequestProperty("Content-Type", "application/json");
            urlc.setRequestProperty("X-Api-Key", api_key);
            android.util.Log.d("http", "connected: " + url);
            urlc.setAllowUserInteraction(false);
            urlc.connect();

            StringBuffer response = new StringBuffer();
            //get result
            BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
            String l;
            while ((l=br.readLine())!=null) {
                response.append(l);
            }
            /* extracting quote from response */
            JSONObject jsonObj = new JSONObject(response.toString().substring(response.indexOf("{"), response.lastIndexOf("}")+1));
            br.close();
            urlc.disconnect();
            return jsonObj;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    /* pulls all logs from Shared Preference and populates the
     * global Log array */
    public void createPersistentLogs(){
        DataHandler dataHandler = new DataHandler();
        SharedPreferences pref = dataHandler.getSharedPref(MainActivity.this);

        Map<String, ?> allData = pref.getAll();
        for( Map.Entry<String, ?> entry: allData.entrySet() ){
            String formattedString = (String)entry.getValue();
            Log newLog = new Log();
            newLog.setTitle(formattedString.substring(formattedString.indexOf('{')+1,formattedString.indexOf('}')));
            newLog.setBody(formattedString.substring(formattedString.indexOf('[')+1,formattedString.indexOf(']')));
            Log.allLogs.add(newLog);
        }
        // ensures logs array list is in correct order, based on each Logs index field
        Log.sortedLogs();
        for(Log l: Log.reverseSortedLogs ){
            android.util.Log.d("logs", l.getTitle() + " : " + l.getBody() );
        }
    }


}

