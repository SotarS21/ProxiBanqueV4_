package org.ProxiBanque.dao;

import org.ProxiBanque.model.Footprint;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface permettant la communication avec la base de donnée
 * Permet la sauvegarde des actions effectuées
 * exemple : transactions
 * 
 * @author Kévin, Jonas, Andy, Mathieu
 * @version 1.0
 */
public interface ICRUDFootPrint extends JpaRepository<Footprint, Long>{

}
