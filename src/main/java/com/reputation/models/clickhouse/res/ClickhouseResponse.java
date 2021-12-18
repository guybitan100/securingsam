
package com.reputation.models.clickhouse.res;

import java.util.List;

public class ClickhouseResponse {

    private String status;
    private String dataSource;
    private DebugData debugData;
    private List<Report> reports = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public DebugData getDebugData() {
        return debugData;
    }

    public void setDebugData(DebugData debugData) {
        this.debugData = debugData;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

}
