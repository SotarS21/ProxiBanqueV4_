package org.ProxiBanque.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.ProxiBanque.config.ApplicationConfig;
import org.ProxiBanque.model.Address;
import org.ProxiBanque.model.Advisor;
import org.ProxiBanque.model.Agence;
import org.ProxiBanque.model.Client;
import org.ProxiBanque.model.CurrentAccount;
import org.ProxiBanque.model.User;
import org.ProxiBanque.service.IServiceAdvisor;
import org.ProxiBanque.service.IServiceClient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class ICRUDClientTest {

	@Autowired
	IServiceClient serviceClient;
	@Autowired
	IServiceAdvisor serviceAdvisor;

	@Autowired
	ICRUDClient daoClient;
	@Test
	public void testServiceClientNotNull() {

		assertNotNull(serviceClient);
	}

//	@Test
//	public void testFindOne() {
//
//		Client client = new Client("Jo", "Bar", new Address());
//		serviceClient.save(client);
//
//		Client client2 = serviceClient.findOne(client.getId());
//		assertEquals(client2.getFirstName(), "Jo");
//		assertEquals(client2.getLastName(), "Bar");
//	}
//	
//	@Test
//	public void testFindAll() {
//
//		Client client = new Client("Jo", "Bar", new Address());
//		serviceClient.save(client);
//		List<Client> list = serviceClient.findAll();
//		assertTrue(list.size() != 0);
//	}

	@Test
	public void testAdd() {

		Client client = new Client("M", "B", new Address());
		Advisor advisor = new Advisor("Jean", "PATRIC", null, new User("admin", "admin"));
		advisor.addClient(client);
		client.setAdvisor(advisor);
		//client.setAgence(new Agence());
		client.setCurrentAccount(new CurrentAccount());
		
		serviceAdvisor.save(advisor);
		daoClient.save(client);
		
		
		
		assertTrue(serviceClient.findOne(client.getId()) != null);
	}
	
//	@Test
//	public void testDelete() {
//
//		Client client1 = serviceClient.findOne(3L);
//		client1.setAdvisor(null);
//		
//		serviceClient.save(client1);
//		//serviceClient.delete(client1.getId());
//		
//		
//		assertTrue(serviceClient.findOne(client1.getId()) == null);
//	}
	
//	@Test
//	public void testfindByLastNameAndFirstNameAllIgnoreCase() {
//
//		Client client = new Client("Jo", "Bar", new Address());
//		serviceClient.save(client);
//		List<Client> list = serviceClient.findAllByLastNameAndFirstNameAllIgnoreCase("Bar", "Jo");
//		assertTrue(list.get(0).getFirstName().equals("Jo"));
//	}
//	
//	@Test
//	public void testClientWithCurrentAccount() {
//		
//		Client client = new Client("lol", "lol", new Address());
//		CurrentAccount account = new CurrentAccount();
//		client.setCurrentAccount(account);
//		serviceClient.save(client);
//		Client client2 = serviceClient.findOne(client.getId());
//		assertTrue(client2.getCurrentAccount() != null);
//		
//		client2.getCurrentAccount().setSold(2000d);
//		serviceClient.update(client2);
//		Client client3 = serviceClient.findOne(client2.getId());
//		assertTrue(client3.getCurrentAccount().getSold() == 2000d);
//	}
}
