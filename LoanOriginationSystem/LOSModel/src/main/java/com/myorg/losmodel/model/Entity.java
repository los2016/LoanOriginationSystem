package com.myorg.losmodel.model;

public abstract class Entity extends Customer {
	
	static final long serialVersionUID = 1L;

	// Binary document 
	private byte[] entityDocument;
		
	public byte[] getEntityDocument() {
		return entityDocument;
	}

	public void setEntityDocument(byte[] entityDocument) {
		this.entityDocument = entityDocument;
	}
	
}
