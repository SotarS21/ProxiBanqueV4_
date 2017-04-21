package org.ProxiBanque.service;

import static org.junit.Assert.*;

import java.util.List;

import org.ProxiBanque.config.ApplicationConfig;
import org.ProxiBanque.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class ServiceUserTest {

	@Autowired
	IServiceUser serviceUser;
	
	@Test
	public void testServiceNotNull() {
		
		assertNotNull(serviceUser);
	}
	
	@Test
	public void testFindOne() {
		
		User user = new User("log", "mdp");
		serviceUser.save(user);
		User user2 = serviceUser.findOne(user.getId());
		assertTrue(user2.equals(user));
	}
	
	@Test
	public void testFindAll() {
		
		User user = new User("log", "mdp");
		serviceUser.save(user);
		User user2 = new User("log2", "mdp2");
		serviceUser.save(user2);
		
		List<User> list = serviceUser.findAll();
		assertTrue(list.size() != 0);
	}

	@Test
	public void testDelete() {
		
		User user = new User("log", "mdp");
		serviceUser.save(user);
		serviceUser.delete(user.getId());
		
		assertTrue(serviceUser.findOne(user.getId()) == null);
	}
	
	@Test
	public void testFindAllByLoginAndPasswordAllIgnoreCase() {
		
		User user = new User("log", "mdp");
		serviceUser.save(user);
		User user2 = new User("log2", "mdp2");
		serviceUser.save(user2);
		
		List<User> list = serviceUser.findAllByLoginAndPasswordAllIgnoreCase("LOG2", "MDP2");
		assertTrue(list.size() != 0);
	}
}
