package com.example.ecm2425.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.ecm2425.R;

public class ViewLogFragment extends Fragment {

    private TextView mTitle;

    private TextView mBody;

    private TextView mDate;

    String stringTitle;
    String stringBody;
    String stringDate;

    /* constructor */
    ViewLogFragment(String title, String body, String date){
        stringTitle = title;
        stringBody = body;
        stringDate = date;
    }

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

        mTitle.setText(stringTitle);
        mBody.setText(stringBody);
        mDate.setText(stringDate);

        return view;
    }
}
