package org.ProxiBanque.dao;

import java.util.List;

import org.ProxiBanque.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICRUDClient extends JpaRepository<Client, Long> {

	public List<Client> findByConseiller_Id(Long idConseiller);
}
