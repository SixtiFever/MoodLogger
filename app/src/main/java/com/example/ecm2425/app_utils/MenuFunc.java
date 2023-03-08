package com.example.ecm2425.app_utils;

import com.example.ecm2425.activities.*;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.MenuItem;

import com.example.ecm2425.R;

public class MenuFunc {

    MenuFunc(){

    }

    /* performs action based on the option item selected. Takes in the
    * Shared Preferences editor, the menu, the current context as arguments */
    public static boolean menuFunctionality(SharedPreferences.Editor editor, MenuItem item, Context context){

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
