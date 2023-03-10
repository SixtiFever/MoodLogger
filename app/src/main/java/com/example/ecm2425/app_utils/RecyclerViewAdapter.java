package com.example.ecm2425.app_utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ecm2425.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private final RecyclerViewInterface mRecyclerViewInterface;

    Context context; // sets the current context of the adapter
    ArrayList<Log> mLogArrayList;  // static array which will be the input data

    public RecyclerViewAdapter(Context context, ArrayList<Log> logArrayList, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.mLogArrayList = logArrayList;
        this.mRecyclerViewInterface = recyclerViewInterface;
    }

    /* inflates the view holder */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);  // create an inflater object within the recycler view context
        View view = inflater.inflate(R.layout.vh_log, parent, false);  // inflate the view holder layout within the recycler view (parent)
        return new RecyclerViewAdapter.MyViewHolder(view, mRecyclerViewInterface);  // returns the instance of the view holder to be added to the recycler view
    }

    /* binds data to the views in the view holder */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log newLog = mLogArrayList.get(position);
        holder.title.setText(newLog.getLogTitle());
    }

    /* returns the count of the collection */
    @Override
    public int getItemCount() {
        return mLogArrayList.size();
    }

    /* class to create view holder objects */
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView date;

        /* Creates the view holder, and provides an implementation of onClick, which then
        * calls the anonymous implementation mRecyclerView.onItemClick() as per RecordedLogs.class */
        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface mRecyclerViewInterface) {
            super(itemView);
            title = itemView.findViewById(R.id.recordedLogs_title);
            date = itemView.findViewById(R.id.recordedLogs_date);
            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    if ( mRecyclerViewInterface != null ) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mRecyclerViewInterface.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
