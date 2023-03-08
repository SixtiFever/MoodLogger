package com.example.ecm2425;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

        /* get intent */
        Intent receivedIntent = getIntent();
        mTitle = receivedIntent.getStringExtra("logTitle");
        mBody = receivedIntent.getStringExtra("logBody");
        mDate = receivedIntent.getStringExtra("logDate");

        // activate fragment
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
        SharedPreferences sharedPreferences = getSharedPref(ViewLog.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(MenuFunc.menuFunctionality(editor,item,ViewLog.this)){
            return true;
        }
        return false;
    }

    public SharedPreferences getSharedPref(Context context){
        return context.getSharedPreferences(Integer.toString(R.string.shared_pref_key), Context.MODE_PRIVATE);
    }
}