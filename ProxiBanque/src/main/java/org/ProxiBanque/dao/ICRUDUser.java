package org.ProxiBanque.dao;

import org.ProxiBanque.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICRUDUser extends JpaRepository<User, Long> {

}
