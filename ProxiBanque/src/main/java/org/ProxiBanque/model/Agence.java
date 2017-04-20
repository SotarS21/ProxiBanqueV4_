package org.ProxiBanque.model;

import java.text.DateFormat;
import java.util.Collection;

public class Agence {

	private long id;
	private DateFormat dateCreation;
	private Collection<Advisor> advisors;
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
