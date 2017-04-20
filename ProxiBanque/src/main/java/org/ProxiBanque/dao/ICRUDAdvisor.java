package org.ProxiBanque.dao;

import java.util.List;

import org.ProxiBanque.model.Advisor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICRUDAdvisor extends JpaRepository<Advisor, Long>{

	public List<Advisor> findByLastNameAndFirstNameAllIgnoreCase(String lastName, String firstName);

}
