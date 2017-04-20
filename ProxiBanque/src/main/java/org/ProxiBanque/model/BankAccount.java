package org.ProxiBanque.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public abstract class BankAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long accountNumber;
	private double sold;
	private e_AccountType type;
	
	
	enum e_AccountType{
		CURRUENT_ACCOUNT,
		SAVING_ACCOUNT
	}
	
	public long getAccountNumber() {
		return accountNumber;
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

	public e_AccountType getType() {
		return type;
	}

	public void setType(e_AccountType type) {
		this.type = type;
	}

	public BankAccount( double sold, e_AccountType type) {
		super();
		this.sold = sold;
		this.type = type;
	}


	
	
}
