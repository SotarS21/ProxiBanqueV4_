package org.ProxiBanque.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="")
public class Client extends Person{

	private CurrentAccount currentAccount;
	private SavingAccount safeAccount;
	private e_ClientType type;
	private boolean isRitch;
	private Advisor advisor;
	
	
	enum e_ClientType{
		CASUAL_CLIENT,
		ENTERPRISE_CLIENT
	}


	public Client(String firstName, String lastName, Address address, e_ClientType type, boolean isRitch,
			Advisor advisor) {
		super(firstName, lastName, address);
		this.type = type;
		this.isRitch = isRitch;
		this.advisor = advisor;
	}


	public Client(String firstName, String lastName, Address address) {
		super(firstName, lastName, address);
	}


	public CurrentAccount getCurrentAccount() {
		return currentAccount;
	}


	public void setCurrentAccount(CurrentAccount currentAccount) {
		this.currentAccount = currentAccount;
	}


	public SavingAccount getSafeAccount() {
		return safeAccount;
	}


	public void setSafeAccount(SavingAccount safeAccount) {
		this.safeAccount = safeAccount;
	}


	public e_ClientType getType() {
		return type;
	}


	public void setType(e_ClientType type) {
		this.type = type;
	}


	public boolean isRitch() {
		return isRitch;
	}


	public void setRitch(boolean isRitch) {
		this.isRitch = isRitch;
	}


	public Advisor getAdvisor() {
		return advisor;
	}


	public void setAdvisor(Advisor advisor) {
		this.advisor = advisor;
	}


	
	
}
