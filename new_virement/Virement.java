package org.ProxiBanque.model;

import org.springframework.stereotype.Component;

@Component
public class Virement {

	private double value;
	private BankAccount account;
	
	public Virement(double value, BankAccount account) {
		super();
		this.value = value;
		this.account = account;
	}
	public Virement() {
		super();
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public BankAccount getAccount() {
		return account;
	}
	public void setAccount(BankAccount account) {
		this.account = account;
	}
	
	
	
}
