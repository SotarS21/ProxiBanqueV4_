package org.ProxiBanque.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;

@Entity
@Inheritance
public abstract class Person {

	
	enum e_PersonType{
		CLIENT,
		ADVISOR,
		DIRECTOR;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private long id;
	
	@Column(name="FIRSTNAME")
	
	private String firstName;
	
	@Column(name="LASTNAME")
	
	private String lastName;
	
	@Embedded
	@Column(name="ADRESSE")
	private Address address;
	
//	@DiscriminatorColumn(discriminatorType=e_PersonType.class)
	private e_PersonType type;
	
	public Person(String firstName, String lastName, Address address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
	}
	public Person(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public Person() {
		super();
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public long getId() {
		return id;
	}
	
	
}
