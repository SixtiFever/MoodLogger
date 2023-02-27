package com.example.ecm2425;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
        SharedPreferences sharedPreferences = getSharedPref(NewsOpener.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(MenuFunc.menuFunctionality(editor,item,NewsOpener.this)){
            return true;
        }
        return false;
    }

    public SharedPreferences getSharedPref(Context context){
        return context.getSharedPreferences(Integer.toString(R.string.shared_pref_key), Context.MODE_PRIVATE);
    }
}