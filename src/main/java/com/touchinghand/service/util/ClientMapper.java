package com.touchinghand.service.util;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.cxf.common.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.touchinghand.common.DateResolver;
import com.touchinghand.dto.Client;
import com.touchinghand.entity.client.ClientEntity;

@Component
public class ClientMapper {

	@Autowired
	private DateResolver dateResolver;

	
	public List<Client> fromEntities(List<ClientEntity> ces){
		if(CollectionUtils.isEmpty(ces)) return null;
		return ces.stream().map(this::fromEntity).collect(Collectors.toList());
	}
	
	public Client fromEntity(ClientEntity ce) {
		if (ce == null)
			return null;
		Client c = new Client();
		c.setClientId(ce.getClientId());
		c.setClientName(ce.getClientName());
		c.setAddress(ce.getAddress());
		c.setAge(ce.getAge());
		c.setStatus(ce.getStatus());
		c.setCity(ce.getCity());
		c.setCountry(ce.getCountry());
		c.setEducation(ce.getEducation());
		c.setEmail(ce.getEmail());
		c.setGender(ce.getGender());
		c.setMaritalStatus(ce.getMaritalStatus());
		c.setMobile(ce.getMobile());
		c.setPin(ce.getPin());
		c.setProfession(ce.getProfession());
		c.setReference(ce.getReference());
		c.setSecondPhone(ce.getSecondPhone());
		c.setState(ce.getState());
		c.setFollowupdate(dateResolver.toStringDate(ce.getFollowupDate()));
		return c;

	}

	public ClientEntity toEntity(Client c) {
		if (c == null)
			return null;
		ClientEntity ce = new ClientEntity();
		ce.setClientId(c.getClientId());
		ce.setClientName(c.getClientName());
		ce.setAddress(c.getAddress());
		ce.setAge(c.getAge());
		ce.setStatus(c.getStatus());
		ce.setCity(c.getCity());
		ce.setCountry(c.getCountry());
		ce.setEducation(c.getEducation());
		ce.setEmail(c.getEmail());
		ce.setGender(c.getGender());
		ce.setMaritalStatus(c.getMaritalStatus());
		ce.setMobile(c.getMobile());
		ce.setPin(c.getPin());
		ce.setProfession(c.getProfession());
		ce.setReference(c.getReference());
		ce.setSecondPhone(c.getSecondPhone());
		ce.setState(c.getState());
		ce.setFollowupDate(dateResolver.toLocalDate(c.getFollowupdate()));
		return ce;
	}

}
