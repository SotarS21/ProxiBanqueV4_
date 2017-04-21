package org.ProxiBanque.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.ProxiBanque.config.ApplicationConfig;
import org.ProxiBanque.model.Address;
import org.ProxiBanque.model.Advisor;
import org.ProxiBanque.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class AdvisorTestService {

	@Autowired
	IServiceAdvisor service;
	

	private static final Logger logger = LoggerFactory.getLogger(AdvisorTestService.class);


	@Test
	public void testAdvisorService() {
		assertNotNull(service);
	}

	@Test
	public void testAddAdvisor() {
		Advisor advisor = new Advisor("Jean", "Valjean", new Address("10", "75010", "Paris"), new User("jean", "jean"));

		advisor.setCellphone("09784556");
		advisor.setEmail("jean.valjean@gmail.com");
		
		int presize = service.findAll().size();
		service.save(advisor);
		int postSize = service.findAll().size();
		assertTrue(presize < postSize);
	}

	@Test
	public void testListFindByName() {
		Advisor advisor = new Advisor("Jean", "Valjean", new Address("10", "75010", "Paris"), new User("jean", "jean"));

		advisor.setCellphone("09784556");
		advisor.setEmail("jean.valjean@gmail.com");

		service.save(advisor);
		int size = service.findByLastNameAndFirstNameAllIgnoreCase( "Valjean", "jean").size();
		assertTrue(size > 0);
	}
	
	
	@Test
	public void testUpdate()
	{
		Advisor advisor = new Advisor("Jean", "Valjean", new Address("10", "75010", "Paris"), new User("jean", "jean"));
		advisor.setCellphone("09784556");
		advisor.setEmail("jean.valjean@gmail.com");

		service.save(advisor);
		List<Advisor> advisors = service.findByLastNameAndFirstNameAllIgnoreCase( "Valjean", "jean");
		
		Advisor advisor2 = advisors.get(advisors.size()-1);
		
		advisor2.setAddress(new Address("78", "77420", "Champ sur Marne"));
		service.update(advisor2);
		List<Advisor> advisors2 = service.findByLastNameAndFirstNameAllIgnoreCase( "Valjean", "jean");
//		System.out.println("------------------------------------------- advisor = "+advisor);
//		System.out.println("------------------------------------------- advisor2 = "+advisors2.get(advisors.size()-1));
		assertFalse(advisor.getAddress().getZipCode().equals(advisors2.get(advisors.size()-1).getAddress().getZipCode()));
	}
	
	@Test
	public void testDelete()
	{
		Advisor advisor = new Advisor("Jean", "Valjean", new Address("10", "75010", "Paris"), new User("jean", "jean"));
		advisor.setCellphone("09784556");
		advisor.setEmail("jean.valjean@gmail.com");

		service.save(advisor);
		List<Advisor> advisors = service.findByLastNameAndFirstNameAllIgnoreCase( "Valjean", "jean");
		int sizeBefor = advisors.size();
		service.delete(advisors.get(0).getId());
		int sizeAfter = service.findByLastNameAndFirstNameAllIgnoreCase( "Valjean", "jean").size();
		assertTrue(sizeBefor > sizeAfter);
	}
}
