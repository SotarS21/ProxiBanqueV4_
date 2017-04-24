package org.ProxiBanque.service;

import org.ProxiBanque.dao.ICRUDAgence;
import org.ProxiBanque.model.Agence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Implémentation de l'interface de service concernant le compte bancaire, elle est appeler dans les controllers addapter ou dans un webService
 * 
* @author kevin, jonas, andy, mathieu
* @version 1.0
*/

@Service
public class ServiceAgence implements IServiceAgence {


	private static final Logger logger = LoggerFactory.getLogger(ServiceAgence.class);
	
	@Autowired
	ICRUDAgence daoAgence;

	@Override
	public Agence findOne(Long id) {
		
		return daoAgence.findOne(id);
	}
	

}
