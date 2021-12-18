
package com.reputation.models.clickhouse.res;


public class Aggregation__ {

    private String field;
    private String function;
    private Integer total;
    private Integer previousTotal;
    private Integer totalLastFiveMin;
    private Float diffFromPrevPercentage;
    private Float diffFromPrev;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPreviousTotal() {
        return previousTotal;
    }

    public void setPreviousTotal(Integer previousTotal) {
        this.previousTotal = previousTotal;
    }

    public Integer getTotalLastFiveMin() {
        return totalLastFiveMin;
    }

    public void setTotalLastFiveMin(Integer totalLastFiveMin) {
        this.totalLastFiveMin = totalLastFiveMin;
    }

    public Float getDiffFromPrevPercentage() {
        return diffFromPrevPercentage;
    }

    public void setDiffFromPrevPercentage(Float diffFromPrevPercentage) {
        this.diffFromPrevPercentage = diffFromPrevPercentage;
    }

    public Float getDiffFromPrev() {
        return diffFromPrev;
    }

    public void setDiffFromPrev(Float diffFromPrev) {
        this.diffFromPrev = diffFromPrev;
    }

}
