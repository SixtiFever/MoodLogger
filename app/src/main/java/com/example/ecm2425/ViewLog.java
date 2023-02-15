package com.example.ecm2425;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

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
}