package com.touchinghand.dto;

import java.util.List;

public class GroupedReferenceData {

	private String referenceDataType;
	
	private List<ReferenceData> refData;

	public GroupedReferenceData() {}
	
	public GroupedReferenceData(String rdType, List<ReferenceData> rData) {
		this.referenceDataType = rdType;
		this.refData = rData;
	}
	
	public String getReferenceDataType() {
		return referenceDataType;
	}

	public void setReferenceDataType(String referenceDataType) {
		this.referenceDataType = referenceDataType;
	}

	public List<ReferenceData> getRefData() {
		return refData;
	}

	public void setRefData(List<ReferenceData> refData) {
		this.refData = refData;
	}
	
	
}
