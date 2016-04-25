package com.myorg.losmodel.model.questions;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Question implements Comparable<Question> {

	Set<Attribute> attributes = new TreeSet<Attribute>();
	protected HashSet<Question> childQuestions;
	protected String mandatoryCd;
	
	//Changed - having both parent and child is getting into an infinite recursive loop
	protected int parentQuestionId;

	public int getParentQuestionId() {
		return parentQuestionId;
	}

	public void setParentQuestionId(int parentQuestionId) {
		this.parentQuestionId = parentQuestionId;
	}

	protected QuestionContext questionContext;
	protected int questionId;
	protected String questionLongDesc;
	protected Role role;
	protected Section section;
	protected int sequenceNo;
	protected String toolTip;

	public String getToolTip() {
		return toolTip;
	}

	public void setToolTip(String toolTip) {
		this.toolTip = toolTip;
	}

	public int getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public void addChildQuestion(Question childQuestions) {
		this.childQuestions.add(childQuestions);
	}

	public boolean equals(Question q) {
		boolean ret = false;
		if (this.getQuestionId() == q.getQuestionId()) {
			ret = true;
		}
		return ret;
	}

	public Set<Attribute> getAttributes() {
		return attributes;
	}

	public HashSet<Question> getChildQuestions() {
		return childQuestions;
	}

	public String getMandatoryCd() {
		return mandatoryCd;
	}



	public QuestionContext getQuestionContext() {
		return questionContext;
	}

	public int getQuestionId() {
		return questionId;
	}

	public String getQuestionLongDesc() {
		return questionLongDesc;
	}

	public Role getRole() {
		return role;
	}

	public Section getSection() {
		return section;
	}

	public int hashCode() {
		return this.getQuestionId() % 25;
	}

	public void addAttribute(Attribute attribute) {
		this.attributes.add(attribute);
	}

	public void setMandatoryCd(String mandatoryCd) {
		this.mandatoryCd = mandatoryCd;
	}



	public void setQuestionContext(QuestionContext questionContext) {
		this.questionContext = questionContext;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public void setQuestionLongDesc(String questionLongDesc) {
		this.questionLongDesc = questionLongDesc;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	
	@Override
	public int compareTo(Question o) {
		int ret;

		if (this.getSequenceNo() > (o).getSequenceNo()) {
			ret = 1;
		} else if (this.getSequenceNo() == (o).getSequenceNo()) {
			ret = 0;
		} else {
			ret = -1;
		}

		return ret;

	}

	public String toString() {
		StringBuffer sb = new StringBuffer(10000);
		sb.append("{\"questionId\" :" + "\"" + questionId + "\"\n");
		sb.append("{\"questionLongDesc\" :" + "\"" + questionLongDesc + "\"\n");
		sb.append("{\"role\" :" + "\"" + role.getRoleNm() + "\"\n");
		sb.append("{\"mandatoryCd\" :" + "\"" + mandatoryCd + "\"\n");
		sb.append("{\"sequenceNo\" :" + "\"" + sequenceNo + "\"\n");
		sb.append("{\"toolTip\" :" + "\"" + toolTip + "\"\n");
		sb.append("{\"questionContext\" :" + "\"" + questionContext.getQuestionContextNm() + "\"\n");
		sb.append("{\n");
		Iterator<Attribute> i = this.getAttributes().iterator();
		while (i.hasNext()) {
			Attribute a = i.next();
			sb.append(a.toString());
		}
		sb.append("}\n");

		return sb.toString();
	}

}
