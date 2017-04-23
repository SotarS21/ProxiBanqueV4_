package org.ProxiBanque.wsrest.bean;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Agence {

	private long id;
	
	private String dateCreation = DateFormat.getDateInstance().toString();
	
	private List<Advisor> advisors = new ArrayList<Advisor>();
	
	private Director director;
	
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
	}
	
	public void addAdvisor(Advisor advisor) {
		
		if(! advisors.contains(advisor)) {
			
			advisors.add(advisor);
		}
	}
	
	public void removeAdvisor(Advisor advisor) {
		
		if(advisors.contains(advisor)) {
			
			advisors.remove(advisor);
		}
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
	
}
