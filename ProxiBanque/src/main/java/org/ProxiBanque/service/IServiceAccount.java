package org.ProxiBanque.service;

import java.util.List;

import org.ProxiBanque.exception.VirementException;
import org.ProxiBanque.model.BankAccount;
import org.ProxiBanque.model.BankAccount.e_AccountType;
import org.ProxiBanque.model.Client;

/**
 * Interface de service concernant le compte bancaire, elle est appeler dans les controllers addapter ou dans un webService .
 * 
 * 
* @author kevin, jonas, andy, mathieu
* @version 1.0
*/

public interface IServiceAccount {

	/**
	 * 
	 * @param id l'identifiant d'un compte bancaire
	 * @return un compte bancaire de type générique BankAccount
	 */
	public BankAccount getAccount (Long id);
	
	/**
	 * 
	 * @param account le compte bancaire à ajouter
	 * @param client le client qui lui est liée
	 */
	public void addAccount (BankAccount account, Client client);
	/**
	 * 
	 * @return la liste des comptes bancaire 
	 */
	public List<BankAccount> listAccounts();
	
	/**
	 * 
	 * @param idAccount l'identifiant du compte à supprimer
	 */
	public void deleteAccount (Long idAccount);
	/**
	 * 
	 * @param account compte bancaire à modifier
	 */
	public void editAccount (BankAccount account);
	/**
	 * 
	 * @param idClient identifiant du client 
	 * @return la liste des comptes du client 
	 */
	public List<BankAccount> getAccountsByClientId(Long idClient);
	/**
	 * 
	 * @param debiteur compte bancaire à débiter.
	 * @param crediteur compte bancaire à créditer.
	 * @param montant : le montant de la transaction.
	 * @return la fenêtre JSF vers la quel on veux se diriger.
	 * @throws VirementException renvois une exception particulière si le virement s'est mal passé, notamment pour tracer les LOGS du virement.
	 */
	public String doVirement(BankAccount debiteur, BankAccount crediteur, double montant) throws VirementException;
	/**
	 * 
	 * @return la liste des clients qui sont à découvert
	 */
	public List<Client> findAllClientOverdrawn();
	/**
	 * 
	 * @return liste des comptes bancaires qui réponde au critère de l'audit
	 */
	public List<BankAccount> doAudit();
	public void setClientRich();
	/**
	 * 
	 * @param client client auquel on ajoute un compte bancaire 
	 * @param type type de compte que l'on veux ajouter : e_AccountType.CURRENT_ACCOUNT / e_AccountType.SAVING_ACCOUNT
	 */
	public void addAccount (Client client, e_AccountType type);
	/**
	 * 
	 * @param id identifiant du conseiller
	 * @return la liste des clients à découvert du conseiller
	 */
	List<Client> findByAdvisorClientOverdrawn(Long id);
	
}
