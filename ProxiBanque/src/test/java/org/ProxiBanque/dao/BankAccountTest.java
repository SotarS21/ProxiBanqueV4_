package org.ProxiBanque.dao;

import static org.junit.Assert.assertTrue;

import org.ProxiBanque.config.ApplicationConfig;
import org.ProxiBanque.exception.VirementException;
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
@ContextConfiguration(classes = ApplicationConfig.class)
public class BankAccountTest {

	@Autowired
	IServiceAccount serviceAccount;

	@Autowired
	IServiceClient serviceClient;

//	@Test
//	public void serviceNotNull() {
//		assertNotNull(serviceAccount);
//	}
//
//	@Test
//	public void createCurrentAccount() {
//		Client cl = new Client("Jo", "Bar", new Address());
//		serviceClient.save(cl);
//		CurrentAccount ba = new CurrentAccount(100, 0);
//		serviceAccount.addAccount(ba, cl);
//		assertNotNull(serviceAccount.listAccounts());
//	}
//
//	@Test
//	public void dontCreateTwoSameAccountTypeHereCurrent() {
//		Client cl = new Client("Jo", "Bar", new Address());
//		serviceClient.save(cl);
//		CurrentAccount ba = new CurrentAccount(100, 0);
//		serviceAccount.addAccount(ba, cl);
//		CurrentAccount ba1 = new CurrentAccount(0, 100);
//		serviceAccount.addAccount(ba1, cl);
//		Long id = cl.getId();
//		assertTrue(serviceAccount.getAccountsByClientId(id).size() == 1);
//	}
//
//	@Test
//	public void dontCreateTwoSameAccountTypeHereSaving() {
//		Client cl = new Client("Jo", "Bar", new Address());
//		serviceClient.save(cl);
//		SavingAccount ba = new SavingAccount(100, 0, 0);
//		serviceAccount.addAccount(ba, cl);
//		SavingAccount ba1 = new SavingAccount(100, 0, 0);
//		serviceAccount.addAccount(ba1, cl);
//		Long id = cl.getId();
//		assertTrue(serviceAccount.getAccountsByClientId(id).size() == 1);
//	}
//
//	@Test
//	public void doVirement() throws VirementException {
//		Client cl = new Client("Jo", "Bar", new Address());
//		serviceClient.save(cl);
//		SavingAccount sa = new SavingAccount(0, 1000);
//		serviceAccount.addAccount(sa, cl);
//		CurrentAccount ca = new CurrentAccount(0, 0);
//		serviceAccount.addAccount(ca, cl);
//		serviceAccount.doVirement(sa, ca, 500);
//		assertTrue(ca.getSold() == 500);
//	}

//	@Test
//	 public void dontDoVirementSameAccount() throws Exception {
//	 Client cl = new Client("Jo", "Bar", new Address());
//	 serviceClient.save(cl);
//	 CurrentAccount sa = new CurrentAccount(0, 1000);
//	 serviceAccount.addAccount(sa, cl);
//	 assertTrue(serviceAccount.doVirement(sa, sa, 500).equals("pas le droit pour un m�me compte"));
//	 }
//
	@Test
	 public void dontDoVirementSoldeInsuffisant() throws RuntimeException {
	 Client cl = new Client("Jo", "Bar", new Address());
	 serviceClient.save(cl);
	 SavingAccount sa = new SavingAccount(100, 0, 500);
	 serviceAccount.addAccount(sa, cl);
	 CurrentAccount ca = new CurrentAccount(0, 0);
	 serviceAccount.addAccount(ca, cl);
	 try {
		 serviceAccount.doVirement(sa, ca, 800);
	} catch (VirementException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		assertTrue("from n� 176 (client n� 175) to n� 177 (client n� 175), amount :800.0, reason : unsufficient funds" == e.toString());
	}
	 }

//	@Test
//	public void checkClientOverdrawn() {
//		Client cl = new Client("Jo", "Bar", new Address());
//		serviceClient.save(cl);
//		CurrentAccount ca = new CurrentAccount(100, -500);
//		serviceAccount.addAccount(ca, cl);
//		assertNotNull(serviceAccount.findAllClientOverdrawn());
//	}
//
//	@Test
//	public void getCurrentAccount() {
//		Client cl = new Client("Jo", "Bar", new Address());
//		serviceClient.save(cl);
//		CurrentAccount ca = new CurrentAccount(100, -500);
//		serviceAccount.addAccount(ca, cl);
//		BankAccount ba = serviceAccount.getAccount(ca.getAccountNumber());
//		assertNotNull(ba);
//	}
//
//	@Test
//	public void updateCurrentAccount() {
//		Client cl = new Client("Jo", "Bar", new Address());
//		serviceClient.save(cl);
//		CurrentAccount ba = new CurrentAccount(100, 0);
//		serviceAccount.addAccount(ba, cl);
//		ba.setSold(200);
//		serviceAccount.editAccount(ba);
//		assertTrue(200 == ba.getSold());
//	}

//	@Test
//	public void deleteCurrentAccount() {
//		Client cl = new Client("Jo", "Bar", new Address());
//		serviceClient.save(cl);
//		CurrentAccount ba = new CurrentAccount(100, 0);
//		serviceAccount.addAccount(ba, cl);
//		int i = serviceAccount.listAccounts().size();
//		long id = ba.getAccountNumber();
//		serviceAccount.deleteAccount(id);
//		int j = serviceAccount.listAccounts().size();
//		j++;
//		System.out.println(i + "!!!!!!!!!!!!!!!" + j);
//		assertTrue(i == j);
//	}

	@Test
	public void deleteSavingAccount() {
		Client cl = new Client("Jo", "Bar", new Address());
		SavingAccount ba = new SavingAccount(100, 0, 0);
		cl.setSafeAccount(ba);
		serviceClient.save(cl);
		cl.setSafeAccount(null);
		serviceClient.update(cl);
		long id = ba.getAccountNumber();
		serviceAccount.deleteAccount(id);
		BankAccount accountTemp = serviceAccount.getAccount(id);
		assertTrue(accountTemp == null);
	}

//	@Test
//	public void isClientSetRich() {
//		Client cl = new Client("Jo", "Bar", new Address());
//		serviceClient.save(cl);
//		Long idClient = cl.getId();
//		SavingAccount sa = new SavingAccount(30000, 0, 0);
//		serviceAccount.addAccount(sa, cl);
//		CurrentAccount ca = new CurrentAccount(0, 30000);
//		serviceAccount.addAccount(ca, cl);
//		serviceAccount.setClientRich();
//		Client cl1 = serviceClient.findOne(idClient);
//		assertTrue(cl1.isRitch());
//	}

//	@Test
//	public void isClientNotRich() {
//		Client cl = new Client("Jo", "Bar", new Address());
//		serviceClient.save(cl);
//		Long idClient = cl.getId();
//		SavingAccount sa = new SavingAccount(2000, 0, 0);
//		serviceAccount.addAccount(sa, cl);
//		CurrentAccount ca = new CurrentAccount(0, 30000);
//		serviceAccount.addAccount(ca, cl);
//		serviceAccount.setClientRich();
//		Client cl1 = serviceClient.findOne(idClient);
//		assertTrue(cl1.isRitch() == false);
//	}
//
//	@Test
//	public void auditTest() {
//		int i = serviceAccount.doAudit().size();
//		Client cl1 = new Client("Jo", "Bar", new Address());
//		cl1.setType(e_ClientType.CASUAL_CLIENT);
//		serviceClient.save(cl1);
//		Client cl2 = new Client("Jo", "Bar", new Address());
//		cl2.setType(e_ClientType.ENTERPRISE_CLIENT);
//		serviceClient.save(cl2);
//		SavingAccount sa = new SavingAccount(-2000, 0, 0);
//		serviceAccount.addAccount(sa, cl1);
//		CurrentAccount ca = new CurrentAccount(0, -3000);
//		serviceAccount.addAccount(ca, cl1);
//		SavingAccount sa1 = new SavingAccount(-2000, 0, 0);
//		serviceAccount.addAccount(sa1, cl2);
//		CurrentAccount ca1 = new CurrentAccount(0, -300000);
//		serviceAccount.addAccount(ca1, cl2);
//		assertTrue(serviceAccount.doAudit().size() == i + 1);
//	}
//
//	@Test
//	public void createSavingAccountWithClient() {
//		Client cl = new Client("Jo", "Bar", new Address());
//		serviceClient.save(cl);
//		SavingAccount sa = new SavingAccount(0, 1000, 0);
//		serviceAccount.addAccount(sa, cl);
//		// cl.setSafeAccount(sa);
//		// serviceClient.save(cl);
//		assertNotNull(cl.getSafeAccount());
//	}
//
//	@Test
//	public void getAccountsByClientId() {
//		Client client = new Client("Jo", "Bar", new Address());
//		serviceClient.save(client);
//
//		CurrentAccount ba = new CurrentAccount(0, 0, client);
//		serviceAccount.addAccount(ba, client);
//		SavingAccount ba1 = new SavingAccount(0, 0, client);
//		serviceAccount.addAccount(ba1, client);
//		// client.setSafeAccount(ba1);
//		// client.setCurrentAccount(ba);
//		// serviceClient.save(client);
//		Long id = client.getId();
//
//		assertTrue(serviceAccount.getAccountsByClientId(id).size() == 2);
//
//	}

}
