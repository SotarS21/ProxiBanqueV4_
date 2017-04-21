package org.ProxiBanque.service;

import java.util.List;

import org.ProxiBanque.dao.ICRUDUser;
import org.ProxiBanque.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceUser implements IServiceUser {

	@Autowired
	ICRUDUser daoUser;
	
	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		daoUser.save(user);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		if(user != null) {
			
			daoUser.save(user);
		}
	}

	@Override
	public User findOne(Long id) {
		// TODO Auto-generated method stub
		return daoUser.findOne(id);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return daoUser.findAll();
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		daoUser.delete(id);
	}

	@Override
	public List<User> findAllByLoginAndPasswordAllIgnoreCase(String login, String password) {
		// TODO Auto-generated method stub
		return daoUser.findAllByLoginAndPasswordAllIgnoreCase(login, password);
	}

	@Override
	public List<User> findAllByLoginAllIgnoreCase(String login) {
		// TODO Auto-generated method stub
		return daoUser.findAllByLoginAllIgnoreCase(login);
	}

	@Override
	public List<User> findAllByPasswordAllIgnoreCase(String Password) {
		// TODO Auto-generated method stub
		return daoUser.findAllByPasswordAllIgnoreCase(Password);
	}

}
