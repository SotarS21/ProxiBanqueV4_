package org.ProxiBanque.service;

import java.util.List;

import javax.transaction.Transactional;

import org.ProxiBanque.dao.ICRUDClient;
import org.ProxiBanque.model.Client;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;

@Service
@Transactional
public class ServiceClient implements IServiceClient {

	@Autowired
	private ICRUDClient daoClient;
	private final static Logger logger = (Logger) LoggerFactory.getLogger(ServiceClient.class);
	
	@Override
	public Client findOne(Long id) {
		// TODO Auto-generated method stub
		logger.debug("call findOne on serviceClient");
		return daoClient.findOne(id);
	}

	@Override
	public List<Client> findAll() {
		// TODO Auto-generated method stub
		logger.debug("call findAll on serviceClient");
		List<Client> test =  daoClient.findAll();
		logger.debug("test" + test.size());
		return test;
	}

	@Override
	public List<Client> findByConseiller_Id(Long idConseiller) {
		// TODO Auto-generated method stub
		logger.debug("call findByConseiller_Id on serviceClient");
		return daoClient.findByAdvisor_Id(idConseiller);
	}

	@Override
	public List<Client> findAllByLastNameAndFirstNameAllIgnoreCase(String lastName, String firstName) {
		// TODO Auto-generated method stub
		logger.debug("call findByLastNameAndFirstNameAllIgnoreCase on serviceClient");
		return daoClient.findAllByLastNameAndFirstNameAllIgnoreCase(lastName, firstName);
	}

	@Override
	public void save(Client client) {
		// TODO Auto-generated method stub
		logger.debug("call save on serviceClient");
		daoClient.save(client);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		logger.debug("call delete on serviceClient");
		daoClient.delete(id);
	}

	@Override
	public void update(Client client) {
		// TODO Auto-generated method stub
		logger.debug("call update on serviceClient");
		if(! client.equals(null)) {
			
			logger.debug("update performed");
			daoClient.save(client);
		}
	}

	@Override
	public void init() {
		logger.debug("entered init");
		
	}
	
	

}
