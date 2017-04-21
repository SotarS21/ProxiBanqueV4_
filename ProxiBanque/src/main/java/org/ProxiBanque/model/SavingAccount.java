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

	
	
	public SavingAccount(double sold, double tauxDeRemuneration, Client client) {
		super(sold, e_AccountType.CURRUENT_ACCOUNT);
		this.tauxDeRemuneration = tauxDeRemuneration;
		this.client = client;
	}

	public SavingAccount(double tauxDeRemuneration, double sold) {
		super(sold, e_AccountType.CURRUENT_ACCOUNT);
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
