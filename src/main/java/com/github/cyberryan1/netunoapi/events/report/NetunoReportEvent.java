package com.github.cyberryan1.netunoapi.events.report;

import com.github.cyberryan1.netunoapi.events.NetunoEvent;
import com.github.cyberryan1.netunoapi.models.reports.NReport;

import java.util.List;

/**
 * This event is fired when a player is reported
 */
public class NetunoReportEvent implements NetunoEvent {

    private final List<NReport> reports;

    public NetunoReportEvent( List<NReport> report ) {
        this.reports = report;
    }

    /**
     * @return The list of {@link NReport} that were sent
     */
    public List<NReport> getReport() {
        return reports;
    }
}