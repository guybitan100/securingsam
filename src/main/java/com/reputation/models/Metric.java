package com.reputation.models;

import org.springframework.http.HttpStatus;

public class Metric {
    String threadName;
    long startTime = 0;
    long endTime = 0;
    long durationMs;
    Object response;
    HttpStatus httpStatus;
    String reportStatus;

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

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("ThreadName: " + threadName);
        sb.append(" DurationSec: " + getDurationMs() / 1000);
        sb.append(" ReportStatus: " + getReportStatus());
        sb.append(" HttpStatus: " + getHttpStatus());
        return sb.toString();
    }
}
