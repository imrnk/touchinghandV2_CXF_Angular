package com.touchinghand.service;

import java.util.List;

import com.touchinghand.dto.ReferenceData;

public interface ReferenceDataService {

	public List<ReferenceData> getReferenceDataOfType(int typeId);
	
	public List<ReferenceData> getReferenceDataByGroupId(int groupId);
	
}
