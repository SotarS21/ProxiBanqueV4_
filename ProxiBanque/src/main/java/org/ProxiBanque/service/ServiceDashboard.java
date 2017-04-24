package org.ProxiBanque.service;

import java.util.ArrayList;
import java.util.List;

import org.ProxiBanque.model.Footprint;
import org.ProxiBanque.model.Footprint.e_HeadType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceDashboard implements IServiceDashboard {

	private static final Logger logger = LoggerFactory.getLogger(ServiceDashboard.class);

	@Autowired
	IServiceAccount serviceAccount;

	@Autowired
	IServiceFootPrint serviceFoot;

	@Override
	public List<Footprint> getAllTransactions() {
		List<Footprint> listret = new ArrayList<>();
		List<Footprint> listFoot = serviceFoot.findAll();
		for (Footprint footprint : listFoot) {
			if (footprint.getHead().equals(e_HeadType.TRANSACTION)) {
				listret.add(footprint);
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

}
