package org.ProxiBanque.wsrest.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.Response;

import org.ProxiBanque.model.User;
import org.ProxiBanque.service.IServiceUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@Component
public class ServiceUserWSRest implements IServiceUserWSRest {

	private static final Logger logger = LoggerFactory.getLogger(ServiceUserWSRest.class);

	@Autowired
	IServiceUser serviceUser;

	@PostConstruct
	public void init() {

		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	public Response save(User user) {
		// TODO Auto-generated method stub
		if (user != null) {

			serviceUser.save(user);
			return Response.ok().build();
		} else {

			return Response.notModified("user is null").build();
		}
	}

	@Override
	public Response update(User user) {
		// TODO Auto-generated method stub
		if (user != null) {

			serviceUser.save(user);
			return Response.ok().build();
		} else {

			return Response.notModified("user is null").build();
		}
	}

	@Override
	public User findOne(String id) {
		// TODO Auto-generated method stub
		logger.debug("entered findOne()");
		return serviceUser.findOne(Long.valueOf(id));
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return serviceUser.findAll();
	}

	@Override
	public Response delete(String id) {
		// TODO Auto-generated method stub
		serviceUser.delete(Long.valueOf(id));
		if(serviceUser.findOne(Long.valueOf(id)) == null) {
			
			return Response.ok().build();
		} else {
			
			
			return Response.notModified("object does not exist or delete operation failed").build();
		}
	}

	@Override
	public User findFirstByLoginAndPasswordAllIgnoreCase(String login, String password) {
		// TODO Auto-generated method stub
		return serviceUser.findFirstByLoginAndPasswordAllIgnoreCase(login, password);
	}

	@Override
	public List<User> findAllByLoginAllIgnoreCase(String login) {
		// TODO Auto-generated method stub
		return serviceUser.findAllByLoginAllIgnoreCase(login);
	}

	@Override
	public List<User> findAllByPasswordAllIgnoreCase(String Password) {
		// TODO Auto-generated method stub
		return serviceUser.findAllByPasswordAllIgnoreCase(Password);
	}

}
