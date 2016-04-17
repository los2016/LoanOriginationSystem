package com.myorg.document.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mortgage.document_metadata")
public class Document {
	@Id
	@Column(name ="DOCUMENT_ID")
	private int documentId;

	@Column(name ="DOCUMENT_NM")
	private String documentName;
	
	public Document() {}
	
	public Document (int documentId) {
		this.documentId = documentId;
	}

	public int getDocumentId() {
		return documentId;
	}

	public void setDocumentId(int documentId) {
		this.documentId = documentId;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	
}
