package com.myorg.losmodel.model.questions;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

//@TODO - This is a copy / paste of the section class. Need to re-factor so that we can wrap the section class
public class TimelineSectionWrapper {

	
	protected int sectionId;
	protected long mortgageId = -1l;
	public long getMortgageId() {
		return mortgageId;
	}

	public void setMortgageId(long mortgageId) {
		this.mortgageId = mortgageId;
	}

	protected String presentSectionNm = "";
	protected String pastSectionNm = "";
	protected String futureSectionNm = "";
	protected int sectionLevel = -1; // -1 indicates that sectionLevel not calculated
	protected int parentSectionId;
	protected int sequenceNo;
	protected String status = "UNKNOWN";
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	protected Set<TimelineSectionWrapper> childSections = new TreeSet<TimelineSectionWrapper>(new TimelineSectionWrapperComparator());



	public void addChildSection(TimelineSectionWrapper child) {
		this.childSections.add(child);
	}

	public boolean equals(TimelineSectionWrapper s) {
		boolean ret = false;
		boolean b1 = (s.getSectionId() == this.getSectionId());
		boolean b2 = (this.getMortgageId() == s.getMortgageId());
		
		System.out.println("Object 1: Section ID:"+ s.getSectionId()
		+ " Mortgage ID"+s.getMortgageId());
		
		System.out.println("Object 2: Section ID:"+ this.getSectionId()
		+ " Mortgage ID"+this.getMortgageId());
		
		
		System.out.println("B1 = "+b1);
		System.out.println("B2 "+b2);
		
		if(b1 && b2){
			ret = true;
		}
		System.out.println(" RETURN VALUE: "+ret);
		return ret;
	}
	public Set<TimelineSectionWrapper> getChildSections() {
		return childSections;
	}
	public String getFutureSectionNm() {
		return futureSectionNm;
	}
	public int getParentSectionId() {
		return parentSectionId;
	}
	public String getPastSectionNm() {
		return pastSectionNm;
	}

	public String getPresentSectionNm() {
		return presentSectionNm;
	}

	public int getSectionId() {
		return sectionId;
	}


	public int getSectionLevel() {
		return sectionLevel;
	}

	public int getSequenceNo() {
		return sequenceNo;
	}

	public int hashCode() {
		return this.getSectionId() % 10;
	}

	public void setChildSections(Set<TimelineSectionWrapper> childSections) {
		this.childSections = childSections;
	}

	public void setFutureSectionNm(String futureSectionNm) {
		this.futureSectionNm = futureSectionNm;
	}


	public void setParentSectionId(int parentSectionId) {
		this.parentSectionId = parentSectionId;
	}

	public void setPastSectionNm(String pastSectionNm) {
		this.pastSectionNm = pastSectionNm;
	}

	public void setPresentSectionNm(String presentSectionNm) {
		this.presentSectionNm = presentSectionNm;
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	public void setSectionLevel(int sectionLevel) {
		this.sectionLevel = sectionLevel;
	}

	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer(100000);
		sb.append("{\"sectionId\" :" + "\"" + sectionId + "\"\n");
		sb.append("{\"presentSectionNm\" :" + "\"" + this.getPresentSectionNm() + "\"\n");
		sb.append("{\"pastSectionNm\" :" + "\"" + this.getPastSectionNm() + "\"\n");
		sb.append("{\"futureSectionNm\" :" + "\"" + this.getFutureSectionNm() + "\"\n");
		sb.append("{\"sequenceNo\" :" + "\"" + sequenceNo + "\"\n");
		sb.append("{\"level\" :" + "\"" + sectionLevel + "\"\n");
		sb.append("{\n");
		Iterator<TimelineSectionWrapper> i2 = this.getChildSections().iterator();
		while (i2.hasNext()) {
			TimelineSectionWrapper schild = i2.next();
			sb.append(schild.toString());
		}
		sb.append("}\n");
		return sb.toString();

	}
	
	
}
