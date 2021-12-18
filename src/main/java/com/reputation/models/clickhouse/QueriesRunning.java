package com.reputation.models.clickhouse;

import java.util.List;

public class QueriesRunning {

    private List<Object> queries = null;

    public List<Object> getQueries() {
        return queries;
    }

    public void setQueries(List<Object> queries) {
        this.queries = queries;
    }

}