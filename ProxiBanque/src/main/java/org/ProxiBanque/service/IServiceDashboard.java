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
	 * @return le nombre de comppte bancaire qui ne sont pas � d�couvert 
	 */
	public int numberComptesOk();
	/**
	 * 
	 * @return le nombre de compte bancaire � d�couvert
	 */
	public int numberComptesNOk();
	
	/**
	 * 
	 * @return 	la liste des transactions disponibles dans la base de donn�es 
	 */
	public List<String> getAllTransactions();
	/**
	 * 
	 * @return une map de l�gende et le pourcentage associer 
	 */
	public Map<String, Number> getPieMapDirector();
	/**
	 * 
	 * @param id identifiant du conseiller
	 * @return une map de l�gende et le pourcentage associer 
	 */
	public Map<String, Number> getPieMapAdvisor(long id);
	/**
	 * 
	 * @param id identification du consiller
	 * @return le nombre de compte pas � d�couvert avec le conseiller d'id : id
	 */
	int numberComptesOkAdvisor(Long id);
	/**
	 * 
	 * @param id identifiant du conseiller
	 * @return le nombre de compte � d�couvert avec le conseiller d'id : id
	 */
	int numberComptesNOkAdvisor(Long id);

}
