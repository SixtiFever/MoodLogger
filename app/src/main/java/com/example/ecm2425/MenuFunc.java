package com.example.ecm2425;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.MenuItem;
import android.widget.Toast;

public class MenuFunc {


    MenuFunc(){

    }

    static boolean menuFunctionality(SharedPreferences.Editor editor, MenuItem item, Context context){

        Intent intent;

        switch (item.getItemId()) {

            case R.id.miCreateLog:
                intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
                return true;
            case R.id.miRecordedLogs:
                intent = new Intent(context, RecordedLogs.class);
                context.startActivity(intent);
                return true;
            case R.id.miNews:
                intent = new Intent(context, NewsOpener.class);
                context.startActivity(intent);
                return true;
            case R.id.miClearLogs:
                editor.clear();
                editor.apply();
                return true;
            default:
                return true;
        }
    }

}
