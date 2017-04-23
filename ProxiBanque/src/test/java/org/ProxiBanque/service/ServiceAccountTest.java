package org.ProxiBanque.service;

import static org.junit.Assert.assertTrue;

import org.ProxiBanque.config.ApplicationConfig;
import org.ProxiBanque.exception.VirementException;
import org.ProxiBanque.model.Address;
import org.ProxiBanque.model.Advisor;
import org.ProxiBanque.model.Agence;
import org.ProxiBanque.model.Client;
import org.ProxiBanque.model.CurrentAccount;
import org.ProxiBanque.model.SavingAccount;
import org.ProxiBanque.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class ServiceAccountTest {
	
	@Autowired
	IServiceAccount serviceAccount;
	
	@Autowired
	IServiceAdvisor serviceAdvisor;
	
	@Test
	public void testVirement() {
		
		Agence agence = new Agence();
		Advisor advisor = new Advisor("", "", new Address(), new User());
		agence.addAdvisor(advisor);
		Client client = new Client("lol", "lol", new Address());
		Client client2 = new Client("lol", "lol", new Address());
		CurrentAccount currentAccount = new CurrentAccount(100, 200);
		CurrentAccount currentAccount2 = new CurrentAccount(50, 200);
		SavingAccount savingAccount = new SavingAccount(0.3, 200);
		SavingAccount savingAccount2 = new SavingAccount(0.4, 100);
		client.setCurrentAccount(currentAccount);
		client.setSafeAccount(savingAccount);
		client2.setCurrentAccount(currentAccount2);
		client2.setSafeAccount(savingAccount2);
		advisor.addClient(client);
		advisor.addClient(client2);
		serviceAdvisor.save(advisor);
		
		try {
			serviceAccount.doVirement(currentAccount, savingAccount2, 100);
		} catch (VirementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(currentAccount.getSold() == 100);
		assertTrue(savingAccount2.getSold() == 200);
		
		try {
			serviceAccount.doVirement(savingAccount, currentAccount2, 150);
		} catch (VirementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(savingAccount.getSold() == 50);
		assertTrue(currentAccount2.getSold() == 350);
		
		String res = "";
		try {
			serviceAccount.doVirement(savingAccount, currentAccount, 200);
		} catch (VirementException e) {
			// TODO Auto-generated catch block
			res = e.getMessage();
			assertTrue("solde insuffisant".equals(res));
		}
		
		try {
			serviceAccount.doVirement(currentAccount, currentAccount, 100000);
		} catch (VirementException e) {
			// TODO Auto-generated catch block
			res = e.getMessage();
			assertTrue("pas le droit pour un même compte".equals(res));
		}
	}

}
