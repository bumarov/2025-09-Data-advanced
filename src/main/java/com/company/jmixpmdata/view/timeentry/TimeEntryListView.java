package com.company.jmixpmdata.view.timeentry;

import com.company.jmixpmdata.entity.TimeEntry;
import com.company.jmixpmdata.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "time-entries", layout = MainView.class)
@ViewController(id = "TimeEntry.list")
@ViewDescriptor(path = "time-entry-list-view.xml")
@LookupComponent("timeEntriesDataGrid")
@DialogMode(width = "64em")
public class TimeEntryListView extends StandardListView<TimeEntry> {
}