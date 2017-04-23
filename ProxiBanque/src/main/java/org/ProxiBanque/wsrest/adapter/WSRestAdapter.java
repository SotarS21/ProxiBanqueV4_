package org.ProxiBanque.wsrest.adapter;

import org.ProxiBanque.wsrest.bean.Address;
import org.ProxiBanque.wsrest.bean.Advisor;
import org.ProxiBanque.wsrest.bean.Client;
import org.ProxiBanque.wsrest.bean.CurrentAccount;
import org.ProxiBanque.wsrest.bean.Director;
import org.ProxiBanque.wsrest.bean.SavingAccount;

public abstract class WSRestAdapter {

	public static CurrentAccount marshallCurrentAccount(org.ProxiBanque.model.CurrentAccount current) {

	}

	public static org.ProxiBanque.model.CurrentAccount unmarshallCurrentAccount(CurrentAccount current) {

	}

	public static SavingAccount marshallSavingAccount(org.ProxiBanque.model.SavingAccount saving) {

	}

	public static org.ProxiBanque.model.SavingAccount marshallSavingAccount(SavingAccount saving) {

	}

	public static Address marshallAddress(org.ProxiBanque.model.Address address) {

	}

	public static org.ProxiBanque.model.Address marshallAddress(Address address) {

	}
	
	public static Client marshallUser(org.ProxiBanque.model.Client client) {

	}

	public static org.ProxiBanque.model.Client marshallAddress(Client client) {

	}
	
	public static Director marshallUser(org.ProxiBanque.model.Director director) {

	}

	public static org.ProxiBanque.model.Director marshallAddress(Director director) {

	}
	
	public static Advisor marshallUser(org.ProxiBanque.model.Advisor advisor) {

	}

	public static org.ProxiBanque.model.Advisor marshallAddress(Advisor advisor) {

	}
}
