package org.ProxiBanque.service;

import static org.junit.Assert.*;

import org.ProxiBanque.config.ApplicationConfig;
import org.ProxiBanque.model.Footprint;
import org.ProxiBanque.model.Footprint.e_HeadType;
import org.ProxiBanque.model.Footprint.e_State;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class FootPrintTest {

	@Autowired
	IServiceFootPrint service;
	
	@Test
	public void testServiceExist()
	{
		assertNotNull(service);
	}
	
//	@Test
//	public void testAddFootPrint() {
//		
//		int oldSize = service.findAll().size();
//		service.save(new Footprint(e_HeadType.CLENT, "Ajout d'un client de nom : test", e_State.SUCCESS));
//		int newSize = service.findAll().size();
//		
//		assertTrue(oldSize < newSize);
//	}

}
