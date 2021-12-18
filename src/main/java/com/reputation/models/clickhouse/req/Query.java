
package com.reputation.models.clickhouse.req;

import java.util.List;

public class Query {

    private String type;
    private String context;
    private List<CompositeAggregation> compositeAggregations = null;
    private List<Filter> filters = null;
    private Top top;
    private String id;
    private List<Dimension> dimensions = null;
    private List<Aggregation_> aggregations = null;
    private TotalQuery totalQuery;

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

    public List<CompositeAggregation> getCompositeAggregations() {
        return compositeAggregations;
    }

    public void setCompositeAggregations(List<CompositeAggregation> compositeAggregations) {
        this.compositeAggregations = compositeAggregations;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    public Top getTop() {
        return top;
    }

    public void setTop(Top top) {
        this.top = top;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Dimension> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<Dimension> dimensions) {
        this.dimensions = dimensions;
    }

    public List<Aggregation_> getAggregations() {
        return aggregations;
    }

    public void setAggregations(List<Aggregation_> aggregations) {
        this.aggregations = aggregations;
    }

    public TotalQuery getTotalQuery() {
        return totalQuery;
    }

    public void setTotalQuery(TotalQuery totalQuery) {
        this.totalQuery = totalQuery;
    }

}
