package com.example.ecm2425;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Collections;

public class RecordedLogs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorded_logs);

        Collections.reverse(Log.allLogs);
        RecyclerView rv = findViewById(R.id.recordedLogs_recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, Log.allLogs);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));





    }
}