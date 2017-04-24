package org.ProxiBanque.service;

import java.util.List;
import java.util.Map;
/**
 * Interface de service concernant le dashBorad, elle est appeler dans les
 * controllers addapter ou dans un webService .
 * 
 * 
 * @author kevin, jonas, andy, mathieu
 * @version 1.0
 */
public interface IServiceDashboard {

	/**
	 * 
	 * @return le nombre de comppte bancaire qui ne sont pas à découvert 
	 */
	public int numberComptesOk();
	/**
	 * 
	 * @return le nombre de compte bancaire à découvert
	 */
	public int numberComptesNOk();
	
	/**
	 * 
	 * @return 	la liste des transactions disponibles dans la base de données 
	 */
	public List<String> getAllTransactions();
	/**
	 * 
	 * @return une map de légende et le pourcentage associer 
	 */
	public Map<String, Number> getPieMapDirector();
	/**
	 * 
	 * @param id identifiant du conseiller
	 * @return une map de légende et le pourcentage associer 
	 */
	public Map<String, Number> getPieMapAdvisor(long id);
	/**
	 * 
	 * @param id identification du consiller
	 * @return le nombre de compte pas à découvert avec le conseiller d'id : id
	 */
	int numberComptesOkAdvisor(Long id);
	/**
	 * 
	 * @param id identifiant du conseiller
	 * @return le nombre de compte à découvert avec le conseiller d'id : id
	 */
	int numberComptesNOkAdvisor(Long id);

}
