package org.ProxiBanque.wsrest.bean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *	Bean Advisor regroupe les informations du conseiler  : Le user , La liste des clients  
 * 
 * @author Jonas, Kevin, Andy, Mathieu 
 *
 */


@XmlRootElement
public class Advisor extends Person {
	
	private List<Client> clients = new ArrayList<Client>();
	
	public Advisor(String firstName, String lastName, Address address) {
		super(firstName, lastName, address);
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
	
	
	/**
	 * Ajout un client dans la liste des clients du conseiller
	 * @param cl : client
	 */
	public void addClient(Client cl)
	{
		this.clients.add(cl);
	}
	public void removeClient(Client cl) {
		
		if(clients.contains(cl)) {
			
			clients.remove(cl);
		}
	}
}
