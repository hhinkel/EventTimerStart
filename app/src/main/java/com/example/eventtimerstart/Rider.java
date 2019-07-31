package com.example.eventtimerstart;

public class Rider {
    int riderNumber;
    long startTime;
    long finishTime;

    public Rider(int riderNumber, long startTime, long finishTime) {
        this.riderNumber = riderNumber;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    public int getRiderNumber (){
        return riderNumber;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getFinishTime() {
        return finishTime;
    }
}
