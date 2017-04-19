package org.ProxiBanque.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;

import org.ProxiBanque.model.User;
import org.ProxiBanque.service.IServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SessionScoped
public class UserController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1305307344437852575L;
	
	@Autowired
	private IServiceUser service;
	
	private User user;

	@PostConstruct
	public void init() {
		
		service.save(new User());
		this.user = service.find(1L);
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
