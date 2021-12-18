
package com.reputation.models.clickhouse.res;

import java.util.List;

public class Record {

    private String dimension;
    private Long value;
    private List<Aggregation> aggregations = null;

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public List<Aggregation> getAggregations() {
        return aggregations;
    }

    public void setAggregations(List<Aggregation> aggregations) {
        this.aggregations = aggregations;
    }

}
