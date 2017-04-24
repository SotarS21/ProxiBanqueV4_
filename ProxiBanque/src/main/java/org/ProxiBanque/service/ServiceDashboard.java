package org.ProxiBanque.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ProxiBanque.model.Footprint;
import org.ProxiBanque.model.Footprint.e_HeadType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Implémentation de l'interface de service concernant le dashBorad, elle est
 * appeler dans les controllers addapter ou dans un webService
 * 
 * @author kevin, jonas, andy, mathieu
 * @version 1.0
 */

@Service
public class ServiceDashboard implements IServiceDashboard {

	private static final Logger logger = LoggerFactory.getLogger(ServiceDashboard.class);

	@Autowired
	IServiceAccount serviceAccount;
	
	@Autowired
	IServiceClient serviceClient;

	@Autowired
	IServiceFootPrint serviceFoot;

	@Override
	public List<String> getAllTransactions() {
		List<String> listret = new ArrayList<>();
		List<Footprint> listFoot = serviceFoot.findAll();
		for (Footprint footprint : listFoot) {
			if (footprint.getHead().equals(e_HeadType.TRANSACTION)) {
				listret.add(footprint.toString());
			}
		}
		return listret;
	}
	

	@Override
	public int numberComptesOk() {
		logger.debug("test nbre comptes ok");
		int i = serviceAccount.listAccounts().size() - serviceAccount.findAllClientOverdrawn().size();
		return i;
	}

	@Override
	public int numberComptesNOk() {
		logger.debug("test nbre comptes nok");
		return serviceAccount.findAllClientOverdrawn().size();
	}
	
	@Override
	public int numberComptesOkAdvisor(Long id) {
		logger.debug("test nbre comptes ok");
		int i = serviceClient.findByConseiller_Id(id).size() - serviceAccount.findByAdvisorClientOverdrawn(id).size();
		return i;
	}

	@Override
	public int numberComptesNOkAdvisor(Long id) {
		logger.debug("test nbre comptes nok");
		return serviceAccount.findByAdvisorClientOverdrawn(id).size();
	}

	@Override
	public Map<String, Number> getPieMapDirector() {

		Map<String, Number> map = new HashMap<String, Number>();
		
		map.put("Sain", numberComptesOk());
		map.put("A Decouvert", numberComptesNOk());
		
		return map;
	}
	
	@Override
	public Map<String, Number> getPieMapAdvisor(long id) {
		
		Map<String, Number> map = new HashMap<String, Number>();
		
		map.put("Sain", numberComptesOkAdvisor(id));
		map.put("A Decouvert", numberComptesNOkAdvisor(id));
		
		return map;
	}


	
}
