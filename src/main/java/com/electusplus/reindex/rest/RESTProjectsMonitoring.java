package com.electusplus.reindex.rest;

import com.electusplus.reindex.data_warehouse.ProjectsMonitoringController;

import static spark.Spark.get;
import static spark.Spark.path;

public class RESTProjectsMonitoring  extends ARest {
    private final ProjectsMonitoringController projectsMonitoringController = new ProjectsMonitoringController();

    @Override
    public void rest() {
        path("/projects_monitoring", () -> {
            get("/projects_status", (request, response) -> {
                logger.info("Got request for projects monitoring");
                return objectToString(projectsMonitoringController.getProjectsStatusForUI());
            });
            get("/get_report/:projectId", (request, response) -> {
                logger.info("Got request for project report for project with id: " + request.params(":id"));
//                return objectToString(reindexSettingsController.getProjectStatusForSettingsPageForId(request.params(":id")));
                return null;
            });
        });
    }
}
