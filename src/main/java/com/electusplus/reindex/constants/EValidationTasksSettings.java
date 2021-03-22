package com.electusplus.reindex.constants;

public enum EValidationTasksSettings {
    IS_INDEX_SELECTED("Checking is at least one of the indices selected", EValidationStatuses.ERROR, "There is no selected indices"),
    IS_DESTINATION_SELECTED("Checking is at least one destination selected", EValidationStatuses.ERROR, "There is no selected destination"),
    IS_INDEX_EXISTS("Checking is index '%s' exists", EValidationStatuses.WARNING, "The index already exists"),
    IS_ALIAS_EXISTS("Checking is alias '%s' exists", EValidationStatuses.ERROR, "The alias doesn't exists"),
    IS_WHITELIST_EXISTS_IN_THE_REMOTE_CLUSTER("Check is the host: '%s' exists in the remote whitelist", EValidationStatuses.WARNING, "The whitelist doesn't exists or incorrect, please check it"),
    IS_DATE_FIELD_EXISTS("Checking is date field: %s", EValidationStatuses.ERROR, "The date field doesn't exists"),
    IS_CLUSTER_RESPOND("Checking if the cluster is %s", EValidationStatuses.ERROR, "The cluster doesn't respond"),
    IS_PIPELINE_EXISTS("Checking is ingest pipeline '%s' exists", EValidationStatuses.ERROR, "The ingest pipeline doesn't exists");


    private final String taskExplanation;
    private final String onFailureNotes;
    private final EValidationStatuses onFailureStatus;

    EValidationTasksSettings(String taskExplanation, EValidationStatuses onFailureStatus, String onFailureNotes) {
        this.taskExplanation = taskExplanation;
        this.onFailureNotes = onFailureNotes;
        this.onFailureStatus = onFailureStatus;
    }

    public String getTaskExplanation() {
        return this.taskExplanation;
    }

    public String getOnFailureNotes() {
        return onFailureNotes;
    }

    public EValidationStatuses getOnFailureStatus() {
        return onFailureStatus;
    }
}
