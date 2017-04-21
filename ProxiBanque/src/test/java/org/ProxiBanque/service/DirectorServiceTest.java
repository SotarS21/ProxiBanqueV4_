package org.ProxiBanque.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.ProxiBanque.config.ApplicationConfig;
import org.ProxiBanque.model.Address;
import org.ProxiBanque.model.Director;
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
public class DirectorServiceTest {

	@Autowired
	IServiceDirector service;

	private static final Logger logger = LoggerFactory.getLogger(AdvisorTestService.class);

	@Test
	public void testAdvisorService() {
		assertNotNull(service);
	}

	@Test
	public void testAddAdvisor() {
		Director dir = new Director("Sam", "Dumarc", new Address("89", "Lamarle", "St Quentin"),
				new User("sam", "sam"));

		dir.setCellphone("09784556");
		dir.setEmail("sam.dumarc@gmail.com");

		int presize = service.findAll().size();
		service.save(dir);
		int postSize = service.findAll().size();
		assertTrue(presize < postSize);
	}

	@Test
	public void testListFindByName() {
		Director dir = new Director("Sam", "Dumarc", new Address("89", "Lamarle", "St Quentin"),
				new User("sam", "sam"));

		dir.setCellphone("09784556");
		dir.setEmail("sam.dumarc@gmail.com");

		service.save(dir);
		int size = service.findAll().size();
		assertTrue(size > 0);
	}

	@Test
	public void testUpdate() {
		Director dir = new Director("Sam", "Dumarc", new Address("89", "Lamarle", "St Quentin"),
				new User("sam", "sam"));

		dir.setCellphone("09784556");
		dir.setEmail("sam.dumarc@gmail.com");

		service.save(dir);
		List<Director> dirs = service.findAll();

		Director dir2 = dirs.get(dirs.size() - 1);

		dir2.setAddress(new Address("78", "77420", "Champ sur Marne"));
		service.update(dir2);
		List<Director> dirs2 = service.findAll();
		logger.debug("------------------------------------------- advisor = " + dir);
		logger.debug("------------------------------------------- advisor2 = " + dirs2.get(dirs2.size() - 1));
		assertFalse(dir.getAddress().getZipCode().equals(dirs2.get(dirs2.size() - 1).getAddress().getZipCode()));
	}

	@Test
	public void testDelete() {
		Director dir = new Director("Sam", "Dumarc", new Address("89", "Lamarle", "St Quentin"),
				new User("sam", "sam"));

		dir.setCellphone("09784556");
		dir.setEmail("sam.dumarc@gmail.com");

		service.save(dir);
		List<Director> dirs = service.findAll();
		int sizeBefor = dirs.size();
		service.delete(dirs.get(0).getId());
		int sizeAfter = service.findAll().size();
		assertTrue(sizeBefor > sizeAfter);
	}

}
