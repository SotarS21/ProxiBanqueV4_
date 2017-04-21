package org.ProxiBanque.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="BANKACCOUNT")
public abstract class BankAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ACCOUNTNUMBER")
	private long accountNumber;
	
	@Column(name="SOLD")
	private double sold;
	
	@Column(name="TYPE")
	private e_AccountType type;
	
	@OneToOne(cascade=CascadeType.REFRESH)
	private Client client;
	
	public enum e_AccountType{
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
