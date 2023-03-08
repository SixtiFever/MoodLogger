package com.example.ecm2425;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class RecordedLogs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorded_logs);
        RecyclerView rv = findViewById(R.id.recordedLogs_recyclerView);
        Log.sortedLogs();  // sort logs

        /* anonymous implementation of my custom made RecyclerViewInterface */
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, Log.reverseSortedLogs, new RecyclerViewInterface() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(RecordedLogs.this, ViewLog.class);
                intent.putExtra("logTitle", Log.reverseSortedLogs.get(position).getTitle());
                intent.putExtra("logBody", Log.reverseSortedLogs.get(position).getBody());
                intent.putExtra("logDate", Log.reverseSortedLogs.get(position).getStringDate());
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
        SharedPreferences sharedPreferences = DataHandler.getSharedPref(RecordedLogs.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(MenuFunc.menuFunctionality(editor,item,RecordedLogs.this)){
            return true;
        }
        return false;
    }



}