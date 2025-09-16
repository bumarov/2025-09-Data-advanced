package com.company.jmixpmdata.view.project;

import com.company.jmixpmdata.entity.Project;
import com.company.jmixpmdata.repository.ProjectRepository;
import com.company.jmixpmdata.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.core.LoadContext;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static io.jmix.core.repository.JmixDataRepositoryUtils.buildPageRequest;
import static io.jmix.core.repository.JmixDataRepositoryUtils.buildRepositoryContext;

@Route(value = "projects-remove", layout = MainView.class)
@ViewController(id = "Project.lookup")
@ViewDescriptor(path = "project-lookup-view.xml")
@LookupComponent("projectsDataGrid")
@DialogMode(width = "64em")
public class ProjectLookupView extends StandardListView<Project> {

    @Autowired
    private ProjectRepository repository;

    @Install(to = "projectsDl", target = Target.DATA_LOADER)
    private List<Project> loadDelegate(LoadContext<Project> context) {
        return repository.findAll(buildPageRequest(context), buildRepositoryContext(context)).getContent();
    }
}