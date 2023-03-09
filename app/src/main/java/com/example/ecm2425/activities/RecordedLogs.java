package com.example.ecm2425.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.example.ecm2425.app_utils.DataHandler;
import com.example.ecm2425.app_utils.Log;
import com.example.ecm2425.app_utils.MenuFunc;
import com.example.ecm2425.R;
import com.example.ecm2425.app_utils.RecyclerViewAdapter;
import com.example.ecm2425.app_utils.RecyclerViewInterface;

public class RecordedLogs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorded_logs);
        RecyclerView rv = findViewById(R.id.recordedLogs_recyclerView);
        Log.sortedLogs();  // sort logs

        /* implementation of Recycler View Adapter that takes the context, log data
         * and provides an anonymous implementation of my RecyclerViewInterface to provide onItemClick */
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, Log.reverseSortedLogs, new RecyclerViewInterface() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(RecordedLogs.this, ViewLog.class);
                intent.putExtra("logTitle", Log.reverseSortedLogs.get(position).getLogTitle());
                intent.putExtra("logBody", Log.reverseSortedLogs.get(position).getLogBody());
                intent.putExtra("logDate", Log.reverseSortedLogs.get(position).getStringDate());
                startActivity(intent);
            }
        });

        /* bind the adapter to the Recycler View */
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
        DataHandler dataHandler = new DataHandler();
        SharedPreferences sharedPreferences = dataHandler.getSharedPref(RecordedLogs.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (MenuFunc.menuFunctionality(editor,item, RecordedLogs.this)) {
            return true;
        }
        return false;
    }
}
