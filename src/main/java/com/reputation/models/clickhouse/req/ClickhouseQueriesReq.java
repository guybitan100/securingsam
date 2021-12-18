
package com.reputation.models.clickhouse.req;

import java.util.List;

public class ClickhouseQueriesReq {

    private String name;
    private List<Integer> appIds = null;
    private TimeRange timeRange;
    private List<Query> queries = null;
    private List<Object> topLevelFilters = null;
    private String perspective;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getAppIds() {
        return appIds;
    }

    public void setAppIds(List<Integer> appIds) {
        this.appIds = appIds;
    }

    public TimeRange getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(TimeRange timeRange) {
        this.timeRange = timeRange;
    }

    public List<Query> getQueries() {
        return queries;
    }

    public void setQueries(List<Query> queries) {
        this.queries = queries;
    }

    public List<Object> getTopLevelFilters() {
        return topLevelFilters;
    }

    public void setTopLevelFilters(List<Object> topLevelFilters) {
        this.topLevelFilters = topLevelFilters;
    }

    public String getPerspective() {
        return perspective;
    }

    public void setPerspective(String perspective) {
        this.perspective = perspective;
    }

}
