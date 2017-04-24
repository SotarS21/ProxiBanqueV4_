package org.ProxiBanque.dao;

import org.ProxiBanque.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface permettant la communication avec la base de donnée
 * Permet la sauvegarde des directeurs
 * 
 * @author Kévin, Jonas, Andy, Mathieu
 * @version 1.0
 */
public interface ICRUDDirector extends JpaRepository<Director, Long>{
	
}
