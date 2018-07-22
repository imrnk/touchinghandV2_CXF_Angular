package com.touchinghand.dto;

import java.util.Comparator;

public class ReferenceDataComparator implements Comparator<ReferenceData> {

	@Override
	public int compare(ReferenceData o1, ReferenceData o2) {
		
		return o1.getReferenceTypeId().compareTo(o2.getReferenceTypeId());
	}

}
