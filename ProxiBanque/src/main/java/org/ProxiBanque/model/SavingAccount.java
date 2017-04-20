package org.ProxiBanque.model;

public class SavingAccount extends BankAccount{
	
	private double tauxDeRemuneration;

	public SavingAccount(double tauxDeRemuneration, double sold, e_AccountType type) {
		super(sold, type);
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
