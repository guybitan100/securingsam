
package com.reputation.models.clickhouse.req;

public class Dimension {

    private String fieldName;
    private TimeBucketing timeBucketing;
    private String aliasName;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public TimeBucketing getTimeBucketing() {
        return timeBucketing;
    }

    public void setTimeBucketing(TimeBucketing timeBucketing) {
        this.timeBucketing = timeBucketing;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

}
