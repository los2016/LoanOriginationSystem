package com.myorg.losmodel.model.questions;

import java.util.Set;

public class Timeline {

	public int getMortgageId() {
		return mortgageId;
	}
	public void setMortgageId(int mortgageId) {
		this.mortgageId = mortgageId;
	}
	public Set<TimelineSectionWrapper> getSections() {
		return sections;
	}
	public void setSections(Set<TimelineSectionWrapper> sections) {
		this.sections = sections;
	}
	protected int mortgageId;
	protected Set<TimelineSectionWrapper> sections;
	
	
}
