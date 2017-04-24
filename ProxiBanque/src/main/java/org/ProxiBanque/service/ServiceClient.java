package org.ProxiBanque.service;

import java.util.List;

import javax.transaction.Transactional;

import org.ProxiBanque.dao.ICRUDClient;
import org.ProxiBanque.model.Client;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;

/**
 * Implémentation de l'interface de service concernant le client, elle est
 * appeler dans les controllers addapter ou dans un webService
 * 
 * @author kevin, jonas, andy, mathieu
 * @version 1.0
 */

@Service
@Transactional
public class ServiceClient implements IServiceClient {

	@Autowired
	private ICRUDClient daoClient;
	private final static Logger logger = (Logger) LoggerFactory.getLogger(ServiceClient.class);

	@Override
	public Client findOne(Long id) {
		logger.debug("call findOne on serviceClient");
		return daoClient.findOne(id);
	}

	@Override
	public List<Client> findAll() {
		logger.debug("call findAll on serviceClient");
		List<Client> test = daoClient.findAll();
		logger.debug("test" + test.size());
		return test;
	}

	@Override
	public List<Client> findByConseiller_Id(Long idConseiller) {
		logger.debug("call findByConseiller_Id on serviceClient");
		return daoClient.findByAdvisor_Id(idConseiller);
	}

	@Override
	public List<Client> findAllByLastNameAndFirstNameAllIgnoreCase(String lastName, String firstName) {
		logger.debug("call findByLastNameAndFirstNameAllIgnoreCase on serviceClient");
		return daoClient.findAllByLastNameAndFirstNameAllIgnoreCase(lastName, firstName);
	}

	@Override
	public void save(Client client) {
		logger.debug("call save on serviceClient");
		daoClient.save(client);
	}

	@Override
	public void delete(Long id) {
		logger.debug("call delete on serviceClient " + id);
		Client client = daoClient.findOne(id);
		client.setAdvisor(null);

		daoClient.save(client);
		daoClient.delete(client.getId());
	}

	@Override
	public void update(Client client) {
		logger.debug("call update on serviceClient");
		if (!client.equals(null)) {

			logger.debug("update performed");
			daoClient.save(client);
		}
	}

	@Override
	public void init() {
		logger.debug("entered init");

	}

}
