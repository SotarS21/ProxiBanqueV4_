package org.ProxiBanque.presentation;

import java.io.Serializable;

import org.ProxiBanque.model.BankAccount;
import org.ProxiBanque.service.ServiceAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VirementController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5022648763880776308L;

	@Autowired
	private ServiceAccount serviceAccount;
	
	private BankAccount creditorAccount, mortgagorAccount;
	private Double valeur;
	
	private void init() {
		
//		creditorAccount
		
	}
	
}
