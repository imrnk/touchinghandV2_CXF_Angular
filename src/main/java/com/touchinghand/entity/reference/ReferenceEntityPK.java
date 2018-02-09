package com.touchinghand.entity.reference;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ReferenceEntityPK implements Serializable{
	
	@Column(name="reference_type_id")
	private Integer referenceTypeId;
	
	@Column(name="reference_data_key")
	private String referenceDataKey;

	public ReferenceEntityPK() {}
	
	
	public Integer getReferenceTypeId() {
		return referenceTypeId;
	}

	public void setReferenceTypeId(Integer referenceTypeId) {
		this.referenceTypeId = referenceTypeId;
	}

	public String getReferenceDataKey() {
		return referenceDataKey;
	}

	public void setReferenceDataKey(String referenceDataKey) {
		this.referenceDataKey = referenceDataKey;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((referenceDataKey == null) ? 0 : referenceDataKey.hashCode());
		result = prime * result + ((referenceTypeId == null) ? 0 : referenceTypeId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReferenceEntityPK other = (ReferenceEntityPK) obj;
		if (referenceDataKey == null) {
			if (other.referenceDataKey != null)
				return false;
		} else if (!referenceDataKey.equals(other.referenceDataKey))
			return false;
		if (referenceTypeId == null) {
			if (other.referenceTypeId != null)
				return false;
		} else if (!referenceTypeId.equals(other.referenceTypeId))
			return false;
		return true;
	}
	
	
	

}
