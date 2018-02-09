package com.touchinghand.entity.reference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="reference_data_type")
public class ReferenceDataTypeEntity {

	@Id
	@Column(name="reference_type_id")
	private int reference_type_id;
	
	@Column(name="reference_data_type")
	private String reference_data_type;
	
	@Column(name="reference_group")
	private int reference_group;

	public int getReference_type_id() {
		return reference_type_id;
	}

	public void setReference_type_id(int reference_type_id) {
		this.reference_type_id = reference_type_id;
	}

	public String getReference_data_type() {
		return reference_data_type;
	}

	public void setReference_data_type(String reference_data_type) {
		this.reference_data_type = reference_data_type;
	}

	public int getReference_group() {
		return reference_group;
	}

	public void setReference_group(int reference_group) {
		this.reference_group = reference_group;
	}
	
	
	
}
