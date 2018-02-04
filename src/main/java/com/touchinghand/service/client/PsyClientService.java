package com.touchinghand.service.client;

import java.util.List;

import com.touchinghand.dto.Client;
import com.touchinghand.dto.ClientMse;

public interface PsyClientService {

	public List<Client> findAllClients();
	
	public List<Client> findActiveClients();
	
	public Client findClientById(int id);
	
	public boolean addClient(Client client);
	
	public boolean updateClient(int clientId, Client client);
	
	public Client findClientByName(String fname, String lname);
	
	public boolean addClientMse(int clientId, ClientMse clientMse);
	
	public ClientMse getClientMse(int clientId);
	
	public boolean updateClientMse(int clientMseId, ClientMse clientMse);
}
