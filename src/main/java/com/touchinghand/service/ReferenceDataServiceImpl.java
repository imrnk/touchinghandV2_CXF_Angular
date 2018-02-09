package com.touchinghand.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.touchinghand.dto.ReferenceData;
import com.touchinghand.entity.reference.ReferenceDataEntity;
import com.touchinghand.entity.reference.ReferenceDataTypeEntity;
import com.touchinghand.service.util.ReferenceDataMapper;

@Component
public class ReferenceDataServiceImpl implements ReferenceDataService {
	
	@Autowired
	EntityManager em;
	
	@Autowired
	private ReferenceDataMapper rdMapper;
	
	@Override
	public List<ReferenceData> getReferenceDataOfType(int typeId) {
		
		//em.find(ReferenceDataEntity.class, new ReferenceEntityPK(typeId));
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ReferenceDataEntity> cq = cb.createQuery(ReferenceDataEntity.class);
		Root<ReferenceDataEntity> from = cq.from(ReferenceDataEntity.class);
		
		cq.where(cb.isNotNull(from.get("referenceDataValue")));
		List<ReferenceDataEntity> output = em.createQuery(cq).getResultList();
		
		List<ReferenceDataEntity> results = output.stream().filter(r -> r.getPk().getReferenceTypeId() == typeId).collect(Collectors.toList());
		
		CriteriaQuery<ReferenceDataTypeEntity> cqe = cb.createQuery(ReferenceDataTypeEntity.class);
		Root<ReferenceDataTypeEntity> fromRDE = cqe.from(ReferenceDataTypeEntity.class);
		cqe.where(cb.equal(fromRDE.get("reference_type_id"), typeId));
		
		List<ReferenceData> refData = rdMapper.fromEntities(results);
		
		try {
			ReferenceDataTypeEntity rdeResult = em.createQuery(cqe).getSingleResult();
			if(rdeResult != null) {
				refData.forEach(r -> {r.setReferenceDataType(rdeResult.getReference_data_type()); r.setReferenceDataGroup(rdeResult.getReference_group());});
			}
		} catch (NoResultException e) {
		}
		
		
		return refData;
	}

}
