package com.example.ecm2425;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RecordedLogs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorded_logs);
        Log.sortedLogs();
        RecyclerView rv = findViewById(R.id.recordedLogs_recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, Log.reverseSortedLogs, new RecyclerViewInterface() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(RecordedLogs.this, ViewLog.class);
                intent.putExtra("logTitle", Log.allLogs.get(position).getTitle());
                intent.putExtra("logBody", Log.allLogs.get(position).getBody());
                intent.putExtra("logDate", Log.allLogs.get(position).getStringDate());
                startActivity(intent);
            }
        });

        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SharedPreferences sharedPreferences = getSharedPref(RecordedLogs.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(MenuFunc.menuFunctionality(editor,item,RecordedLogs.this)){
            return true;
        }
        return false;
    }

    public SharedPreferences getSharedPref(Context context){
        return context.getSharedPreferences(Integer.toString(R.string.shared_pref_key), Context.MODE_PRIVATE);
    }


}