
package com.reputation.models.clickhouse.res;


public class Debug_ {

    private Long startTimestamp;
    private Long createdTimestamp;
    private Long endTimestamp;
    private Long durationMs;
    private Long waitForExecutionInMs;

    public Long getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Long startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public Long getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Long createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public Long getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(Long endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public Long getDurationMs() {
        return durationMs;
    }

    public void setDurationMs(Long durationMs) {
        this.durationMs = durationMs;
    }

    public Long getWaitForExecutionInMs() {
        return waitForExecutionInMs;
    }

    public void setWaitForExecutionInMs(Long waitForExecutionInMs) {
        this.waitForExecutionInMs = waitForExecutionInMs;
    }

}
