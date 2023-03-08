package com.example.ecm2425;

import android.content.Context;
import android.content.SharedPreferences;

public class DataHandler {

    DataHandler(){
    }

    static SharedPreferences getSharedPref(Context context){
        return context.getSharedPreferences(Integer.toString(R.string.shared_pref_key), Context.MODE_PRIVATE);
    }

    /* add to shared preference */
    void addToSharedPref(Log log, SharedPreferences sharedPreferences){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        try {
            editor.putString(log.getID().toString(), formattedLog(log));
            editor.apply();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /* formats log contents for shared preference insertion and retrieval via substring extraction */
    String formattedLog(Log log){
        return "{"+log.getTitle()+"}["+log.getBody()+"]";
    }

}
