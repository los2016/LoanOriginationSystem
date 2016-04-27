package com.myorg.losmodel.model.questions;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Attribute {

	protected int attributeId;
	protected String colName;
	protected String colDesc;
	protected DataType dataType;
	protected int sequenceNo;
	protected String objectAttrFQN;
	protected boolean isMandatoryFl;
	protected int lengthNum;
	protected String lookupEntityNm;
	protected Set<LookupListOfValues> listOfValues = new TreeSet<LookupListOfValues>(new LookupListOfValuesComparator());
	



	public void addListOfValues(LookupListOfValues l) {
		listOfValues.add(l);
	}

	public int compareTo(Attribute o) {
		int ret;
		if (this.getSequenceNo() > o.getSequenceNo()) {
			ret = -1;
		} else if (this.getSequenceNo() == o.getSequenceNo()) {
			ret = 0;
		} else {
			ret = 1;
		}
		return ret;
	}

	
	public boolean equals(Attribute e) {
		boolean ret = false;
		if (this.getAttributeId() == e.getAttributeId()) {
			ret = true;
		}

		ret = false;
		return ret;
	}

	public int getAttributeId() {
		return attributeId;
	}

	public String getColDesc() {
		return colDesc;
	}

	public String getColName() {
		return colName;
	}

	public DataType getDataType() {
		return dataType;
	}

	public int getLengthNum() {
		return lengthNum;
	}

	public Set<LookupListOfValues> getListOfValues() {
		return listOfValues;
	}

	public String getLookupEntityNm() {
		return lookupEntityNm;
	}

	public String getObjectAttrFQN() {
		return objectAttrFQN;
	}

	public int getSequenceNo() {
		return sequenceNo;
	}

	public int hashCode() {
		return this.getAttributeId() % 30;

	}

	public boolean isMandatoryFl() {
		return isMandatoryFl;
	}

	public void setAttributeId(int attributeId) {
		this.attributeId = attributeId;
	}

	public void setColDesc(String colDesc) {
		this.colDesc = colDesc;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	public void setLengthNum(int lengthNum) {
		this.lengthNum = lengthNum;
	}

	public void setLookupEntityNm(String lookupEntityNm) {
		this.lookupEntityNm = lookupEntityNm;
	}

	public void setMandatoryFl(boolean isMandatoryFl) {
		this.isMandatoryFl = isMandatoryFl;
	}

	public void setObjectAttrFQN(String objectAttrFQN) {
		this.objectAttrFQN = objectAttrFQN;
	}

	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer(1000);
		sb.append("{\"attibuteID\" :" + "\"" + attributeId + "\"\n");
		sb.append("\"colName\" : \"" + colName + "\"\n");
		sb.append("\"colDesc\" : \"" + colDesc + "\"\n");
		sb.append("\"dataType\" : \"" + dataType.getDataTypeNm() + "\"\n");
		sb.append("\"sequenceNo\" : \"" + sequenceNo + "\"\n");
		sb.append("{\n");
		Iterator<LookupListOfValues> i = this.getListOfValues().iterator();
		while (i.hasNext()) {
			LookupListOfValues l = i.next();
			sb.append(l.toString());
		}
		sb.append("}\n");

		return sb.toString();
	}

}
