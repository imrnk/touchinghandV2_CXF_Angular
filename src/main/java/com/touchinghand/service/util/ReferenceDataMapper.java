package com.touchinghand.service.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

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
}
