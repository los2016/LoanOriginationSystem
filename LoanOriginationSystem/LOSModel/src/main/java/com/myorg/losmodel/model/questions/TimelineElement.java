package com.myorg.losmodel.model.questions;

import java.util.Set;
import java.util.TreeSet;

public class TimelineElement implements Comparable<TimelineElement>{

	public long getMortgageId() {
		return mortgageId;
	}
	public void setMortgageId(long mortgageId) {
		this.mortgageId = mortgageId;
	}
	public Set<TimelineSectionWrapper> getSections() {
		return sections;
	}
	public void setSections(Set<TimelineSectionWrapper> sections) {
		this.sections = sections;
	}
	protected long mortgageId;
	protected long bpmProcessId;
	
	public void addSection(TimelineSectionWrapper s){
		sections.add(s);
	}
	public long getBpmProcessId() {
		return bpmProcessId;
	}
	public void setBpmProcessId(long bpmProcessId) {
		this.bpmProcessId = bpmProcessId;
	}
	protected Set<TimelineSectionWrapper> sections = new TreeSet<TimelineSectionWrapper>(new TimelineSectionWrapperComparator());;
	
	
	public int compareTo(TimelineElement o) {
		int ret = 0;
		if(this.getMortgageId() > o.getMortgageId()){
				ret = 1;
		}else if (this.getMortgageId() < o.getMortgageId()){
			ret = -1;
		}
		return ret;
	}
	
	public boolean equals(TimelineElement o){
		if(o != null){
			return (o.getMortgageId() == this.getMortgageId());
		}else{
			return false;
		}
	}
	
	public int hashCode(){
		return (int)(this.getMortgageId() % 1000l);
	}
}
