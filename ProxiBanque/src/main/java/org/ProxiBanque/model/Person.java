package org.ProxiBanque.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="TYPEPERSON")
@Table(name="PERSON")
public class Person {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private long id;
	
	@Column(name="FIRSTNAME")
	private String firstName;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="CELLPHONE")
	private String cellphone;
	
	
	@Column(name="LASTNAME")
	private String lastName;
	
	@Embedded
	@Column(name="ADRESSE")
	private Address address;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	private Agence agence;
	
	
	public Person(String firstName, String email, String cellphone, String lastName, Address address, Agence agence) {
		super();
		this.firstName = firstName;
		this.email = email;
		this.cellphone = cellphone;
		this.lastName = lastName;
		this.address = address;
		this.agence = agence;
	}
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
	
	public void setId(long id) {
		this.id = id;
	}
	public Agence getAgence() {
		return agence;
	}
	public void setAgence(Agence agence) {
		
		this.agence = agence;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", email=" + email + ", cellphone=" + cellphone
				+ ", lastName=" + lastName + ", address=" + address + "]";
	}
	
}
