package org.power_systems_modelica.psm.gui.service;

import org.power_systems_modelica.psm.gui.view.WorkflowNewController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkflowThread extends Thread {

    public void run() {
        LOG.debug("WorkflowThread");
    }

    private static final Logger LOG = LoggerFactory.getLogger(WorkflowThread.class);
}
