package com.myorg.losmodel.model.client;

import java.util.List;
import java.util.Set;

import com.myorg.losmodel.model.questions.Section;

public class Timeline {

	public List<?> getMortage() {
		return mortage;
	}
	public void setMortage(List<?> mortage) {
		this.mortage = mortage;
	}
	public Set<Section> getSections() {
		return sections;
	}
	public void setSections(Set<Section> sections) {
		this.sections = sections;
	}
	protected List<?> mortage;
	protected Set<Section> sections;
	
	
}
