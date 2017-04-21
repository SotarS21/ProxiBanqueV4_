package org.ProxiBanque.dao;

import org.ProxiBanque.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICRUDDirector extends JpaRepository<Director, Long>{
	
}
