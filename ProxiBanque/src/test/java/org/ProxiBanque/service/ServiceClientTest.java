package org.ProxiBanque.service;

import static org.junit.Assert.*;

import org.ProxiBanque.config.ApplicationConfig;
import org.ProxiBanque.model.Address;
import org.ProxiBanque.model.Advisor;
import org.ProxiBanque.model.Agence;
import org.ProxiBanque.model.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class ServiceClientTest {

	@Autowired
	private IServiceClient serviceClient;
	
	@Autowired
	private IServiceAdvisor serviceAdvisor;
	
	@Test
	public void testNotNull() {
		
		assertTrue(serviceClient != null);
	}
	
	@Test
	public void testDeleteClient() {
		
		Agence agence = new Agence();
		Advisor advisor = new Advisor("lol", "lol", new Address("lol", "lol", "lol"));
		Client client = new Client("lol", "lol", new Address("lol", "lol", "lol"));
		advisor.addClient(client);
		agence.addAdvisor(advisor);
		
		serviceAdvisor.save(advisor);
		
		long id = client.getId();
		
		Client client2 = serviceClient.findOne(id);
		
		assertTrue(client.getId() == client2.getId());
		
		serviceClient.delete(id);
		
		client2 = serviceClient.findOne(id);
		
		assertTrue(client2 == null);
	}

}
