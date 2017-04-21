package org.ProxiBanque.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="DIRECTOR")
@DiscriminatorValue("D")
public class Director extends Person{
	
	@Embedded
	@Column(name="USER")
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

	public Director() {
		super();
	}

	@Override
	public String toString() {
		return "Director [user=" + user + "]";
	}

	
	
	

}
