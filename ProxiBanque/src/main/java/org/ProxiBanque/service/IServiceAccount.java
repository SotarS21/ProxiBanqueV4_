package org.ProxiBanque.service;

import java.util.List;

import org.ProxiBanque.exception.VirementException;
import org.ProxiBanque.model.BankAccount;
import org.ProxiBanque.model.BankAccount.e_AccountType;
import org.ProxiBanque.model.Client;

public interface IServiceAccount {

	public BankAccount getAccount (Long id);
	public void addAccount (BankAccount account, Client client);
	public List<BankAccount> listAccounts();
	public void deleteAccount (Long idAccount);
	public void editAccount (BankAccount account);
	public List<BankAccount> getAccountsByClientId(Long idClient);
	public String doVirement(BankAccount debiteur, BankAccount crediteur, double montant) throws VirementException;
	public List<Client> findAllClientOverdrawn();
	public List<BankAccount> doAudit();
	public void setClientRich();
	public void addAccount (Client client, e_AccountType type);
	List<Client> findByAdvisorClientOverdrawn(Long id);
	
}
