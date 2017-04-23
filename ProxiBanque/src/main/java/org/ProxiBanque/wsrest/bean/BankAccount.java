package org.ProxiBanque.wsrest.bean;

import javax.xml.bind.annotation.XmlRootElement;

import org.ProxiBanque.model.BankAccount.e_AccountType;


@XmlRootElement
public abstract class BankAccount {

	private long accountNumber;
	
	private double sold;
	
	private e_AccountType type;
	
	private double decouvert;
	
	public double getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
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

	public void setType(e_AccountType e_ClientType) {
		this.type = e_ClientType;
	}

	public BankAccount( double sold, e_AccountType type) {
		super();
		this.sold = sold;
		this.type = type;
	}

}
