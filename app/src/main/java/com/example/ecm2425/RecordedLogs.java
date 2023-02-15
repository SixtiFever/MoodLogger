package com.example.ecm2425;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Collections;

public class RecordedLogs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorded_logs);

        Collections.reverse(Log.allLogs);
        RecyclerView rv = findViewById(R.id.recordedLogs_recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, Log.allLogs, new RecyclerViewInterface() {
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

        Intent intent;
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.miNews:
                intent = new Intent(RecordedLogs.this, NewsOpener.class);
                startActivity(intent);
                return true;
            case R.id.miCreateLog:
                intent = new Intent(RecordedLogs.this, MainActivity.class);
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