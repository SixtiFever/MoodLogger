package com.example.ecm2425.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.example.ecm2425.app_utils.DataHandler;
import com.example.ecm2425.app_utils.MenuFunc;
import com.example.ecm2425.R;

public class ViewLog extends AppCompatActivity {

    String logTitle;

    String logBody;

    String logDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_log);

        /* get received intent and set fields to the intent data values */
        Intent receivedIntent = getIntent();
        logTitle = receivedIntent.getStringExtra("logTitle");
        logBody = receivedIntent.getStringExtra("logBody");
        logDate = receivedIntent.getStringExtra("logDate");

        /* activate, populate and commit the fragment */
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.viewLog_fragment_container, new ViewLogFragment(logTitle, logBody, logDate)).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        DataHandler dataHandler = new DataHandler();
        SharedPreferences sharedPreferences = dataHandler.getSharedPref(ViewLog.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (MenuFunc.menuFunctionality(editor,item,ViewLog.this)) {
            return true;
        }
        return false;
    }
}
