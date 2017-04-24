package org.ProxiBanque.service;
/**
 * Implémentation de l'interface de service concernant le directeur, elle est
 * appeler dans les controllers addapter ou dans un webService
 * 
 * @author kevin, jonas, andy, mathieu
 * @version 1.0
 */
import java.util.List;

import javax.transaction.Transactional;

import org.ProxiBanque.dao.ICRUDDirector;
import org.ProxiBanque.model.Director;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;

@Service
@Transactional
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
