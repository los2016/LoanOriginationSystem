package com.myorg.losmodel.model.questions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Section implements Comparable<Section> {

	protected String activeLanguage = "";
	protected Set<Section> childSections = new TreeSet<Section>();
	protected HashMap<String, String> futureSectionNm = new HashMap<String, String>();
	protected Section parentSection = null;
	protected HashMap<String, String> pastSectionNm = new HashMap<String, String>();

	public Set<Question> getQuestions() {
		return questions;
	}

	protected Set<Question> questions = new TreeSet<Question>();

	public void addQuestion(Question q) {
		questions.add(q);
	}

	protected HashMap<String, String> presentSectionNm = new HashMap<String, String>();

	protected int sectionId;

	protected int sectionLevel = -1; // -1 indicates that sectionLevel not
										// calculated

	protected int sequenceNo;

	public void addChildSection(Section child) {
		this.childSections.add(child);
	}

	@Override
	public int compareTo(Section o) {
		int ret;
		if (this.getSequenceNo() > o.getSequenceNo()) {
			ret = 1;
		} else if (this.getSequenceNo() == o.getSequenceNo()) {
			ret = 0;
		} else {
			ret = -1;
		}
		return ret;
	}

	public boolean equals(Section s) {
		boolean ret = false;
		if (s.getSectionId() == this.getSectionId()) {
			ret = true;
		}
		return ret;
	}

	public String getActiveLanguage() {
		return activeLanguage;
	}

	public Set<Section> getChildSections() {
		return childSections;
	}

	public String getFutureSectionNm() {
		return futureSectionNm.get(activeLanguage);
	}

	public String getFutureSectionNm(String language) {
		if (language == null || "".equals(language)) {
			language = "en";
		}
		return futureSectionNm.get(language);
	}

	public Section getParentSection() {
		return parentSection;
	}

	public String getPastSectionNm() {
		return pastSectionNm.get(activeLanguage);
	}

	public String getPastSectionNm(String language) {
		if (language == null || "".equals(language)) {
			language = "en";
		}
		return pastSectionNm.get(language);
	}

	public String getPresentSectionNm() {
		return presentSectionNm.get(activeLanguage);
	}

	public String getPresentSectionNm(String language) {
		if (language == null || "".equals(language)) {
			language = "en";
		}
		return presentSectionNm.get(language);
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

	public void setActiveLanguage(String activeLanguage) {
		this.activeLanguage = activeLanguage;
	}

	public void setFutureSectionNm(String language, String futureSectionNm) {
		if (language == null || "".equals(language)) {
			language = "en";
		}
		this.futureSectionNm.put(language, futureSectionNm);
	}

	public void setParentSection(Section parentSection) {
		this.parentSection = parentSection;
	}

	public void setPastSectionNm(String language, String pastSectionNm) {
		if (language == null || "".equals(language)) {
			language = "en";
		}
		this.pastSectionNm.put(language, pastSectionNm);
	}

	public void setPresentSectionNm(String language, String presentSectionNm) {
		if (language == null || "".equals(language)) {
			language = "en";
		}
		this.presentSectionNm.put(language, presentSectionNm);
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
