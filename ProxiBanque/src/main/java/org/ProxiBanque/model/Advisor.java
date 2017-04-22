package org.ProxiBanque.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *	Bean Advisor regroupe les informations du conseiler  : Le user , La liste des clients  
 * 
 * @author Jonas, Kevin, Andy, Mathieu 
 *
 */

@Entity
@Table(name="ADVISOR")
@DiscriminatorValue("A")
public class Advisor extends Person{

	@OneToOne(cascade=CascadeType.ALL)
	private User user;
	
	@OneToMany(fetch=FetchType.EAGER, cascade={CascadeType.ALL}, mappedBy="advisor")
	private List<Client> clients = new ArrayList<Client>();
	
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
	public void setUser(User user) {
		this.user = user;
	}
	public List<Client> getClients() {
		return clients;
	}
	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
	public Advisor() {
		super();
	}
	
	@Override
	public String toString() {
		return "Advisor [user=" + user + ", clients=" + clients + "]";
	}
	
	/**
	 * Ajout un client dans la liste des clients du conseiller
	 * @param cl : client
	 */
	public void addClient(Client cl)
	{
		this.clients.add(cl);
		cl.setAdvisor(this);
	}
	public void removeClient(Client cl) {
		
		if(clients.contains(cl)) {
			
			clients.remove(cl);
			cl.setAdvisor(null);
		}
	}
}
