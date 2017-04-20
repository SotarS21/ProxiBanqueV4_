package org.ProxiBanque.service;

import java.util.List;

import org.ProxiBanque.dao.ICRUDAccount;
import org.ProxiBanque.model.BankAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceAccount implements IServiceAccount {

	private static final Logger logger = LoggerFactory.getLogger(ServiceAccount.class);
	
	@Autowired
	ICRUDAccount daoAccount;
	
	@Override
	public void addAccount(BankAccount account) {
		logger.debug("test add account 1");
		daoAccount.saveAndFlush(account);
		logger.debug("test add account 2");
	}

	@Override
	public List<BankAccount> listAccounts() {
		logger.debug("test list accounts");
		return daoAccount.findAll();
	}

	@Override
	public void deleteAccount(long idAccount) {
		logger.debug("test delete account 1");
		daoAccount.delete(idAccount);
		logger.debug("test delete account 2");
	}

	@Override
	public void editAccount(BankAccount account) {
		logger.debug("test edit account 1");
		if (account != null) {
			daoAccount.save(account);
			logger.debug("test edit account 2");
		}
		
	}

}
