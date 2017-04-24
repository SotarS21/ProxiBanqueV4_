package org.ProxiBanque.dao;

import org.ProxiBanque.model.Footprint;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface permettant la communication avec la base de donn�e
 * Permet la sauvegarde des actions effectu�es
 * exemple : transactions
 * 
 * @author K�vin, Jonas, Andy, Mathieu
 * @version 1.0
 */
public interface ICRUDFootPrint extends JpaRepository<Footprint, Long>{

}
