package com.company.jmixpmdata.validation;

import com.company.jmixpmdata.entity.Project;
import io.jmix.core.DataManager;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class ProjectsService {

    private final DataManager dataManager;

    public ProjectsService(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void save(@NotNull @Valid Project project) {
        dataManager.save(project);
    }
}
