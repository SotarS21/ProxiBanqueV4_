package org.ProxiBanque.wsrest.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.Response;

import org.ProxiBanque.exception.VirementException;
import org.ProxiBanque.model.BankAccount.e_AccountType;
import org.ProxiBanque.service.IServiceAccount;
import org.ProxiBanque.service.IServiceClient;
import org.ProxiBanque.wsrest.adapter.WSRestAdapter;
import org.ProxiBanque.wsrest.bean.BankAccount;
import org.ProxiBanque.wsrest.bean.Client;
import org.ProxiBanque.wsrest.bean.CurrentAccount;
import org.ProxiBanque.wsrest.bean.SavingAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@Component
public class ServiceAccountWSRest implements IServiceAccountWSRest {

	private static final Logger logger = LoggerFactory.getLogger(ServiceAccountWSRest.class);

	@Autowired
	IServiceAccount serviceAccount;

	@Autowired
	IServiceClient serviceClient;

	@PostConstruct
	public void init() {

		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	public Response addAccount(BankAccount account, Client client) {
		// TODO Auto-generated method stub
		logger.debug("entered addAccount()");

		org.ProxiBanque.model.Client unmarshallclient = WSRestAdapter.unmarshallClient(client);

		if (account.getType().equals(e_AccountType.CURRENT_ACCOUNT)) {

			if (unmarshallclient.getCurrentAccount() == null) {

				unmarshallclient.setCurrentAccount(WSRestAdapter.unmarshallCurrentAccount((CurrentAccount) account));
				serviceClient.save(unmarshallclient);

				return Response.ok().build();
			} else {

				return Response.notModified("client already has a current account").build();
			}
		} else {

			if (client.getSafeAccount() == null) {

				unmarshallclient.setSafeAccount(WSRestAdapter.unmarshallSavingAccount((SavingAccount) account));
				serviceClient.save(unmarshallclient);

				return Response.ok().build();
			} else {

				return Response.notModified("client already has a saving account").build();

			}

		}
	}

	@Override
	public BankAccount getAccount(String id) {
		// TODO Auto-generated method stub
		org.ProxiBanque.model.BankAccount account = serviceAccount.getAccount(Long.valueOf(id));

		if (account.getType() == e_AccountType.CURRENT_ACCOUNT) {

			return (BankAccount) WSRestAdapter.marshallCurrentAccount((org.ProxiBanque.model.CurrentAccount) account);
		} else {

			return (BankAccount) WSRestAdapter.marshallSavingAccount((org.ProxiBanque.model.SavingAccount) account);
		}
	}

	@Override
	public List<BankAccount> listAccounts() {
		// TODO Auto-generated method stub

		List<org.ProxiBanque.model.BankAccount> list = serviceAccount.listAccounts();
		List<BankAccount> marshallList = new ArrayList<BankAccount>();
		for (org.ProxiBanque.model.BankAccount bankAccount : list) {

			if (bankAccount.getType() == e_AccountType.CURRENT_ACCOUNT) {

				marshallList.add((BankAccount) WSRestAdapter
						.marshallCurrentAccount((org.ProxiBanque.model.CurrentAccount) bankAccount));
			} else {

				marshallList.add((BankAccount) WSRestAdapter
						.marshallSavingAccount((org.ProxiBanque.model.SavingAccount) bankAccount));
			}
		}
		return marshallList;
	}

	@Override
	public Response deleteAccount(String id) {
		// TODO Auto-generated method stub
		serviceAccount.deleteAccount(Long.valueOf(id));
		if (serviceAccount.getAccount(Long.valueOf(id)) == null) {

			return Response.ok().build();
		} else {

			return Response.notModified("error : a problem occured, or the object don't exist").build();
		}
	}

	@Override
	public Response editAccount(BankAccount account) {
		// TODO Auto-generated method stub
		logger.debug("entered editAccount()");

		if (account.getType().equals(e_AccountType.CURRENT_ACCOUNT)) {

			serviceAccount.editAccount((org.ProxiBanque.model.BankAccount) WSRestAdapter.unmarshallCurrentAccount((CurrentAccount)account));

			return Response.ok().build();

		} else {

			serviceAccount.editAccount((org.ProxiBanque.model.BankAccount) WSRestAdapter.unmarshallSavingAccount((SavingAccount)account));
			
			return Response.ok().build();
		}
	}

	@Override
	public List<BankAccount> getAccountsByClientId(String id) {
		// TODO Auto-generated method stub
		org.ProxiBanque.model.Client client = serviceClient.findOne(Long.valueOf(id));
		List<BankAccount> marshallList = new ArrayList<BankAccount>();
		
		if(client.getCurrentAccount() != null) {
			
			marshallList.add(WSRestAdapter.marshallCurrentAccount(client.getCurrentAccount()));
		}
		if(client.getSafeAccount() != null) {
			
			marshallList.add(WSRestAdapter.marshallSavingAccount(client.getSafeAccount()));
		}
		return marshallList;
	}

	@Override
	public Response doVirement(BankAccount debiteur, BankAccount crediteur, double montant) throws VirementException {
		// TODO Auto-generated method stub
		if(debiteur != null && crediteur != null) {
			
			org.ProxiBanque.model.BankAccount unmarsallDebiteur = null;
			org.ProxiBanque.model.BankAccount unmarsallCrediteur = null;
			
			if (debiteur.getType().equals(e_AccountType.CURRENT_ACCOUNT)) {

				unmarsallDebiteur = (org.ProxiBanque.model.BankAccount) WSRestAdapter.unmarshallCurrentAccount((CurrentAccount)debiteur);
			} else {
				
				unmarsallDebiteur = (org.ProxiBanque.model.BankAccount) WSRestAdapter.unmarshallSavingAccount((SavingAccount)debiteur);
			}
			
			if (crediteur.getType().equals(e_AccountType.CURRENT_ACCOUNT)) {

				unmarsallCrediteur = (org.ProxiBanque.model.BankAccount) WSRestAdapter.unmarshallCurrentAccount((CurrentAccount)crediteur);
			} else {
				
				unmarsallCrediteur = (org.ProxiBanque.model.BankAccount) WSRestAdapter.unmarshallSavingAccount((SavingAccount)crediteur);
			}
			
			serviceAccount.doVirement(unmarsallDebiteur, unmarsallCrediteur, montant);
			return Response.ok().build();
		} else {
			
			return Response.notModified("null account").build();
		}
	}

}
