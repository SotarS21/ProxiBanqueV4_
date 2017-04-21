package org.ProxiBanque.service;

import java.util.List;

import org.ProxiBanque.model.BankAccount;

public interface IServiceAccount {

	public BankAccount getAccount (Long id);
	public void addAccount (BankAccount account);
	public List<BankAccount> listAccounts();
	public void deleteAccount (Long idAccount);
	public void editAccount (BankAccount account);
	public List<BankAccount> getAccountsByClientId(Long idClient);
}
