package com.myorg.losmodel.model.questions;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Question{


	protected int questionId;
	protected String questionLongDesc;
	protected int sequenceNo;
	protected String toolTip;
	protected String mandatoryCd;
	protected int parentQuestionId;
	protected Role role;
	protected QuestionContext questionContext;
	Set<Attribute> attributes = new TreeSet<Attribute>(new AttrubuteComparator());
	protected Set<Question> childQuestions = new TreeSet<Question>(new QuestionComparator());
	
	
	public void addAttribute(Attribute attribute) {
		this.attributes.add(attribute);
	}
	public void addChildQuestion(Question childQuestions) {
		this.childQuestions.add(childQuestions);
	}

	public int compareTo(Question o) {
		int ret;

		if (this.getSequenceNo() > (o).getSequenceNo()) {
			ret = -1;
		} else if (this.getSequenceNo() == (o).getSequenceNo()) {
			ret = 0;
		} else {
			ret = 1;
		}
  
		return ret;

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

	public Set<Question> getChildQuestions() {
		return childQuestions;
	}

	public String getMandatoryCd() {
		return mandatoryCd;
	}


	public int getParentQuestionId() {
		return parentQuestionId;

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

	public int getSequenceNo() {
		return sequenceNo;
	}

	public String getToolTip() {
		return toolTip;
	}

	public int hashCode() {
		return this.getQuestionId() % 25;
	}

	//public Section getSection() {
	//return section;
	//}

	public void setMandatoryCd(String mandatoryCd) {
		this.mandatoryCd = mandatoryCd;
	}


	public void setParentQuestionId(int parentQuestionId) {
		this.parentQuestionId = parentQuestionId;

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

	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}


	public void setToolTip(String toolTip) {
		this.toolTip = toolTip;
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
