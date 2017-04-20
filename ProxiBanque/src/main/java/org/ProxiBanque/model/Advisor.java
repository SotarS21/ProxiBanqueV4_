package org.ProxiBanque.model;

import java.util.Collection;

public class Advisor extends Person{

	private User user;
	private Collection<Client> clients;
	public Advisor(String firstName, String lastName, Address address, User user) {
		super(firstName, lastName, address);
		this.user = user;
	}
	public Advisor(String firstName, String lastName, Address address) {
		super(firstName, lastName, address);
	}
	public User getUser() {
		return user;
	}
	public Collection<Client> getClients() {
		return clients;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
