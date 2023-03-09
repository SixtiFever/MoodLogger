package com.example.ecm2425.app_utils;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;
import java.util.stream.Collectors;

public class Log implements Serializable {

    /* private fields */
    private UUID logID;

    private String logTitle;

    private String logBody;

    private LocalDate date;

    private String stringDate;

    private int index;

    /* statics */
    static int indexCounter = 1;
    public static ArrayList<Log> allLogs = new ArrayList<>();
    public static ArrayList<Log> reverseSortedLogs = new ArrayList<>();

    /* constructor */
    public Log(){
        checkResetIndex();
        date = LocalDate.now();
        this.stringDate = formatLocalDate(date);
        this.logID = UUID.randomUUID();
        setIndex(indexCounter++);
    }

    /* formats the LocalDate into UK formatted String date for UI display */
    private String formatLocalDate(LocalDate date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dtf.format(date);
    }

    /* resets index counter is higher than 200 */
    public void checkResetIndex(){
        if(getIndex() > 200) setIndex(1);
    }

    /* sorts the logs objects via the index field, and then reverses to ensure
    * index 1 is at the tail */
    public static void sortedLogs() {
        ArrayList<Log> reverseSorted = (ArrayList<Log>) allLogs.stream().sorted(Comparator.comparingInt(Log::getIndex)).collect(Collectors.toList());
        Collections.reverse(reverseSorted);
        reverseSortedLogs = reverseSorted;
    }

    /* accessors */
    public String getLogTitle() {
        return logTitle;
    }

    public void setLogTitle(String logTitle) {
        this.logTitle = logTitle;
    }

    public String getLogBody() {
        return logBody;
    }

    public void setLogBody(String logBody) {
        this.logBody = logBody;
    }

    public String getStringDate() {
        return stringDate;
    }

    public UUID getLogID() {
        return logID;
    }

    public int getIndex() { return this.index; }

    public void setIndex(int index){ this.index = index; }
}