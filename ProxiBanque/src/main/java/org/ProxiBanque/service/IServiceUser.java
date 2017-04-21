package org.ProxiBanque.service;

import java.util.List;

import org.ProxiBanque.model.User;

public interface IServiceUser {

	public void save(User user);
	public void update(User user);
	public User findOne(Long id);
	public List<User> findAll();
	public void delete(Long id);
	public List<User> findAllByLoginAndPasswordAllIgnoreCase(String login, String password);
	public List<User> findAllByLoginAllIgnoreCase(String login);
	public List<User> findAllByPasswordAllIgnoreCase(String Password);
}
