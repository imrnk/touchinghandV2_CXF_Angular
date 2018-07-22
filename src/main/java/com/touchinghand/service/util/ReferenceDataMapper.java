package com.touchinghand.service.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.touchinghand.dto.GroupedReferenceData;
import com.touchinghand.dto.ReferenceData;
import com.touchinghand.entity.reference.ReferenceDataEntity;

@Component
public class ReferenceDataMapper {

	
	public List<ReferenceData> fromEntities(List<ReferenceDataEntity> rdes){
		
		return rdes.stream().map(this::fromEntity).collect(Collectors.toList());
	}
	
	public ReferenceData fromEntity(ReferenceDataEntity rde) {
		
		if(rde == null) return null;
		
		ReferenceData rd = new ReferenceData();
		rd.setReferenceTypeId(rde.getPk().getReferenceTypeId());
		rd.setReferenceKey(rde.getPk().getReferenceDataKey());
		rd.setReferenceValue(rde.getReferenceDataValue());

		return rd;
	}
	
	public List<GroupedReferenceData> groupReferenceDataByTypeId(List<ReferenceData> refdata) {
		Map<String, List<ReferenceData>> grouped =  refdata.stream().collect(Collectors.groupingBy(ReferenceData::getReferenceDataType));
		
		List<GroupedReferenceData> groupedData = new ArrayList<>();
		
		for(Map.Entry<String, List<ReferenceData>> entry : grouped.entrySet()) {
			groupedData.add(new GroupedReferenceData(entry.getKey(), entry.getValue()));
		}
		
		return groupedData;
	}
}
