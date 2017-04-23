package org.ProxiBanque.dao;

import org.ProxiBanque.model.Agence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICRUDAgence extends JpaRepository<Agence, Long>{
	
}
