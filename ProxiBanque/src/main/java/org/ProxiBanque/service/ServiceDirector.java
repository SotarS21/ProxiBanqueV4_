package org.ProxiBanque.service;

import java.util.List;

import org.ProxiBanque.dao.ICRUDDirector;
import org.ProxiBanque.model.Director;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;

@Service
public class ServiceDirector implements IServiceDirector{

	private final static Logger logger = (Logger) LoggerFactory.getLogger(ServiceClient.class);
	
	@Autowired
	ICRUDDirector daoDirector;
	
	@Override
	public Director findOne(Long id) {
		logger.debug("call findOne on serviceDirector");
		return daoDirector.findOne(id);
	}

	@Override
	public List<Director> findAll() {
		logger.debug("call findall on serviceDirector");
		return daoDirector.findAll();
	}

	@Override
	public void save(Director dir) {
		logger.debug("save director : "+dir);
		daoDirector.save(dir);
		
	}

	@Override
	public void update(Director dir) {
		logger.debug("update director : "+dir);
		daoDirector.save(dir);
		
	}

	@Override
	public void delete(Long id) {
		logger.debug("delete director id : "+id);
		daoDirector.delete(id);
		
	}

}
