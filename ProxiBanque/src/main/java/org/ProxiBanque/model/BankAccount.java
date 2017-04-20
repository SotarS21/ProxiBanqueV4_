package org.ProxiBanque.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public abstract class BankAccount {

	private double sold;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long AccountNumber;
	
	
	public long getAccountNumber() {
		return AccountNumber;
	}



	public void setAccountNumber(long accountNumber) {
		AccountNumber = accountNumber;
	}



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
