
package com.reputation.models.clickhouse.req;

import java.util.List;

public class TotalQuery {

    private Boolean compare;
    private Boolean activeFiveMinSessions;
    private List<Aggregation__> aggregations = null;
    private Boolean comparePercentage;

    public Boolean getCompare() {
        return compare;
    }

    public void setCompare(Boolean compare) {
        this.compare = compare;
    }

    public Boolean getActiveFiveMinSessions() {
        return activeFiveMinSessions;
    }

    public void setActiveFiveMinSessions(Boolean activeFiveMinSessions) {
        this.activeFiveMinSessions = activeFiveMinSessions;
    }

    public List<Aggregation__> getAggregations() {
        return aggregations;
    }

    public void setAggregations(List<Aggregation__> aggregations) {
        this.aggregations = aggregations;
    }

    public Boolean getComparePercentage() {
        return comparePercentage;
    }

    public void setComparePercentage(Boolean comparePercentage) {
        this.comparePercentage = comparePercentage;
    }

}
