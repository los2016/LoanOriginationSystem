package com.myorg.losmodel.model.questions;

import java.util.Set;
import java.util.TreeSet;

public class Timeline {

Set<TimelineElement> timelineElement = new TreeSet<TimelineElement>();

public Set<TimelineElement> getTimelineElement() {
	return timelineElement;
}

public void setTimelineElement(Set<TimelineElement> timelineElement) {
	this.timelineElement = timelineElement;
}	
	
}
