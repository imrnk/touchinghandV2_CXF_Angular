package com.touchinghand.entity.reference;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="reference_data")
public class ReferenceDataEntity {
	
	@EmbeddedId
	private ReferenceEntityPK pk;
	
	
	@Column(name="reference_data_value")
	private String referenceDataValue;


	public ReferenceEntityPK getPk() {
		return pk;
	}


	public void setPk(ReferenceEntityPK pk) {
		this.pk = pk;
	}


	public String getReferenceDataValue() {
		return referenceDataValue;
	}


	public void setReferenceDataValue(String referenceDataValue) {
		this.referenceDataValue = referenceDataValue;
	}
	
	

}
