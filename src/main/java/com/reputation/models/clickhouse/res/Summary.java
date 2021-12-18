
package com.reputation.models.clickhouse.res;

import java.util.List;

public class Summary {

    private List<Aggregation__> aggregations = null;
    private Debug_ debug;
    private String status;

    public List<Aggregation__> getAggregations() {
        return aggregations;
    }

    public void setAggregations(List<Aggregation__> aggregations) {
        this.aggregations = aggregations;
    }

    public Debug_ getDebug() {
        return debug;
    }

    public void setDebug(Debug_ debug) {
        this.debug = debug;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
