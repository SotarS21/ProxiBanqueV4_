package org.ProxiBanque.service;

import java.util.List;

import org.ProxiBanque.model.BankAccount;

public interface IServiceAccount {

	public void addAccount (BankAccount account);
	public List<BankAccount> listAccounts();
	public void deleteAccount (long idAccount);
	public void editAccount (BankAccount account);
}
