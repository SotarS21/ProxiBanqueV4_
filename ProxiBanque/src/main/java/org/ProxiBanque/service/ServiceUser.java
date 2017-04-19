package org.ProxiBanque.service;

import java.util.List;

import org.ProxiBanque.dao.ICRUDUser;
import org.ProxiBanque.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceUser implements IServiceUser {
	
	@Autowired
	private ICRUDUser dao;
	
	@Override
	public void save(User user) {
		
		dao.save(user);
	}

	@Override
	public User find(Long idUser) {

		return dao.findOne(idUser);
	}

	@Override
	public List<User> findAll() {
		
		return dao.findAll();
	}

	@Override
	public void delete(Long idUser) {

		dao.delete(idUser);
	}

}
