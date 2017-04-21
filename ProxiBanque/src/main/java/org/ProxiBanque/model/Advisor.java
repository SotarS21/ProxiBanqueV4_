package org.ProxiBanque.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="ADVISOR")
@DiscriminatorValue("A")
public class Advisor extends Person{

	@Embedded
	@Column(name="USER")
	private User user;
	
	@OneToMany(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy="advisor")
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
	public Advisor() {
		super();
	}
	@Override
	public String toString() {
		return "Advisor [user=" + user + ", clients=" + clients + "]";
	}
	
	
}
