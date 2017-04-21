package org.ProxiBanque.dao;

import java.util.List;

import org.ProxiBanque.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICRUDUser extends JpaRepository<User, Long> {
	
	public List<User> findAllByLoginAndPasswordAllIgnoreCase(String login, String password);
	public List<User> findAllByLoginAllIgnoreCase(String login);
	public List<User> findAllByPasswordAllIgnoreCase(String Password);
}
