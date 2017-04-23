package org.ProxiBanque.wsrest.bean;

import javax.xml.bind.annotation.XmlRootElement;

import org.ProxiBanque.model.BankAccount.e_AccountType;

@XmlRootElement
public class CurrentAccount extends BankAccount {

	private double decouvert;

	public CurrentAccount(double decouvert, double sold) {
		super(sold, e_AccountType.CURRENT_ACCOUNT);
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
