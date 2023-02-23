package com.example.ecm2425;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class NewsOpener extends AppCompatActivity {

    private final String newsURL = "https://www.positive.news/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_opener);

        Button mNewsOpenerBtn = findViewById(R.id.newsOpener_openNews_btn);

        mNewsOpenerBtn.setOnClickListener( v -> {
            Intent newsIntent = new Intent(Intent.ACTION_VIEW);
            newsIntent.setData(Uri.parse(newsURL));
            startActivity(newsIntent);
        });
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
                intent = new Intent(NewsOpener.this, RecordedLogs.class);
                startActivity(intent);
                return true;
            case R.id.miNews:
                intent = new Intent(NewsOpener.this, NewsOpener.class);
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