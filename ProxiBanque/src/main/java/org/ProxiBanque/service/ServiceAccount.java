package org.ProxiBanque.service;

import java.util.ArrayList;
import java.util.List;

import org.ProxiBanque.dao.ICRUDAccount;
import org.ProxiBanque.dao.ICRUDClient;
import org.ProxiBanque.exception.VirementException;
import org.ProxiBanque.model.BankAccount;
import org.ProxiBanque.model.BankAccount.e_AccountType;
import org.ProxiBanque.model.Client;
import org.ProxiBanque.model.Client.e_ClientType;
import org.ProxiBanque.model.CurrentAccount;
import org.ProxiBanque.model.SavingAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceAccount implements IServiceAccount {

	private static final Logger logger = LoggerFactory.getLogger(ServiceAccount.class);

	@Autowired
	ICRUDAccount daoAccount;

	@Autowired
	ICRUDClient daoClient;

	@Override
	public void addAccount(BankAccount account, Client client) {
		logger.debug("test add account 1");
		account.setClient(client);
		daoAccount.save(account);
		if (account.getType().equals(e_AccountType.CURRENT_ACCOUNT)) {
			if (client.getCurrentAccount() == null) {
				CurrentAccount account1 = (CurrentAccount) account;
				client.setCurrentAccount(account1);
				daoClient.save(client);
			} else {
				daoAccount.delete(account);
			}

		} else if (account.getType().equals(e_AccountType.SAVING_ACCOUNT)) {
			if (client.getSafeAccount() == null) {
				SavingAccount account2 = (SavingAccount) account;
				client.setSafeAccount(account2);
				daoClient.save(client);
			} else {
				daoAccount.delete(account);
			}

		}

		logger.debug("test add account 2");
	}

	@Override
	public List<BankAccount> listAccounts() {
		logger.debug("test list accounts");
		return daoAccount.findAll();
	}

	@Override
	public void deleteAccount(Long idAccount) {
		logger.debug("test delete account 1");
		
		daoAccount.delete(idAccount);
		
//		BankAccount ba = this.getAccount(idAccount);
//		System.out.println(ba);
//		if (ba.getType().equals(e_AccountType.CURRENT_ACCOUNT)) {
//			Client cl = ba.getClient();
//			cl.setCurrentAccount(null);
//			daoClient.save(cl);
//			daoAccount.delete(idAccount);
//		} else if (ba.getType().equals(e_AccountType.SAVING_ACCOUNT)) {
//			Client cl = ba.getClient();
////			cl.setSafeAccount(null);
//			daoClient.save(cl);
//			daoAccount.delete(idAccount);
//		}
//		logger.debug("test delete account 2");
	}

	@Override
	public void editAccount(BankAccount account) {
		logger.debug("test edit account 1");
		if (account.getClient() != null) {
			daoAccount.save(account);
			logger.debug("test edit account 2");
		}

	}

	@Override
	public BankAccount getAccount(Long id) {
		return daoAccount.findOne(id);
	}

	@Override
	public List<BankAccount> getAccountsByClientId(Long idClient) {
		return daoAccount.findAllByClient_Id(idClient);
	}

	@Override
	public String doVirement(BankAccount debiteur, BankAccount crediteur, double montant) throws VirementException {
		if (debiteur.getAccountNumber() == crediteur.getAccountNumber()) {

			throw new VirementException("pas le droit pour un m�me compte");
		} else {
			double soldDeb = debiteur.getSold();
			double soldCred = crediteur.getSold();

			double newSoldDeb = soldDeb - montant;
			double newSoldCred = soldCred + montant;

			if (newSoldDeb < (0 - debiteur.getDecouvert())) {

				throw new VirementException("solde insuffisant");
			} else {
				debiteur.setSold(newSoldDeb);
				crediteur.setSold(newSoldCred);
				this.editAccount(crediteur);
				this.editAccount(debiteur);
			}
		}
		return "Le virement a �t� effectu�";
	}

	@Override
	public List<Client> findAllClientOverdrawn() {
		List<BankAccount> list = this.listAccounts();
		List<Client> listret = new ArrayList<>();
		for (BankAccount bankAccount : list) {
			if (bankAccount.getSold() < 0) {
				listret.add(bankAccount.getClient());
			}
		}
		return listret;
	}

	@Override
	public List<BankAccount> doAudit() throws RuntimeException {
		List<Client> listClient = daoClient.findAll();
		List<BankAccount> listAudit = new ArrayList<>();
		for (Client client : listClient) {
			Long id = client.getId();
			List<BankAccount> listAccount = this.getAccountsByClientId(id);
			if (client.getType() == e_ClientType.CASUAL_CLIENT) {
				for (BankAccount bankAccount : listAccount) {
					if (bankAccount.getType().equals(e_AccountType.CURRENT_ACCOUNT)) {
						CurrentAccount ca = (CurrentAccount) bankAccount;
						if (ca.getSold() < -5000) {
							listAudit.add(ca);
						}
					} else if (bankAccount.getType().equals(e_AccountType.SAVING_ACCOUNT)) {
						SavingAccount sa = (SavingAccount) bankAccount;
						if (sa.getSold() < -5000) {
							listAudit.add(sa);
						}
					}
				}
			} else if (client.getType() == e_ClientType.ENTERPRISE_CLIENT) {
				for (BankAccount bankAccount : listAccount) {
					if (bankAccount.getType().equals(e_AccountType.CURRENT_ACCOUNT)) {
						CurrentAccount ca = (CurrentAccount) bankAccount;
						if (ca.getSold() < -5000) {
							listAudit.add(ca);
						}
					} else if (bankAccount.getType().equals(e_AccountType.SAVING_ACCOUNT)) {
						SavingAccount sa = (SavingAccount) bankAccount;
						if (sa.getSold() < -50000) {
							listAudit.add(sa);
						}
					}
				}
			}
		}
		return listAudit;
	}

	@Override
	public void setClientRich() {
		List<Client> listClient = daoClient.findAll();
		for (Client client : listClient) {
			Long id = client.getId();
			List<BankAccount> listAccount = this.getAccountsByClientId(id);
			double total = 0;
			for (BankAccount bankAccount : listAccount) {
				if (bankAccount.getType().equals(e_AccountType.CURRENT_ACCOUNT)) {
					CurrentAccount ca = (CurrentAccount) bankAccount;
					total += ca.getSold();
				} else if (bankAccount.getType().equals(e_AccountType.SAVING_ACCOUNT)) {
					SavingAccount sa = (SavingAccount) bankAccount;
					total += sa.getSold();
				}
			}
			if (total >= 500000) {
				client.setRitch(true);
				;
				daoClient.save(client);
			} else {
				client.setRitch(false);
				daoClient.save(client);
			}

		}
		
		
	}

	@Override
	public void addAccount(Client client, e_AccountType type) {
		
		if (type.equals(e_AccountType.CURRENT_ACCOUNT)) {
			if (client.getCurrentAccount() == null) {
				CurrentAccount currentAccount = new CurrentAccount();
				client.setCurrentAccount(currentAccount);
				currentAccount.setType(e_AccountType.CURRENT_ACCOUNT);
				currentAccount.setClient(client);
				daoAccount.save(currentAccount);
				daoClient.save(client);
				
			} 

		} else if (type.equals(e_AccountType.SAVING_ACCOUNT)) {
			if (client.getSafeAccount() == null) {
				SavingAccount savingAccount = new SavingAccount();
				savingAccount.setType(e_AccountType.SAVING_ACCOUNT);
				client.setSafeAccount(savingAccount);
				savingAccount.setClient(client);
				daoAccount.save(savingAccount);
				daoClient.save(client);
			}

		}

		logger.debug("test add account 2");
	
		
	}

}
