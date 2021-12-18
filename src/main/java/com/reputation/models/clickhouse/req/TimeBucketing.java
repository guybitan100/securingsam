
package com.reputation.models.clickhouse.req;


public class TimeBucketing {

    private String bucketResolution;
    private Integer bucketInterval;

    public String getBucketResolution() {
        return bucketResolution;
    }

    public void setBucketResolution(String bucketResolution) {
        this.bucketResolution = bucketResolution;
    }

    public Integer getBucketInterval() {
        return bucketInterval;
    }

    public void setBucketInterval(Integer bucketInterval) {
        this.bucketInterval = bucketInterval;
    }

}
