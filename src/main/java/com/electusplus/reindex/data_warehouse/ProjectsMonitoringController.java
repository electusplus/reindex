package com.electusplus.reindex.data_warehouse;

import com.electusplus.reindex.reindex_execution_plan_monitoring.ProjectStatusPOJO;
import com.electusplus.reindex.reindex_execution_plan_monitoring_for_ui.projects_monitoring.ProjectStatusForUIProjectsMonitoringPOJO;
import com.electusplus.reindex.app_settings.AppSettingsPOJO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProjectsMonitoringController {
    private static final Logger logger = LogManager.getLogger();
    DataWarehouse dataWarehouse = DataWarehouse.getInstance();
    private final Map<String, ProjectStatusPOJO> projectsStatus = dataWarehouse.getProjectsStatus();
    private final AppSettingsPOJO appSettings = dataWarehouse.getAppSettings();

    public List<ProjectStatusForUIProjectsMonitoringPOJO> getProjectsStatusForUI() {
        return projectsStatus.values().stream()
                .map(ProjectStatusForUIProjectsMonitoringPOJO::new)
                .collect(Collectors.toList());
    }
}
