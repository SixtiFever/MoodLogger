package com.example.ecm2425;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Collections;
import java.util.Scanner;

public class MainActivity<HttpRequest, HttpResponse> extends AppCompatActivity implements RecyclerViewInterface {

    private static final String TAG = "TAG";
    private final String API_KEY = "5m1cJmo4lCYar60eRMhm1A==yQMvjeHswVaXi55a";
    private String requestURL = "https://api.api-ninjas.com/v1/quotes?category=happiness";

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
        resumed = true;

        quote = (TextView) findViewById(R.id.main_quote);

        quoteURL = buildUrl();

        networkThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (resumed){
                        JSONObject jsonObj = getJSONResponseFromAPI(quoteURL, API_KEY);
                        quote.setText(jsonObj.getString("quote"));
                        try {Thread.sleep(2000);} catch (Exception e){ e.printStackTrace();}
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        networkThread.start();




        /* wire widgets */
        mTitle = findViewById(R.id.main_logTitle);
        mBody = findViewById(R.id.main_logBody);
        mCreateLogButton = findViewById(R.id.main_createLog_btn);

        /* when createLogButton is pressed, create Log object with input data */
        mCreateLogButton.setOnClickListener( v -> {
            /** add -> if null functionality **/
            Log newLog = new Log();
            String title = mTitle.getText().toString();
            String body = mBody.getText().toString();
            newLog.setTitle(title);
            newLog.setBody(body);
            Log.allLogs.add(newLog);
            Intent intent = new Intent(MainActivity.this, RecordedLogs.class);
            intent.putExtra("sent_log", newLog); // use serializable version of putExtra
            startActivity(intent);
        });

        Button mGoToNewsBtn = findViewById(R.id.main_goToNews_btn);
        mGoToNewsBtn.setOnClickListener( v -> {
            Intent intent = new Intent(MainActivity.this, NewsOpener.class);
            startActivity(intent);
        });
    }

    @Override
    public void onPause(){
        super.onPause();
        resumed = false;
    }


    /* creates test log models to populate recycler view. called on click */
    void createTestLogModels(int size){
        Log log;
        for( int i = 0; i < size; i++ ){
            log = new Log();
            log.setTitle("Today was a good day!");
            Log.allLogs.add(log);
        }
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.miRecordedLogs:
                intent = new Intent(MainActivity.this, RecordedLogs.class);
                startActivity(intent);
                return true;
            case R.id.miNews:
                intent = new Intent(MainActivity.this, NewsOpener.class);
                startActivity(intent);
                return true;
            case R.id.miSettings:
                Toast.makeText(this, "Clicked on settings", Toast.LENGTH_LONG).show();
                return true;
            case R.id.miClose:
                Toast.makeText(this, "Clicked on close", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

    /* returns a json object */
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
            //get result
            BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
            String l;
            while ((l=br.readLine())!=null) {
                response.append(l);
            }
            JSONObject jsonObj = new JSONObject(response.toString().substring(response.indexOf("{"), response.lastIndexOf("}")+1));
            System.out.println(jsonObj);
            br.close();
            urlc.disconnect();
            return jsonObj;
        } catch (Exception e){
            System.out.println("Error occured");
            System.out.println(e.toString());
            return null;
        }
    }

}
