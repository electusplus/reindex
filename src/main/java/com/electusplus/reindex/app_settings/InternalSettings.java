package com.electusplus.reindex.app_settings;

import com.electusplus.reindex.constants.EAppSettings;

public class InternalSettings {
    private final String projectsFolder;
    private final String clientFolder;
    private final long tasksRefreshInterval;

    public InternalSettings(String homeFolder) {
        this.projectsFolder = homeFolder + EAppSettings.PROJECTS_FOLDER.getStringValueOfSetting();
        this.clientFolder = homeFolder + EAppSettings.CLIENT_FOLDER.getStringValueOfSetting();
        this.tasksRefreshInterval = 10;
    }

    public String getProjectsFolder() {
        return projectsFolder;
    }

    public long getTasksRefreshInterval() {
        return tasksRefreshInterval;
    }

    public String getClientFolder() {
        return clientFolder;
    }

}