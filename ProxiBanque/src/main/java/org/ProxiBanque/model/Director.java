package org.ProxiBanque.model;

public class Director extends Person{
	
	private User user;

	public Director(String firstName, String lastName, Address address, User user) {
		super(firstName, lastName, address);
		this.user = user;
	}

	public Director(String firstName, String lastName, Address address) {
		super(firstName, lastName, address);
	}

	public User getUser() {
		return user;
	}

	
	
	

}
