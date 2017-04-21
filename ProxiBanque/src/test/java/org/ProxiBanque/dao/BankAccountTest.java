package org.ProxiBanque.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.ProxiBanque.config.ApplicationConfig;
import org.ProxiBanque.model.Address;
import org.ProxiBanque.model.BankAccount;
import org.ProxiBanque.model.Client;
import org.ProxiBanque.model.CurrentAccount;
import org.ProxiBanque.model.SavingAccount;
import org.ProxiBanque.service.IServiceAccount;
import org.ProxiBanque.service.IServiceClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ApplicationConfig.class)
public class BankAccountTest {

	@Autowired
	IServiceAccount serviceAccount;
	
	@Autowired
	IServiceClient serviceClient;
	
	@Test
	public void serviceNotNull() {
		assertNotNull(serviceAccount);
	}

	@Test
	public void createCurrentAccount(){
		BankAccount ba = new CurrentAccount(100, 0);
		serviceAccount.addAccount(ba);
		assertNotNull(serviceAccount.listAccounts());
	}
	
	@Test
	public void getCurrentAccount(){
		BankAccount ba = serviceAccount.getAccount(10L);
		assertNotNull(ba);
	}
	
	@Test
	public void updateCurrentAccount(){
		BankAccount ba = new CurrentAccount(100, 0);
		serviceAccount.addAccount(ba);
		ba.setSold(200);
		serviceAccount.editAccount(ba);
		assertTrue(200 == serviceAccount.listAccounts().get(1).getSold());
	}
	
//	@Test
//	public void deleteCurrentAccount(){
//		BankAccount ba = new CurrentAccount(100, 0);
//		serviceAccount.addAccount(ba);
//		int i = serviceAccount.listAccounts().size();
//		serviceAccount.deleteAccount(16L);
//		int j = serviceAccount.listAccounts().size();
//		j++;
//		assertTrue(i == j);
//	}
	
	@Test
	public void createSavingAccount(){
		Client cl = new Client("Jo", "Bar", new Address());
		serviceClient.save(cl);
		Client client = serviceClient.findOne(64L);
		BankAccount ba = new SavingAccount(0.5, 15000, client);
		serviceAccount.addAccount(ba);
	}
	
	@Test
	public void getAccountsByClientId(){
		Client client = new Client("Jo", "Bar", new Address());
		serviceClient.save(client);
		
		BankAccount ba = new CurrentAccount(0, 0, client);
		serviceAccount.addAccount(ba);
		BankAccount ba1 = new SavingAccount(0, 0, client);
		serviceAccount.addAccount(ba1);
		Long id = client.getId();
		
		assertTrue(serviceAccount.getAccountsByClientId(id).size()==2);

	}
	
}
