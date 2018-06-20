package com.touchinghand.entity.reference;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="reference_data")
public class ReferenceDataEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8356515908970859264L;


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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
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
		ReferenceDataEntity other = (ReferenceDataEntity) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}
	
	

}
