package org.ProxiBanque.wsrest.bean;

import javax.xml.bind.annotation.XmlRootElement;

import org.ProxiBanque.model.Client.e_ClientType;

/**
 * Bean Client regroupe les informations du client : le compte courant, le
 * compte épargne; son type , s'il est riche, son conseiller
 * 
 * @author Jonas, Kevin, Andy, Mathieu
 *
 */

@XmlRootElement
public class Client extends Person {

	private CurrentAccount currentAccount;

	private SavingAccount safeAccount;

	private e_ClientType type = e_ClientType.CASUAL_CLIENT;

	private boolean isRitch = false;

	public Client() {
		super();
	}

	public Client(String firstName, String lastName, Address address, e_ClientType e_ClientType, boolean isRitch) {
		super(firstName, lastName, address);
		this.type = e_ClientType;
		this.isRitch = isRitch;
	}

	public Client(String firstName, String lastName, Address address) {
		super(firstName, lastName, address);
	}

	public CurrentAccount getCurrentAccount() {
		return currentAccount;
	}

	public void setCurrentAccount(CurrentAccount currentAccount) {
		this.currentAccount = currentAccount;
	}

	public SavingAccount getSafeAccount() {
		return safeAccount;
	}

	public void setSafeAccount(SavingAccount safeAccount) {
		this.safeAccount = safeAccount;
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

}