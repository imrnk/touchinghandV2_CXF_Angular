package com.touchinghand.dto;



public class ReferenceData {
	

	private int referenceTypeId;
	
	private int referenceDataGroup;
	
	private String referenceDataType;
	
	private String referenceKey;
	
	private String referenceValue;

	public int getReferenceTypeId() {
		return referenceTypeId;
	}

	public void setReferenceTypeId(int referenceTypeId) {
		this.referenceTypeId = referenceTypeId;
	}

	public String getReferenceKey() {
		return referenceKey;
	}

	public void setReferenceKey(String referenceKey) {
		this.referenceKey = referenceKey;
	}

	public String getReferenceValue() {
		return referenceValue;
	}

	public void setReferenceValue(String referenceValue) {
		this.referenceValue = referenceValue;
	}

	public int getReferenceDataGroup() {
		return referenceDataGroup;
	}

	public void setReferenceDataGroup(int referenceDataGroup) {
		this.referenceDataGroup = referenceDataGroup;
	}

	public String getReferenceDataType() {
		return referenceDataType;
	}

	public void setReferenceDataType(String referenceDataType) {
		this.referenceDataType = referenceDataType;
	}
	
}
