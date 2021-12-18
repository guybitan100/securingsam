
package com.reputation.models.clickhouse.req;

import java.util.List;

public class TopRelation {

    private String relation;
    private List<SubRelation> subRelations = null;

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public List<SubRelation> getSubRelations() {
        return subRelations;
    }

    public void setSubRelations(List<SubRelation> subRelations) {
        this.subRelations = subRelations;
    }

}
