package org.ProxiBanque.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;


@Entity
public class SavingAccount extends BankAccount{
	
	@Column(name="TAUXREMUNERATION")
	private double tauxDeRemuneration;
	
	@OneToOne(mappedBy="safeAccount")
	private Client client;

	public SavingAccount(double tauxDeRemuneration, double sold, e_AccountType type) {
		super(sold, type);
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
