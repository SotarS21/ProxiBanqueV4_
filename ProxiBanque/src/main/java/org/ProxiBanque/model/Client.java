package org.ProxiBanque.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * Bean Client regroupe les informations du client : le compte courant, le
 * compte �pargne; son type , s'il est riche, son conseiller
 * 
 * @author Jonas, Kevin, Andy, Mathieu
 *
 */
@Component
@Entity
@Table(name = "CLIENT")
@DiscriminatorValue("C")
public class Client extends Person {

	@OneToOne(cascade = CascadeType.ALL)
	private CurrentAccount currentAccount;

	@OneToOne(cascade = CascadeType.ALL)
	private SavingAccount safeAccount;

	@Column(name = "TYPE")
	private e_ClientType type = e_ClientType.CASUAL_CLIENT;

	@Column(name = "ISRICH")
	private boolean isRitch = false;

	@ManyToOne(fetch = FetchType.EAGER)
	private Advisor advisor;

	@ManyToOne(fetch = FetchType.LAZY)
	private Agence agence;

	public enum e_ClientType {
		CASUAL_CLIENT, ENTERPRISE_CLIENT
	}

	public Client() {
		super();
	}

	public Client(String firstName, String lastName, Address address, e_ClientType type, boolean isRitch,
			Advisor advisor) {
		super(firstName, lastName, address);
		this.type = type;
		this.isRitch = isRitch;
		this.advisor = advisor;
	}

	public Client(String firstName, String lastName, Address address) {
		super(firstName, lastName, address);
	}

	public CurrentAccount getCurrentAccount() {
		return currentAccount;
	}

	public void setCurrentAccount(CurrentAccount currentAccount) {
		this.currentAccount = currentAccount;
		if (currentAccount != null) {

			currentAccount.setClient(this);
		}
	}

	public SavingAccount getSafeAccount() {
		return safeAccount;
	}

	public void setSafeAccount(SavingAccount safeAccount) {
		this.safeAccount = safeAccount;
		if (safeAccount != null) {

			safeAccount.setClient(this);
		}
	}

	public e_ClientType getType() {
		return type;
	}

	public void setType(e_ClientType type) {
		this.type = type;
	}

	public boolean isRitch() {
		return isRitch;
	}

	public void setRitch(boolean isRitch) {
		this.isRitch = isRitch;
	}

	public Advisor getAdvisor() {
		return advisor;
	}

	public void setAdvisor(Advisor advisor) {
		
		if(this.advisor != null && advisor != this.advisor) {
			
			this.advisor.removeClient(this);
		}
		this.advisor = advisor;
		if (advisor != null && ! advisor.getClients().contains(this)) {
			
				advisor.addClient(this);
				setAgence(advisor.getAgence());
		}
	
	}

	public Agence getAgence() {
		return agence;
	}

	public void setAgence(Agence agence) {

		if (super.getAgence() != null && agence != super.getAgence()) {
			
			super.getAgence().removeClient(this);
		}
		super.setAgence(agence);
		if(agence != null && ! super.getAgence().getClients().contains(this)) {
			
			agence.addClient(this);
		}
	}

	@Override
	public String toString() {
		return "Client [currentAccount=" + currentAccount + ", safeAccount=" + safeAccount + ", type=" + type
				+ ", isRitch=" + isRitch + " , toString()=" + super.toString() + "]";
	}

}
