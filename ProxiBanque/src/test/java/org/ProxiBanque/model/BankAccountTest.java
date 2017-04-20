package org.ProxiBanque.model;

import static org.junit.Assert.*;

import org.ProxiBanque.config.ApplicationConfig;
import org.ProxiBanque.service.IServiceAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ApplicationConfig.class)
public class BankAccountTest {

	@Autowired
	IServiceAccount service;
	
	@Test
	public void serviceNotNull() {
		assertNotNull(service);
	}

}
