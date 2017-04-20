package org.ProxiBanque.model;

public class SafeAccount extends BankAccount{
	
	private double tauxDeRemuneration;

	public SafeAccount(double tauxDeRemuneration, double sold) {
		super(sold);
		this.tauxDeRemuneration = tauxDeRemuneration;
	}

	public SafeAccount() {
		super();
	}

	public double getTauxDeRemuneration() {
		return tauxDeRemuneration;
	}

	public void setTauxDeRemuneration(double tauxDeRemuneration) {
		this.tauxDeRemuneration = tauxDeRemuneration;
	}
	
	
	
	
	

}
