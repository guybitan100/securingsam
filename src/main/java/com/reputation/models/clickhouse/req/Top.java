
package com.reputation.models.clickhouse.req;


import java.util.List;

public class Top {

    private String limit;
    private List<Field> fields = null;
    private Boolean createOtherDim;
    private Boolean useFirstDimCount;

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public Boolean getCreateOtherDim() {
        return createOtherDim;
    }

    public void setCreateOtherDim(Boolean createOtherDim) {
        this.createOtherDim = createOtherDim;
    }

    public Boolean getUseFirstDimCount() {
        return useFirstDimCount;
    }

    public void setUseFirstDimCount(Boolean useFirstDimCount) {
        this.useFirstDimCount = useFirstDimCount;
    }

}
