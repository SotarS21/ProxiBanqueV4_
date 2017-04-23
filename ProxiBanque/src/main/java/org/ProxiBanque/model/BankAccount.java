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
	private e_AccountType type = e_AccountType.CURRENT_ACCOUNT;
	
	@OneToOne(cascade=CascadeType.REFRESH)
	private Client client;
	
	@Column(name = "DECOUVERT")
	private double decouvert;
	
	
	public enum e_AccountType{
		CURRENT_ACCOUNT,
		SAVING_ACCOUNT
	}
	
	
	public double getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
}
