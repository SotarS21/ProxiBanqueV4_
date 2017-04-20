package org.ProxiBanque.model;

public class CurrentAccount extends BankAccount{

	private double decouvert;

	public CurrentAccount(double decouvert, double sold) {
		super(sold);
		this.decouvert = decouvert;
	}

	public double getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}
	
	
	
}
