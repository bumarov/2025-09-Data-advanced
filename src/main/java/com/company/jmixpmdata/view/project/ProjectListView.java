package com.company.jmixpmdata.view.project;

import com.company.jmixpmdata.datatype.ProjectLabels;
import com.company.jmixpmdata.entity.Project;
import com.company.jmixpmdata.entity.Roadmap;
import com.company.jmixpmdata.entity.User;
import com.company.jmixpmdata.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.UnconstrainedDataManager;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Route(value = "projects", layout = MainView.class)
@ViewController(id = "Project.list")
@ViewDescriptor(path = "project-list-view.xml")
@LookupComponent("projectsDataGrid")
@DialogMode(width = "64em")
public class ProjectListView extends StandardListView<Project> {
    @Autowired
    private DataManager dataManager;
    @Autowired
    private UnconstrainedDataManager unconstrainedDataManager;
    @Autowired
    private CurrentAuthentication currentAuthentication;
    @ViewComponent
    private CollectionContainer<Project> projectsDc;
    @Autowired
    private Notifications notifications;

    @Subscribe(id = "createWithDataMangerButton", subject = "clickListener")
    public void onCreateWithDataMangerButtonClick(final ClickEvent<JmixButton> event) {
        Project project = dataManager.create(Project.class);
        project.setName("Project " + RandomStringUtils.randomAlphabetic(5));

        final User user = (User) currentAuthentication.getUser();
        project.setManager(user);

        Roadmap roadmap = dataManager.create(Roadmap.class);
        project.setRoadmap(roadmap);

        project.setProjectLabels(new ProjectLabels(List.of("demo", "test")));

        Project saved = unconstrainedDataManager.save(project, roadmap).get(project);

        projectsDc.getMutableItems().add(saved);
    }

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        int newProjectsCount = dataManager.loadValue("select count(proj) from Project proj " +
                "where :session_isManager = TRUE " +
                "and proj.status = 10", Integer.class)
                .one();

        if (newProjectsCount > 0) {
            notifications.create("New projects:", "Projects in a new status - " + newProjectsCount)
                    .withPosition(Notification.Position.TOP_END)
                    .show();
        }
    }
    
    
}