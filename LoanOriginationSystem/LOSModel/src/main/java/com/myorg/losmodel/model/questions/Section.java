package com.myorg.losmodel.model.questions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Section{
	
	public String getPresentSectionNm() {
		return presentSectionNm;
	}


	public void setPresentSectionNm(String presentSectionNm) {
		this.presentSectionNm = presentSectionNm;
	}


	public String getPastSectionNm() {
		return pastSectionNm;
	}


	public void setPastSectionNm(String pastSectionNm) {
		this.pastSectionNm = pastSectionNm;
	}


	public String getFutureSectionNm() {
		return futureSectionNm;
	}


	public void setFutureSectionNm(String futureSectionNm) {
		this.futureSectionNm = futureSectionNm;
	}


	public int getParentSectionId() {
		return parentSectionId;
	}


	public void setParentSectionId(int parentSectionId) {
		this.parentSectionId = parentSectionId;
	}


	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}


	public void setChildSections(Set<Section> childSections) {
		this.childSections = childSections;
	}


	protected String presentSectionNm = "";
	protected String pastSectionNm = "";
	protected String futureSectionNm = "";
	protected int sectionLevel = -1; // -1 indicates that sectionLevel not calculated
	protected int sectionId;
	protected int parentSectionId;
	protected int sequenceNo;

	public Set<Question> getQuestions() {
		return questions;
	}


	protected Set<Question> questions = new TreeSet<Question>(new QuestionComparator());
	protected Set<Section> childSections = new TreeSet<Section>(new SectionComparator());

	
	public void addQuestion(Question q) {
		questions.add(q);
	}


	public void addChildSection(Section child) {
		this.childSections.add(child);
	}




	public boolean equals(Section s) {
		boolean ret = false;
		if (s.getSectionId() == this.getSectionId()) {
			ret = true;
		}
		return ret;
	}


	public Set<Section> getChildSections() {
		return childSections;
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
		Iterator<Question> i = this.getQuestions().iterator();
		while (i.hasNext()) {
			Question q = i.next();
			sb.append(q.toString());
		}
		sb.append("}\n");

		sb.append("{\n");
		Iterator<Section> i2 = this.getChildSections().iterator();
		while (i2.hasNext()) {
			Section schild = i2.next();
			sb.append(schild.toString());
		}
		sb.append("}\n");
		return sb.toString();

	}

}
