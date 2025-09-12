package com.company.jmixpmdata.strategy;

import com.company.jmixpmdata.datatype.ProjectLabels;
import com.company.jmixpmdata.datatype.ProjectLabelsDatatype;
import com.company.jmixpmdata.entity.Project;
import com.vaadin.flow.component.Component;
import io.jmix.core.JmixOrder;
import io.jmix.core.metamodel.datatype.Datatype;
import io.jmix.core.metamodel.model.MetaClass;
import io.jmix.core.metamodel.model.MetaProperty;
import io.jmix.core.metamodel.model.Range;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.ComponentGenerationContext;
import io.jmix.flowui.component.ComponentGenerationStrategy;
import io.jmix.flowui.component.textfield.TypedTextField;
import org.springframework.core.Ordered;

@org.springframework.stereotype.Component
public class ProjectFieldsComponentGenerationStrategy implements ComponentGenerationStrategy, Ordered {

    private final UiComponents uiComponents;

    public ProjectFieldsComponentGenerationStrategy(UiComponents uiComponents) {
        this.uiComponents = uiComponents;
    }

    @Override
    public Component createComponent(ComponentGenerationContext context) {
        if (!"projectLabels".equals(context.getProperty())) {
            return null;
        }

        MetaClass metaClass = context.getMetaClass();
        if (!metaClass.getJavaClass().equals(Project.class)) {
            return null;
        }

        MetaProperty property = metaClass.getProperty(context.getProperty());
        Range range = property.getRange();

        if (range.isDatatype()) {
            Datatype projectLabelsDatatype = range.asDatatype();
            if (projectLabelsDatatype instanceof ProjectLabelsDatatype) {
                return create(context);
            }
        }
        return null;
    }

    private TypedTextField<ProjectLabels> create(ComponentGenerationContext context) {
        TypedTextField<ProjectLabels> field = uiComponents.create(TypedTextField.class);
        field.setValueSource(context.getValueSource());
        field.setReadOnly(true);

        return field;

    }

    @Override
    public int getOrder() {
        return JmixOrder.HIGHEST_PRECEDENCE;
    }
}
