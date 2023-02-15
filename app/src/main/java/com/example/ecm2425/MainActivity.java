package com.example.ecm2425;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {

    private final String TAG = "TAG";

    TextView mTitle;
    TextView mBody;
    Button mCreateLogButton;
    // imageView, uploadButton

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            //createTestLogModels(20);
            startActivity(intent);
        });

        Button mGoToNewsBtn = findViewById(R.id.main_goToNews_btn);
        mGoToNewsBtn.setOnClickListener( v -> {
            Intent intent = new Intent(MainActivity.this, NewsOpener.class);
            startActivity(intent);
        });
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
}