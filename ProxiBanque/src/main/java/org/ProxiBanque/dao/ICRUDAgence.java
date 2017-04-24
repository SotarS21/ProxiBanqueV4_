package org.ProxiBanque.dao;

import org.ProxiBanque.model.Agence;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface permettant la communication avec la base de donn�e
 * Permet la sauvegarde des agences
 * 
 * @author K�vin, Jonas, Andy, Mathieu
 * @version 1.0
 */
public interface ICRUDAgence extends JpaRepository<Agence, Long>{
	
}
