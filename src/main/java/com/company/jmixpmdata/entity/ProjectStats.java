package com.company.jmixpmdata.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

@JmixEntity
public class ProjectStats {
    private String projectName;

    private Integer tasksCount;

    private Integer plannedEfforts;

    private Integer actualEfforts;

    public Integer getActualEfforts() {
        return actualEfforts;
    }

    public void setActualEfforts(Integer actualEfforts) {
        this.actualEfforts = actualEfforts;
    }

    public Integer getPlannedEfforts() {
        return plannedEfforts;
    }

    public void setPlannedEfforts(Integer plannedEfforts) {
        this.plannedEfforts = plannedEfforts;
    }

    public Integer getTasksCount() {
        return tasksCount;
    }

    public void setTasksCount(Integer tasksCount) {
        this.tasksCount = tasksCount;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

}