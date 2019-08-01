package com.example.eventtimerstart;

public final class Rider {
    private int riderNumber;
    private long startTime;
    private long finishTime;

    private Rider(int riderNumber, long startTime, long finishTime) {
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
