package com.electusplus.reindex.reindex_execution_plan_builder.reindex_plan;

import java.util.HashMap;
import java.util.Map;

public class ReindexPlanParamsPOJO {
    private final String projectId;
    private final String projectName;

    private Map<String, Map<String, Object>> jobsParams = new HashMap<>();

    public ReindexPlanParamsPOJO(String projectId, String projectName) {
        this.projectId = projectId;
        this.projectName = projectName;
    }

}
