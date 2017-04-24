package org.ProxiBanque.model;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="AGENCE")
public class Agence {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private long id;
	
	@Column(name="DATECREATION")
	private String dateCreation = DateFormat.getDateInstance().toString();
	
	@OneToMany(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.REFRESH})
	@Column(name="ADVISORS")
	private List<Advisor> advisors = new ArrayList<Advisor>();
	
	@OneToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.REFRESH})
	private Director director;
	
	@OneToMany(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.REFRESH})
	@Column(name="CLIENTS")
	private List<Client> clients = new ArrayList<Client>(); 
	
	
	public String getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<Advisor> getAdvisors() {
		return advisors;
	}
	public void setAdvisors(List<Advisor> advisors) {
		this.advisors = advisors;
	}
	
	public Director getDirector() {
		return director;
	}
	public void setDirector(Director director) {
		
		this.director = director;
		if(director != null && director.getAgence() != this) {
			
			director.setAgence(this);
		}
	}
	public List<Client> getClients() {
		return clients;
	}
	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
	
	public void addClient(Client client) {
		
		if(! clients.contains(client)) {
			
			clients.add(client);
			client.setAgence(this);
		}
	}
	
	public void removeClient(Client client) {
		
		if(clients.contains(client)) {
			
			clients.remove(client);
			client.setAgence(null);
		}
	}
	
	public void addAdvisor(Advisor advisor) {
		
		if(! advisors.contains(advisor)) {
			
			advisors.add(advisor);
			advisor.setAgence(this);
			for (Client client : advisor.getClients()) {
				
				addClient(client);
			}
		}
	}
	
	public void removeAdvisor(Advisor advisor) {
		
		if(advisors.contains(advisor)) {
			
			advisors.remove(advisor);
			advisor.setAgence(null);
		}
//		advisor.setAgence(null);
//		for (Client client : advisor.getClients()) {
//			
//			client.setAgence(null);
//		}
	}
	
	
	public Agence(String dateCreation) {
		super();
		this.dateCreation = dateCreation;
	}
	public Agence() {
		super();
	}
	public long getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Agence [id=" + id + ", dateCreation=" + dateCreation + ", advisors=" + advisors + ", director="
				+ director + ", clients=" + clients + "]";
	}
	
	
}
