package com.touchinghand.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.touchinghand.dto.GroupedReferenceData;
import com.touchinghand.dto.ReferenceData;
import com.touchinghand.dto.ReferenceDataComparator;
import com.touchinghand.entity.reference.ReferenceDataEntity;
import com.touchinghand.entity.reference.ReferenceDataTypeEntity;
import com.touchinghand.service.util.ReferenceDataMapper;

@Component
public class ReferenceDataServiceImpl implements ReferenceDataService {
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	private ReferenceDataMapper rdMapper;
	
	@Override
	@Transactional
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
		Collections.sort(refData, new ReferenceDataComparator());
		
		try {
			ReferenceDataTypeEntity rdeResult = em.createQuery(cqe).getSingleResult();
			if(rdeResult != null) {
				refData.forEach(r -> {r.setReferenceDataType(rdeResult.getReference_data_type()); 
				r.setReferenceDataGroup(rdeResult.getReference_group());});
			}
		} catch (NoResultException e) {
		}
		
		
		return refData;
	}

	@Override
	public List<GroupedReferenceData> getReferenceDataByGroupId(int groupId) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<ReferenceDataTypeEntity> cqDataType = cb.createQuery(ReferenceDataTypeEntity.class);
		Root<ReferenceDataTypeEntity> fromDataType = cqDataType.from(ReferenceDataTypeEntity.class);
		
		cqDataType.where(cb.equal(fromDataType.get("reference_group"), groupId));
		List<ReferenceDataTypeEntity> refDataTypes = em.createQuery(cqDataType).getResultList();
		
		
		CriteriaBuilder cb2 = em.getCriteriaBuilder();
		CriteriaQuery<ReferenceDataEntity> cq = cb2.createQuery(ReferenceDataEntity.class);
		Root<ReferenceDataEntity> from = cq.from(ReferenceDataEntity.class);
		
		cq.where(cb.isNotNull(from.get("referenceDataValue")));
		List<ReferenceDataEntity> output = em.createQuery(cq).getResultList();
		
		
		List<Integer> typeIds = refDataTypes.stream()
				.map(ReferenceDataTypeEntity::getReference_type_id)
				.collect(Collectors.toList());
		
		List<ReferenceDataEntity> results = output
				.stream()
				.filter(refData -> typeIds.stream()
						.anyMatch(id -> id == refData.getPk().getReferenceTypeId()))
				.collect(Collectors.toList());
		
		List<ReferenceData> refData = rdMapper.fromEntities(results);
		
		//Set the reference data type in reference data
		List<ReferenceData> populatedRefData = refData
		.stream()
		.flatMap(rd -> 
				refDataTypes
				.stream()
				.filter(rdt -> rdt.getReference_type_id() == rd.getReferenceTypeId())
				.map(rdtf -> 
						{
						rd.setReferenceDataGroup(rdtf.getReference_group());
						rd.setReferenceDataType(rdtf.getReference_data_type());
						return rd;
						}
				
				)).collect(Collectors.toList());
		
		Collections.sort(populatedRefData, new ReferenceDataComparator());
		populatedRefData.forEach(System.out::println);
		return rdMapper.groupReferenceDataByTypeId(populatedRefData);
	}
}
