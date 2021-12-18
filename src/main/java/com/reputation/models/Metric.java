package com.reputation.models;

import com.reputation.pojo.Response;

public class Metric {
    String threadName;
    long startTime = 0;
    long endTime = 0;
    long durationMs;
    Response response;
    int statusCode;

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public long getDurationMs() {
        return (durationMs);
    }

    public void setDurationMs(long durationMs) {
        this.durationMs = durationMs;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setResponse(Response response) {
        this.response = response;
    }


    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("ThreadName: " + getThreadName());
        sb.append(" DurationSec: " + getDurationMs() / 1000);
        sb.append(" HttpStatusCode: " + getStatusCode());
        return sb.toString();
    }
}
