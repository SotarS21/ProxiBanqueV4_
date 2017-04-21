package org.ProxiBanque.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.ProxiBanque.config.ApplicationConfig;
import org.ProxiBanque.model.Address;
import org.ProxiBanque.model.Client;
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

	@Test
	public void testServiceClientNotNull() {

		assertNotNull(serviceClient);
	}

	@Test
	public void testFindOne() {

		Client client = new Client("Jo", "Bar", new Address());
		serviceClient.save(client);

		Client client2 = serviceClient.findOne(client.getId());
		assertEquals(client2.getFirstName(), "Jo");
		assertEquals(client2.getLastName(), "Bar");
	}
	
	@Test
	public void testFindAll() {

		Client client = new Client("Jo", "Bar", new Address());
		serviceClient.save(client);
		List<Client> list = serviceClient.findAll();
		assertTrue(list.size() != 0);
	}

	@Test
	public void testDelete() {

		Client client = new Client("M", "B", new Address());
		serviceClient.save(client);
		serviceClient.delete(client.getId());
		assertTrue(serviceClient.findOne(client.getId()) == null);
	}

	@Test
	public void testfindByLastNameAndFirstNameAllIgnoreCase() {

		Client client = new Client("Jo", "Bar", new Address());
		serviceClient.save(client);
		List<Client> list = serviceClient.findAllByLastNameAndFirstNameAllIgnoreCase("Bar", "Jo");
		assertTrue(list.get(0).getFirstName().equals("Jo"));
	}
}
