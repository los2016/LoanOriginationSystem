package com.myorg.document.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mortgage.loan_documents")
public class LoanDocument {
	
	@Id
	@Column(name ="SEQ_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int sequenceId;
	
	@Column(name ="LOAN_ID")
	private int loanId;
	
	@Column(name ="DOCUMENT_ID")
	private int documentId;
	
	@Column(name ="UPLOADED_LOC_URL_OR_PATH")
	private String uploadLocation;

	@Column(name ="CD_UPLOADED_AS")
	private String uploadedAs;

	public int getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(int sequenceId) {
		this.sequenceId = sequenceId;
	}

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public int getDocumentId() {
		return documentId;
	}

	public void setDocumentId(int documentId) {
		this.documentId = documentId;
	}

	public String getUploadLocation() {
		return uploadLocation;
	}

	public void setUploadLocation(String uploadLocation) {
		this.uploadLocation = uploadLocation;
	}

	public String getUploadedAs() {
		return uploadedAs;
	}

	public void setUploadedAs(String uploadedAs) {
		this.uploadedAs = uploadedAs;
	}
	
	
	
}
