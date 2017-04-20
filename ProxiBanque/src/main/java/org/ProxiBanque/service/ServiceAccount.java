package org.ProxiBanque.service;

import java.util.List;

import org.ProxiBanque.dao.ICRUDAccount;
import org.ProxiBanque.model.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceAccount implements IServiceAccount {

	@Autowired
	ICRUDAccount daoAccount;
	
	@Override
	public void addAccount(BankAccount account) {
		daoAccount.saveAndFlush(account);
	}

	@Override
	public List<BankAccount> listAccounts() {
		return daoAccount.findAll();
	}

	@Override
	public void deleteAccount(long idAccount) {
		daoAccount.delete(idAccount);
	}

	@Override
	public void editAccount(BankAccount account) {
		if (account != null) {
			daoAccount.save(account);
		}
		
	}

}
