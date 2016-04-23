package com.myorg.glass.chat;

public class ChatMessage {
  
  private String recipient;
  
  public String getRecipient() { return recipient; }
  public void setRecipient(String recipient) { this.recipient = recipient; }
  
  private String sender;
  
  public String getSender() { return sender; }
  public void setSender(String sender) { this.sender = sender; }
  
  private String message;
  
  public String getMessage() { return message; }
  public void setMessage(String message) { this.message = message; }
  
  

  private String targetElement;
  public String getTargetElement() { return targetElement; }
	public void setTargetElement(String targetElement) { this.targetElement = targetElement; }
	
	private String targetPageId;
	public String getTargetPageId() { return targetPageId; }
	public void setTargetPageId(String targetPageId) { this.targetPageId = targetPageId; }
	

	private boolean glass;
	public boolean isGlass() { return glass; }
	public void setGlass(boolean glass) { this.glass = glass; }

}
