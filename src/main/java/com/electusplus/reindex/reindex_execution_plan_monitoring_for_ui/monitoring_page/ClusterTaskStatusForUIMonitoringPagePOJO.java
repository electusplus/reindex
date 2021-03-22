package com.electusplus.reindex.reindex_execution_plan_monitoring_for_ui.monitoring_page;

import com.electusplus.reindex.reindex_execution_plan_monitoring.ClusterTaskStatusPOJO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ClusterTaskStatusForUIMonitoringPagePOJO {
    @JsonProperty("self_generated_task_id")
    private String selfGeneratedTaskId;
    @JsonProperty("description")
    private String description;
    private boolean isInActiveProcess = false;
    private boolean isFailed = false;
    private boolean isSucceeded = false;
    private boolean isDone = false;

    public ClusterTaskStatusForUIMonitoringPagePOJO(ClusterTaskStatusPOJO taskStatus) {
        this.selfGeneratedTaskId = taskStatus.getSelfGeneratedTaskId();
        this.description = taskStatus.getDescription();
        this.isInActiveProcess = taskStatus.isInActiveProcess();
        this.isFailed = taskStatus.isFailed();
        this.isSucceeded = taskStatus.isSucceeded();
        this.isDone = taskStatus.isDone();
    }

    public ClusterTaskStatusForUIMonitoringPagePOJO() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSelfGeneratedTaskId() {
        return selfGeneratedTaskId;
    }

    public void setSelfGeneratedTaskId(String selfGeneratedTaskId) {
        this.selfGeneratedTaskId = selfGeneratedTaskId;
    }

    @JsonProperty("is_done")
    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @JsonProperty("is_in_active_process")
    public boolean isInActiveProcess() {
        return isInActiveProcess;
    }

    public void setInActiveProcess(boolean inActiveProcess) {
        isInActiveProcess = inActiveProcess;
    }

    @JsonProperty("is_failed")
    public boolean isFailed() {
        return isFailed;
    }


    public void setFailed(boolean failed) {
        isFailed = failed;
    }

    @JsonProperty("is_succeeded")
    public boolean isSucceeded() {
        return isSucceeded;
    }

    public void setSucceeded(boolean succeeded) {
        isSucceeded = succeeded;
    }
}
