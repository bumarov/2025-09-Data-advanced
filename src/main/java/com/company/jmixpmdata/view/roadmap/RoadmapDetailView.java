package com.company.jmixpmdata.view.roadmap;

import com.company.jmixpmdata.entity.Roadmap;
import com.company.jmixpmdata.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "roadmaps/:id", layout = MainView.class)
@ViewController(id = "Roadmap.detail")
@ViewDescriptor(path = "roadmap-detail-view.xml")
@EditedEntityContainer("roadmapDc")
public class RoadmapDetailView extends StandardDetailView<Roadmap> {
}