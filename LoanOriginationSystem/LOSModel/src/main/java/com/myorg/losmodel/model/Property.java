package com.myorg.losmodel.model;

public class Property {

		private Address propertyAddress;	
		
		private String propertyType;
		
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

		private String occupancyType;	
}
