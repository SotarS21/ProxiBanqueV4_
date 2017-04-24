package org.ProxiBanque.dao;

import java.util.List;

import org.ProxiBanque.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Interface permettant la communication avec la base de donn�e
 * Permet la sauvegarde des comptes bancaires
 * 
 * @author K�vin, Jonas, Andy, Mathieu
 * @version 1.0
 */
public interface ICRUDAccount extends JpaRepository<BankAccount, Long> {

	
	/**
	 * 
	 * @param id identifiant du Client
	 * @return liste des comptes du client
	 */
	public List<BankAccount> findAllByClient_Id(Long id);
}
