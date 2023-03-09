package com.example.ecm2425.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import com.example.ecm2425.app_utils.DataHandler;
import com.example.ecm2425.app_utils.MenuFunc;
import com.example.ecm2425.R;

public class NewsOpener extends AppCompatActivity {

    /* string of the website url */
    private final String newsURL = "https://www.positive.news/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_opener);

        Button mNewsOpenerBtn = findViewById(R.id.newsOpener_openNews_btn);

        /* sends an implicit intent on click, opens a browser of the users selection and directs
         * to the url */
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
        DataHandler dataHandler = new DataHandler();
        SharedPreferences sharedPreferences = dataHandler.getSharedPref(NewsOpener.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (MenuFunc.menuFunctionality(editor,item, NewsOpener.this)) {
            return true;
        }
        return false;
    }
}
