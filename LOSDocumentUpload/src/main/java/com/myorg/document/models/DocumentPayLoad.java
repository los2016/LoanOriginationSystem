package com.myorg.document.models;

import java.io.InputStream;
import java.io.Serializable;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

public class DocumentPayLoad implements Serializable{
	
	private static final long serialVersionUID = -5536466161374316348L;
	
	private long mortgageApplicationID;
	private long documentTypeId;
	private String documentDescription;
	private String documentDownloadLink;
	
	@FormDataParam("documentPayload") 
	private InputStream uploadedInputStream;
	
	@FormDataParam("documentPayload")
	private FormDataContentDisposition uploadfile;

	public long getMortgageApplicationID() {
		return mortgageApplicationID;
	}
	public void setMortgageApplicationID(long mortgageApplicationID) {
		this.mortgageApplicationID = mortgageApplicationID;
	}
	public long getDocumentTypeId() {
		return documentTypeId;
	}
	public void setDocumentTypeId(long documentTypeId) {
		this.documentTypeId = documentTypeId;
	}
	public String getDocumentDescription() {
		return documentDescription;
	}
	public void setDocumentDescription(String documentDescription) {
		this.documentDescription = documentDescription;
	}
	public String getDocumentDownloadLink() {
		return documentDownloadLink;
	}
	public void setDocumentDownloadLink(String documentDownloadLink) {
		this.documentDownloadLink = documentDownloadLink;
	}
	public InputStream getUploadedInputStream() {
		return uploadedInputStream;
	}
	public void setUploadedInputStream(InputStream uploadedInputStream) {
		this.uploadedInputStream = uploadedInputStream;
	}
	public FormDataContentDisposition getUploadfile() {
		return uploadfile;
	}
	public void setUploadfile(FormDataContentDisposition uploadfile) {
		this.uploadfile = uploadfile;
	}
	
}
