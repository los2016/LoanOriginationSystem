package com.jpmorgan.awm.pb.mortgage.workitemhandlers;

import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;

public class ErrorWorkItemHandler implements WorkItemHandler {


	public void abortWorkItem(WorkItem item, WorkItemManager manager) {
	}


	public void executeWorkItem(WorkItem item, WorkItemManager manager) {
		throw new RuntimeException("Failure at item " + item.getId());
	}

}
