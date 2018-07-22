package com.touchinghand.service;

import java.util.List;

import com.touchinghand.dto.GroupedReferenceData;
import com.touchinghand.dto.ReferenceData;

public interface ReferenceDataService {

	public List<ReferenceData> getReferenceDataOfType(int typeId);
	
	public List<GroupedReferenceData> getReferenceDataByGroupId(int groupId);
	
}
