package org.ProxiBanque.wsrest.bean;

import javax.xml.bind.annotation.XmlRootElement;

import org.ProxiBanque.model.BankAccount.e_AccountType;


@XmlRootElement
public class SavingAccount extends BankAccount{
	
	private double tauxDeRemuneration;
	
	private double decouvert;
	
	
	
	public SavingAccount(double sold, double tauxDeRemuneration, double decouvert) {
		super(sold, e_AccountType.SAVING_ACCOUNT);
		this.tauxDeRemuneration = tauxDeRemuneration;
		this.decouvert = decouvert;
	}

	public double getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}

	public SavingAccount(double tauxDeRemuneration, double sold) {
		super(sold, e_AccountType.SAVING_ACCOUNT);
		this.tauxDeRemuneration = tauxDeRemuneration;
	}

	public SavingAccount() {
		super();
	}

	public double getTauxDeRemuneration() {
		return tauxDeRemuneration;
	}

	public void setTauxDeRemuneration(double tauxDeRemuneration) {
		this.tauxDeRemuneration = tauxDeRemuneration;
	}
}
