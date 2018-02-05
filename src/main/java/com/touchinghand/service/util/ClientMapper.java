package com.touchinghand.service.util;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.cxf.common.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.touchinghand.common.CommonUtils;
import com.touchinghand.common.DateResolver;
import com.touchinghand.dto.Client;
import com.touchinghand.entity.client.ClientEntity;

@Component
public class ClientMapper {

	@Autowired
	private DateResolver dateResolver;
	
	@Autowired
	private CommonUtils util;
	
	@Autowired
	private ClientMseMapper mseMapper;

	
	public List<Client> fromEntities(List<ClientEntity> ces){
		if(CollectionUtils.isEmpty(ces)) return null;
		return ces.stream().map(this::fromEntity).collect(Collectors.toList());
	}
	
	public Client fromEntity(ClientEntity ce) {
		if (ce == null)
			return null;
		Client c = new Client();
		c.setClientId(ce.getClientId());
		c.setClientName(util.fullName(ce.getFirstName(), ce.getLastName()));
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
		if(ce.getClientMse() != null) {
			c.setClientMse(mseMapper.fromEntity(ce.getClientMse()));
		}
		return c;

	}

	public ClientEntity toEntity(Client c) {
		if (c == null)
			return null;
		ClientEntity ce = new ClientEntity();
		ce.setClientId(c.getClientId());
		setClientEntityAttribs(c, ce);
		return ce;
	}

	private void setClientEntityAttribs(Client c, ClientEntity ce) {
		
		if(util.changed(ce.getFirstName(), c.getFirstName()))
			ce.setFirstName(c.getFirstName());
		if(util.changed(ce.getLastName(), c.getLastName()))
			ce.setLastName(c.getLastName());
		if(util.changed(ce.getAddress(), c.getAddress()))
			ce.setAddress(c.getAddress());
		if(util.changed(ce.getAge(), c.getAge()))
			ce.setAge(c.getAge());
		if(util.changed(ce.getStatus(), c.getStatus()))
			ce.setStatus(c.getStatus());
		if(util.changed(ce.getCity(), c.getCity()))
			ce.setCity(c.getCity());
		if(util.changed(ce.getCountry(), c.getCountry()))
			ce.setCountry(c.getCountry());
		if(util.changed(ce.getEducation(), c.getEducation()))
			ce.setEducation(c.getEducation());
		if(util.changed(ce.getEmail(), c.getEmail()))
			ce.setEmail(c.getEmail());
		if(util.changed(ce.getGender(), c.getGender()))
			ce.setGender(c.getGender());
		if(util.changed(ce.getMaritalStatus(), c.getMaritalStatus()))
			ce.setMaritalStatus(c.getMaritalStatus());
		if(util.changed(ce.getMobile(), c.getMobile()))
			ce.setMobile(c.getMobile());
		if(util.changed(ce.getPin(), c.getPin()))
			ce.setPin(c.getPin());
		if(util.changed(ce.getProfession(), c.getProfession()))
			ce.setProfession(c.getProfession());
		if(util.changed(ce.getReference(), c.getReference()))
			ce.setReference(c.getReference());
		if(util.changed(ce.getSecondPhone(), c.getSecondPhone()))
			ce.setSecondPhone(c.getSecondPhone());
		if(util.changed(ce.getState(), c.getState()))
			ce.setState(c.getState());
		if(util.changed(dateResolver.toStringDate(ce.getFollowupDate()), c.getFollowupdate()))
			ce.setFollowupDate(dateResolver.toLocalDate(c.getFollowupdate()));
	}
	
	public ClientEntity toEntity(ClientEntity ce, Client c) {
		if( ce == null || c == null) return null;
		setClientEntityAttribs(c, ce);
		return ce;
	}

}
