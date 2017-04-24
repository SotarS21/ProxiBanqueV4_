package org.ProxiBanque.service;

import java.util.List;
import java.util.Map;

public interface IServiceDashboard {

	public int numberComptesOk();
	public int numberComptesNOk();
	public List<String> getAllTransactions();
	public Map<String, Number> getPieMapDirector();
	public Map<String, Number> getPieMapAdvisor(long id);
	int numberComptesOkAdvisor(Long id);
	int numberComptesNOkAdvisor(Long id);

}
