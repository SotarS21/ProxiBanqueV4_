package org.ProxiBanque.service;

import java.util.List;

import org.ProxiBanque.model.Footprint;

public interface IServiceDashboard {

	public int numberComptesOk();
	public int numberComptesNOk();
	public List<Footprint> getAllTransactions();
}
