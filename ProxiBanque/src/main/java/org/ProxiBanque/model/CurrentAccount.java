package org.ProxiBanque.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CurrentAccount extends BankAccount{

	@Column(name="DECOUVERT")
	private double decouvert;

	public CurrentAccount(double decouvert, double sold, e_AccountType type) {
		super(sold,  type);
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
