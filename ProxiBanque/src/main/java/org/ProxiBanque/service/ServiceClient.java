package org.ProxiBanque.service;

import java.util.List;

import org.ProxiBanque.dao.ICRUDClient;
import org.ProxiBanque.model.Client;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceClient implements IServiceClient {

	@Autowired
	private ICRUDClient daoClient;
	
	@Override
	public Client findOne(Long id) {
		// TODO Auto-generated method stub
		return daoClient.findOne(id);
	}

	@Override
	public List<Client> findAll() {
		// TODO Auto-generated method stub
		return daoClient.findAll();
	}

	@Override
	public List<Client> findByConseiller_Id(Long idConseiller) {
		// TODO Auto-generated method stub
		return daoClient.findByConseiller_Id(idConseiller);
	}

	@Override
	public List<Client> findByLastNameAndFirstNameAllIgnoreCase(String lastName, String firstName) {
		// TODO Auto-generated method stub
		return daoClient.findByLastNameAndFirstNameAllIgnoreCase(lastName, firstName);
	}

	@Override
	public void save(Client client) {
		// TODO Auto-generated method stub
		daoClient.save(client);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		daoClient.delete(id);
	}

}
