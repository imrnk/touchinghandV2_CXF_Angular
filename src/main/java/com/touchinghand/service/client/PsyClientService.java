package com.touchinghand.service.client;

import java.util.List;

import com.touchinghand.dto.Client;

public interface PsyClientService {

	public List<Client> findAllClients();
	
	public List<Client> findActiveClients();
	
	public Client findClientById(int id);
	
	public boolean addClient(Client client);
	
	public Client findClientByName(String name);
}
