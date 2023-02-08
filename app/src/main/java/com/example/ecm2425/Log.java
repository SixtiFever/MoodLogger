package com.example.ecm2425;

import android.media.Image;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

public class Log {

    private UUID mID;
    private String mTitle;
    private String mBody;
    private LocalDate date;
    private String stringDate;
    private Image img;

    static ArrayList<Log> allLogs = new ArrayList<>();

    Log(){
        date = LocalDate.now();
        this.stringDate = formatLocalDate(date);
        this.mID = UUID.randomUUID();
    }

    /* formats the LocalDate into UK formatted String date for UI display */
    private String formatLocalDate(LocalDate date){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dtf.format(date);
    }

    /* test: prints all log values (except image) */
    void printLogDetails(){
        System.out.println("Title: " + getTitle());
        System.out.println("Body: " + getBody());
        System.out.println("StringDate: " + getStringDate());
    }

    /* ACCESSORS */

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getBody() {
        return mBody;
    }

    public void setBody(String body) {
        mBody = body;
    }

    public String getStringDate() {
        return stringDate;
    }

    public void setStringDate(String stringDate) {
        this.stringDate = stringDate;
    }

    public UUID getID() {
        return mID;
    }
}
