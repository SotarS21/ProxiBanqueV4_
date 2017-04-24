package org.ProxiBanque.dao;

import java.util.List;

import org.ProxiBanque.model.Advisor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface permettant la communication avec la base de donnée
 * Permet la sauvegarde des conseillers
 * 
 * @author Kévin, Jonas, Andy, Mathieu
 * @version 1.0
 */
public interface ICRUDAdvisor extends JpaRepository<Advisor, Long>{

	/**
	 * @param lastName
	 * @param firstName
	 * @return liste de conseillers
	 */
	public List<Advisor> findByLastNameAndFirstNameAllIgnoreCase(String lastName, String firstName);

}
