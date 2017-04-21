package org.ProxiBanque.service;

import java.util.List;

import org.ProxiBanque.model.Client;

public interface IServiceClient {

	public Client findOne(Long id);
	public List<Client> findAll();
	public List<Client> findByConseiller_Id(Long idConseiller);
	public List<Client> findAllByLastNameAndFirstNameAllIgnoreCase(String lastName, String firstName);
	public void save(Client client);
	public void update(Client client);
	public void delete(Long id);
	
}
