package com.touchinghand.dto;

import java.io.Serializable;

public class ReferenceData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4124749914938550188L;

	private Integer referenceTypeId;
	
	private Integer referenceDataGroup;
	
	private String referenceDataType;
	
	private String referenceKey;
	
	private String referenceValue;

	public Integer getReferenceTypeId() {
		return referenceTypeId;
	}

	public void setReferenceTypeId(Integer referenceTypeId) {
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

	public Integer getReferenceDataGroup() {
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
