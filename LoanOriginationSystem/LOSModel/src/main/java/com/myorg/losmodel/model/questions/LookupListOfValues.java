package com.myorg.losmodel.model.questions;

public class LookupListOfValues implements Comparable<LookupListOfValues> {

	public String getLookupCd() {
		return lookupCd;
	}

	public void setLookupCd(String lookupCd) {
		this.lookupCd = lookupCd;
	}

	public String getLookupValue() {
		return lookupValue;
	}

	public void setLookupValue(String lookupValue) {
		this.lookupValue = lookupValue;
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	String lookupCd;
	String lookupValue;
	int sortOrder;

	@Override
	public int compareTo(LookupListOfValues o) {
		int ret;
		if (this.getSortOrder() > o.getSortOrder()) {
			ret = 1;
		} else if (this.getSortOrder() == o.getSortOrder()) {
			ret = 0;
		} else {
			ret = -1;
		}
		return ret;
	}

	public boolean equals(LookupListOfValues o) {
		boolean ret = false;
		if ((o != null) && (o.getLookupCd() != null) && (this.getLookupCd() != null)
				&& (o.getLookupCd().equals(o.getLookupCd()))) {
			ret = true;
		}
		return ret;
	}

	public int hashCode() {
		return this.getLookupCd().hashCode();
	}

	public String toString() {
		StringBuffer sb = new StringBuffer(1000);
		sb.append("{\"lookupCd\" : \"" + lookupCd + "\"\n");
		sb.append("\"lookupValue\" : \"" + lookupValue + "\"\n");
		sb.append("\"sortOrder\" : \"" + sortOrder + "\"\n}");

		return sb.toString();
	}

}
