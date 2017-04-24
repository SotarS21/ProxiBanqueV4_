package org.ProxiBanque.dao;

import java.util.List;

import org.ProxiBanque.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface permettant la communication avec la base de donn�e
 * Permet la sauvegarde des clients
 * 
 * @author K�vin, Jonas, Andy, Mathieu
 * @version 1.0
 */
public interface ICRUDClient extends JpaRepository<Client, Long> {

	/**
	 * @param idConseiller identifiant du conseiller
	 * @return liste de clients
	 */
	public List<Client> findByAdvisor_Id(Long idConseiller);
	
	/**
	 * @param lastName
	 * @param firstName
	 * @return liste de clients
	 */
	public List<Client> findAllByLastNameAndFirstNameAllIgnoreCase(String lastName, String firstName);
}
