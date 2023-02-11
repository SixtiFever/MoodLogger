package com.example.ecm2425;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ViewLogFragment extends Fragment {

    private TextView mTitle;
    private TextView mBody;
    private TextView mDate;

    @Override
    public void onCreate(Bundle savedInstanceStace){
        super.onCreate(savedInstanceStace);
    }

    /* set up and return the fragments UI to the container */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.view_log_fragment, container, false);

        mTitle = view.findViewById(R.id.viewLog_title_textView);
        mBody = view.findViewById(R.id.viewLog_body_textView);
        mDate = view.findViewById(R.id.viewLog_date_textView);

        mTitle.setText("Title from fragment");
        mBody.setText("Body from fragment");
        mDate.setText("03/03/2023");

        return view;
    }
}
