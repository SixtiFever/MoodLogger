package com.example.ecm2425;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Log implements Serializable {

    /* private fields */
    private UUID mID;
    private String mTitle;
    private String mBody;
    private LocalDate date;
    private String stringDate;
    private int index;

    /* statics */
    static int indexCounter = 1;
    static ArrayList<Log> allLogs = new ArrayList<>();
    static ArrayList<Log> reverseSortedLogs = new ArrayList<>();

    /* constructor */
    Log(){
        checkResetIndex();
        date = LocalDate.now();
        this.stringDate = formatLocalDate(date);
        this.mID = UUID.randomUUID();
        setIndex(indexCounter++);
    }

    /* formats the LocalDate into UK formatted String date for UI display */
    private String formatLocalDate(LocalDate date){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dtf.format(date);
    }

    /* resets index counter is higher than 200 */
    public void checkResetIndex(){
        if(getIndex() > 200) setIndex(1);
    }

    static void sortedLogs(){
        ArrayList<Log> reverseSorted = (ArrayList<Log>) allLogs.stream().sorted(Comparator.comparingInt(Log::getIndex)).collect(Collectors.toList());
        Collections.reverse(reverseSorted);
        reverseSortedLogs = reverseSorted;
    }



    /* accessors */

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

    public int getIndex() { return this.index; }

    public void setIndex(int index){ this.index = index; }
}