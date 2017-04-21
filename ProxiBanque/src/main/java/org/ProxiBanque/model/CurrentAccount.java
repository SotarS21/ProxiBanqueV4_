package org.ProxiBanque.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;


@Entity
public class CurrentAccount extends BankAccount{

	@Column(name="DECOUVERT")
	private double decouvert;
	
	@OneToOne(mappedBy="currentAccount",cascade=CascadeType.REFRESH)
	private Client client;

	

	public CurrentAccount(double sold, double decouvert, Client client) {
		super(sold, e_AccountType.CURRUENT_ACCOUNT);
		this.decouvert = decouvert;
		this.client = client;
	}

	public CurrentAccount(double decouvert, double sold) {
		super(sold, e_AccountType.CURRUENT_ACCOUNT);
		this.decouvert = decouvert;
	}

	public double getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}

	public CurrentAccount() {
		super();
	}
	
	
	
}
