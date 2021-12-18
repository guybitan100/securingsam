
package com.reputation.models.clickhouse.res;

import java.util.List;

public class Metadata {

    private String perspective;
    private Timeframe_ timeframe;
    private TimeBucketing timeBucketing;
    private List<Aggregation_> aggregations = null;
    private List<DimDetail> dimDetails = null;
    private String type;
    private String context;

    public String getPerspective() {
        return perspective;
    }

    public void setPerspective(String perspective) {
        this.perspective = perspective;
    }

    public Timeframe_ getTimeframe() {
        return timeframe;
    }

    public void setTimeframe(Timeframe_ timeframe) {
        this.timeframe = timeframe;
    }

    public TimeBucketing getTimeBucketing() {
        return timeBucketing;
    }

    public void setTimeBucketing(TimeBucketing timeBucketing) {
        this.timeBucketing = timeBucketing;
    }

    public List<Aggregation_> getAggregations() {
        return aggregations;
    }

    public void setAggregations(List<Aggregation_> aggregations) {
        this.aggregations = aggregations;
    }

    public List<DimDetail> getDimDetails() {
        return dimDetails;
    }

    public void setDimDetails(List<DimDetail> dimDetails) {
        this.dimDetails = dimDetails;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

}
