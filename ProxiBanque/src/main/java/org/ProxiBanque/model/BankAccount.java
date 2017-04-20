package org.ProxiBanque.model;

public abstract class BankAccount {

	private double sold;

	public BankAccount() {
		super();
	}
	
	

	public BankAccount(double sold) {
		super();
		this.sold = sold;
	}



	public double getSold() {
		return sold;
	}

	public void setSold(double sold) {
		this.sold = sold;
	}
	
	
	
	
}
