package com.company.jmixpmdata.view.project;


import com.company.jmixpmdata.datatype.ProjectLabels;
import com.company.jmixpmdata.entity.Project;
import com.company.jmixpmdata.entity.Roadmap;
import com.company.jmixpmdata.validation.ProjectsService;
import com.company.jmixpmdata.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.router.Route;
import io.jmix.core.Metadata;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.DataContext;
import io.jmix.flowui.view.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.groups.Default;
import liquibase.exception.CommandValidationException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

@Route(value = "projects/:id", layout = MainView.class)
@ViewController(id = "Project.detail")
@ViewDescriptor(path = "project-detail-view.xml")
@EditedEntityContainer("projectDc")
public class ProjectDetailView extends StandardDetailView<Project> {
    @ViewComponent
    private DataContext dataContext;
    @Autowired
    private Metadata metadata;
    @ViewComponent
    private TypedTextField<ProjectLabels> projectLabelsField;
    @Autowired
    private ProjectsService projectsService;
    @Autowired
    private Notifications notifications;
    @Autowired
    private Validator validator;

    @Subscribe
    public void onInitEntity(final InitEntityEvent<Project> event) {
        //        Выбросит исключение
        //        Roadmap roadmap = metadata.create(Roadmap.class);
        Roadmap roadmap = dataContext.create(Roadmap.class);
        event.getEntity().setRoadmap(roadmap);

        projectLabelsField.setReadOnly(false);
        event.getEntity().setProjectLabels(new ProjectLabels(List.of("bug", "task", "new")));
    }

    @Subscribe(id = "commitWithBeanValidationButton", subject = "clickListener")
    public void onCommitWithBeanValidationButtonClick(final ClickEvent<JmixButton> event) {
        try {
            projectsService.save(getEditedEntity());
            close(StandardOutcome.CLOSE);
        } catch (ConstraintViolationException e) {
            StringBuilder sb = new StringBuilder();

            for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
                sb.append(violation.getMessage()).append("\n");
            }

            notifications.create(sb.toString())
                    .withPosition(Notification.Position.TOP_END)
                    .show();
        }

    }

    @Subscribe(id = "performValidationButton", subject = "clickListener")
    public void onPerformValidationButtonClick(final ClickEvent<JmixButton> event) {
        Set<ConstraintViolation<Project>> violations = validator.validate(getEditedEntity(), Default.class);

        showViolations(violations);
    }

    private void showViolations(Set<ConstraintViolation<Project>> violations) {
        StringBuilder sb = new StringBuilder();

        for (ConstraintViolation<?> violation : violations) {
            sb.append(violation.getMessage()).append("\n");
        }

        notifications.create(sb.toString())
                .withThemeVariant(NotificationVariant.LUMO_WARNING)
                .withPosition(Notification.Position.BOTTOM_END)
                .show();
    }
}