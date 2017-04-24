package org.ProxiBanque.service;

import java.util.List;

import org.ProxiBanque.model.Advisor;
 /**
  * Interface de service concernant le conseiller, elle est appeler dans les controllers addapter ou dans un webService
  * 
 * @author kevin, jonas, andy, mathieu
 * @version 1.0
 */
public interface IServiceAdvisor {

	/**
	 * 
	 * @param id  l'identifiant du conseiller
	 * @return un conseiller
	 */
	public Advisor findOne(Long id);
	/**
	 * 
	 * @return une liste de conseiller
	 */
	public List<Advisor> findAll();
	/**
	 * Appel la méthode de la base de données pour récupèrer tous les conseiller de prénom : lastName et de nom : firstName
	 * 
	 * @param lastName prenom
	 * @param firstName nom
	 * @return la liste des conseiller trouvés
	 */
	public List<Advisor> findByLastNameAndFirstNameAllIgnoreCase(String lastName, String firstName);
	
	/**
	 * Appel la méthode de persistance de la base de données
	 * 
	 * @param advisor le conseiller
	 */
	public void save(Advisor advisor);
	/**
	 * Appel la méthode de persistance de la base de données
	 * 
	 * @param advisor le conseiller
	 */
	public void update(Advisor advisor);
	
	/**
	 * 
	 * @param id l'identifiant du conseiller
	 */
	public void delete(Long id);
	
}
