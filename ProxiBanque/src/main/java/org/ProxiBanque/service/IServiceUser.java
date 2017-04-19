package org.ProxiBanque.service;

import java.util.List;

import org.ProxiBanque.model.User;

public interface IServiceUser {

	public void save(User user);
	public User find(Long idUser);
	public List<User> findAll();
	public void delete(Long idUser);
}
