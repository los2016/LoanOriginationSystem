package com.myorg.losmodel.model;

import java.io.Serializable;

public final class Property implements Serializable {
	
		static final long serialVersionUID = 1L;
		
		public static final String LOAN_PROPERTY_DETAILS="propertyType=PROPERTY_TYPE_CD|isthsRecognizedCoOp=UNRECOGNIZED_COOP_FL|" + Address.NEW_PROPERTY_DETAILS ;
				
		private String propertyType;
		
		private String occupancyType;
		
		private ContactInfo buildingContact;
		
		private char isthsRecognizedCoOp;
		
		private Address propertyAddress;		
		
		public Address getPropertyAddress() {
			return propertyAddress;
		}

		public void setPropertyAddress(Address propertyAddress) {
			this.propertyAddress = propertyAddress;
		}

		public String getPropertyType() {
			return propertyType;
		}

		public void setPropertyType(String propertyType) {
			this.propertyType = propertyType;
		}

		public String getOccupancyType() {
			return occupancyType;
		}

		public void setOccupancyType(String occupancyType) {
			this.occupancyType = occupancyType;
		}

		public ContactInfo getBuildingContact() {
			return buildingContact;
		}

		public void setBuildingContact(ContactInfo buildingContact) {
			this.buildingContact = buildingContact;
		}

		public char getIsthsRecognizedCoOp() {
			return isthsRecognizedCoOp;
		}

		public void setIsthsRecognizedCoOp(char isthsRecognizedCoOp) {
			this.isthsRecognizedCoOp = isthsRecognizedCoOp;
		}				
}