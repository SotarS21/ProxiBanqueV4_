package org.ProxiBanque.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public abstract class BankAccount {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long accountNumber;
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
