package com.touchinghand.common;

public class ErrorResponse {
	
	public ErrorResponse() {}
	
	public ErrorResponse(String rc, String rm) {
		this.responseCode = rc;
		this.responseMessage = rm;
	}
	
	private String responseCode;
	private String responseMessage;
	
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
	

}
