package org.ProxiBanque.service;

import java.util.List;

import org.ProxiBanque.dao.ICRUDAdvisor;
import org.ProxiBanque.model.Advisor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceAdvisor implements IServiceAdvisor {


	private static final Logger logger = LoggerFactory.getLogger(ServiceAdvisor.class);
	
	@Autowired
	ICRUDAdvisor daoAdvisor;
	
	@Override
	public Advisor findOne(Long id) {
		logger.debug("Call advisor fidOne: id "+id);
		return daoAdvisor.findOne(id);
	}

	@Override
	public List<Advisor> findAll() {

		logger.debug("Call advisor findAll()");
		return daoAdvisor.findAll();
	}

	@Override
	public List<Advisor> findByLastNameAndFirstNameAllIgnoreCase(String lastName, String firstName) {

		logger.debug("Call advisor  findByLastNameAndFirstNameAllIgnoreCase() last name : "+lastName+"| first name :"+firstName);
		return daoAdvisor.findByLastNameAndFirstNameAllIgnoreCase(lastName, firstName);
	}

	@Override
	public void save(Advisor ad) {
		logger.debug("Call advisor save () : client = "+ad); 
		daoAdvisor.save(ad);
	}

	@Override
	public void update(Advisor ad) {
		logger.debug("Call advisor service update() : advisor = "+ad); 
		daoAdvisor.save(ad);
	}

	@Override
	public void delete(Long id) {
		logger.debug("Call advisor service update() : id "+id); 
		daoAdvisor.delete(id);;
	}

}
