package org.ProxiBanque.dao;

import java.util.List;

import org.ProxiBanque.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ICRUDClient extends JpaRepository<Client, Long> {

	public List<Client> findByAdvisor_Id(Long idConseiller);
	public List<Client> findByLastNameAndFirstNameAllIgnoreCase(String lastName, String firstName);
}
