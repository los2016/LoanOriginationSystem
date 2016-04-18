package com.jpmorgan.awm.pb.mortgageorigination.model;

import java.util.Date;

public class Customer {

	private long customerId;
	private String customerName;
	private Date lastLogin;
	private int age;
	
	
	
	public Customer(long customerId, String customerName, Date lastLogin, int age) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.lastLogin = lastLogin;
		this.age = age;
	}
	
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (customerId ^ (customerId >>> 32));
        return result;
	}

	@Override
	public boolean equals(Object obj) {
	      if (this == obj)
	            return true;
	        if (obj == null)
	            return false;
	        if (getClass() != obj.getClass())
	            return false;
	        Customer other = (Customer) obj;
	        if (customerId != other.customerId)
	            return false;
	        return true;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", age=" + age
                + ", lastLogin=" + lastLogin + "]";
	}
	
	
	
}
