package org.ProxiBanque.model;

import java.text.DateFormat;
import java.util.Collection;

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
	private DateFormat dateCreation;
	
	@OneToMany(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.REFRESH})
	@Column(name="ADVISORS")
	private Collection<Advisor> advisors;
	
	@OneToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.REFRESH})
	private Director director;
	
	@OneToMany(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.REFRESH})
	@Column(name="CLIENTS")
	private Collection<Client> clients; 
	
	public DateFormat getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(DateFormat dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Collection<Advisor> getAdvisors() {
		return advisors;
	}
	public void setAdvisors(Collection<Advisor> advisors) {
		this.advisors = advisors;
	}
	public Agence(DateFormat dateCreation) {
		super();
		this.dateCreation = dateCreation;
	}
	public Agence() {
		super();
	}
	public long getId() {
		return id;
	}
	
	
}
