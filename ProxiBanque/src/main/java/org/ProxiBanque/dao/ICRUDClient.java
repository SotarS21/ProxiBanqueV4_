package org.ProxiBanque.dao;

import org.ProxiBanque.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICRUDClient extends JpaRepository<Client, Long> {

}
