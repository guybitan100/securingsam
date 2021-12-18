
package com.reputation.models.clickhouse.res;


public class TimeBucketing {

    private String bucketResolution;
    private Long bucketInterval;
    private Long bucketShiftSeconds;
    private Long bucketShiftMiliSeconds;
    private Long intervalInSeconds;

    public String getBucketResolution() {
        return bucketResolution;
    }

    public void setBucketResolution(String bucketResolution) {
        this.bucketResolution = bucketResolution;
    }

    public Long getBucketInterval() {
        return bucketInterval;
    }

    public void setBucketInterval(Long bucketInterval) {
        this.bucketInterval = bucketInterval;
    }

    public Long getBucketShiftSeconds() {
        return bucketShiftSeconds;
    }

    public void setBucketShiftSeconds(Long bucketShiftSeconds) {
        this.bucketShiftSeconds = bucketShiftSeconds;
    }

    public Long getBucketShiftMiliSeconds() {
        return bucketShiftMiliSeconds;
    }

    public void setBucketShiftMiliSeconds(Long bucketShiftMiliSeconds) {
        this.bucketShiftMiliSeconds = bucketShiftMiliSeconds;
    }

    public Long getIntervalInSeconds() {
        return intervalInSeconds;
    }

    public void setIntervalInSeconds(Long intervalInSeconds) {
        this.intervalInSeconds = intervalInSeconds;
    }

}
