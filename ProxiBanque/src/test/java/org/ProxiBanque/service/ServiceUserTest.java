package org.ProxiBanque.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.ProxiBanque.config.ApplicationConfig;
import org.ProxiBanque.model.Address;
import org.ProxiBanque.model.Advisor;
import org.ProxiBanque.model.Agence;
import org.ProxiBanque.model.Client;
import org.ProxiBanque.model.CurrentAccount;
import org.ProxiBanque.model.Director;
import org.ProxiBanque.model.SavingAccount;
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
	
	@Autowired
	IServiceAdvisor serviceAdvisor;
	
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
	public void testFindFirstByLoginAndPasswordAllIgnoreCase() {
		
		User user = new User("log", "mdp");
		serviceUser.save(user);
		User user2 = new User("log2", "mdp2");
		serviceUser.save(user2);
		
		User user3 = serviceUser.findFirstByLoginAndPasswordAllIgnoreCase("LOG2", "MDP2");
		assertTrue(user3 != null);
	}
	
	@Test
	public void testChainSavingfromAdvisor() {
		
		Agence agence = new Agence();
		Director director = new Director("Bib", "Bib", new  Address());
		User user = new User("login", "pass");
		director.setUser(user);
		Advisor advisor = new Advisor("Bob", "Bob", new Address());
		advisor.setUser(user);
		
		CurrentAccount curr = new CurrentAccount();
		SavingAccount save = new SavingAccount();
		Client client = new Client("BouB", "BouB", new Address());
		client.setCurrentAccount(curr);
		client.setSafeAccount(save);
		
		
		agence.addAdvisor(advisor);
		agence.setDirector(director);
		advisor.addClient(client);
		
		System.out.println(client.getAgence());
		System.out.println(client.getAdvisor());
		System.out.println(director.getAgence());
		System.out.println(advisor.getClients());
		System.out.println(advisor.getAgence());
		System.out.println(agence.getAdvisors());
		System.out.println(agence.getClients());
		System.out.println(agence.getDirector());
		
		
		serviceAdvisor.save(advisor);
		System.out.println(serviceAdvisor.findOne(advisor.getId()).getClients().get(0));
		
		client.setLastName("test");
		client.setAddress(new Address("10 rue lila", "92810", "Sarcelles"));
		System.out.println(client);
		
		
		serviceAdvisor.update(advisor);
		System.out.println(serviceAdvisor.findOne(advisor.getId()).getClients().get(0));
		
		
	}
	
}
