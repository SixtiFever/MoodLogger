package com.example.ecm2425;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class ViewLog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_log);

        // activate fragment
        FragmentManager fm = getSupportFragmentManager();

        fm.beginTransaction().add(R.id.viewLog_fragment_container, new ViewLogFragment()).commit();
    }
}