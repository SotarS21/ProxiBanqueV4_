package org.ProxiBanque.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;


@Entity
public class SavingAccount extends BankAccount{
	
	@Column(name="TAUXREMUNERATION")
	private double tauxDeRemuneration;
	
	@OneToOne(mappedBy="safeAccount",cascade=CascadeType.REFRESH)
	private Client client;

	@Column(name = "DECOUVERT")
	private double decouvert;
	
	
	
	public SavingAccount(double sold, double tauxDeRemuneration, Client client, double decouvert) {
		super(sold, e_AccountType.SAVING_ACCOUNT);
		this.tauxDeRemuneration = tauxDeRemuneration;
		this.client = client;
		this.decouvert = decouvert;
	}

	public SavingAccount(double sold, double tauxDeRemuneration, double decouvert) {
		super(sold, e_AccountType.SAVING_ACCOUNT);
		this.tauxDeRemuneration = tauxDeRemuneration;
		this.decouvert = decouvert;
	}

	public double getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public SavingAccount(double sold, double tauxDeRemuneration, Client client) {
		super(sold, e_AccountType.SAVING_ACCOUNT);
		this.tauxDeRemuneration = tauxDeRemuneration;
		this.client = client;
	}

	public SavingAccount(double tauxDeRemuneration, double sold) {
		super(sold, e_AccountType.SAVING_ACCOUNT);
		this.tauxDeRemuneration = tauxDeRemuneration;
	}

	public SavingAccount() {
		super();
	}

	public double getTauxDeRemuneration() {
		return tauxDeRemuneration;
	}

	public void setTauxDeRemuneration(double tauxDeRemuneration) {
		this.tauxDeRemuneration = tauxDeRemuneration;
	}
}
