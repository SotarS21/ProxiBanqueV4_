package org.ProxiBanque.model;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="DIRECTOR")
@DiscriminatorValue("D")
public class Director extends Person{
	
	@OneToOne(cascade=CascadeType.ALL)
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
	
	public void setUser(User user) {
		this.user = user;
		user.setDirector(this);
	}

	public Director() {
		super();
	}

	@Override
	public String toString() {
		return "Director [user=" + user + "]";
	}

	@Override
	public void setAgence(Agence agence) {
		
		super.setAgence(agence);
		if(agence != null && agence.getDirector() != this) {
			
			agence.setDirector(this);
		}
	}
	
	

}
