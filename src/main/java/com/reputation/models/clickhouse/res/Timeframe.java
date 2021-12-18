
package com.reputation.models.clickhouse.res;


public class Timeframe {

    private String timeZone;
    private Long from;
    private Long till;

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getTill() {
        return till;
    }

    public void setTill(Long till) {
        this.till = till;
    }

}
