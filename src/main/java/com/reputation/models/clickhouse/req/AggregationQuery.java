
package com.reputation.models.clickhouse.req;

import java.util.List;

public class AggregationQuery {

    private List<Object> filters = null;
    private List<Object> dimensions = null;
    private List<Aggregation> aggregations = null;

    public List<Object> getFilters() {
        return filters;
    }

    public void setFilters(List<Object> filters) {
        this.filters = filters;
    }

    public List<Object> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<Object> dimensions) {
        this.dimensions = dimensions;
    }

    public List<Aggregation> getAggregations() {
        return aggregations;
    }

    public void setAggregations(List<Aggregation> aggregations) {
        this.aggregations = aggregations;
    }

}
