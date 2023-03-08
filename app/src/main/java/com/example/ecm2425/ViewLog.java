package com.example.ecm2425;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class ViewLog extends AppCompatActivity {

    String mTitle;
    String mBody;
    String mDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_log);

        /* get received intent and set fields to the intent data values */
        Intent receivedIntent = getIntent();
        mTitle = receivedIntent.getStringExtra("logTitle");
        mBody = receivedIntent.getStringExtra("logBody");
        mDate = receivedIntent.getStringExtra("logDate");

        /* activate, populate and commit the fragment */
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.viewLog_fragment_container, new ViewLogFragment(mTitle, mBody, mDate)).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SharedPreferences sharedPreferences = DataHandler.getSharedPref(ViewLog.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(MenuFunc.menuFunctionality(editor,item,ViewLog.this)){
            return true;
        }
        return false;
    }
}