package org.ProxiBanque.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.ProxiBanque.config.ApplicationConfig;
import org.ProxiBanque.model.Address;
import org.ProxiBanque.model.Advisor;
import org.ProxiBanque.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class AdvisorTestService {

	@Autowired
	IServiceAdvisor service;

//	@Test
//	public void testAdvisorService() {
//		assertNotNull(service);
//	}
//
//	@Test
//	public void testAddAdvisor() {
//		Advisor advisor = new Advisor("Jean", "Valjean", new Address("10", "75010", "Paris"), new User("jean", "jean"));
//
//		service.save(advisor);
//		List<Advisor> advisors = service.findByLastNameAndFirstNameAllIgnoreCase("jean", null);
//
//		assertTrue(advisors.size() >= 1);
//	}
//
//	@Test
//	public void testListFindByName() {
//		Advisor advisor = new Advisor("Jean", "Valjean", new Address("10", "75010", "Paris"), new User("jean", "jean"));
//
//		service.save(advisor);
//		List<Advisor> advisors = service.findByLastNameAndFirstNameAllIgnoreCase("jean", null);
//	}
}
