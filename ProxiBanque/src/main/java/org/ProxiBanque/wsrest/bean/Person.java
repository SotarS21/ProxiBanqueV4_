package org.ProxiBanque.wsrest.bean;

import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement
public class Person {
	
	private long id;
	
	private String firstName = "null";
	
	private String email = "null";
	
	private String cellphone = "null";
	
	
	private String lastName = "null";
	
	private Address address = new Address();
	
	public Person(String firstName, String email, String cellphone, String lastName, Address address) {
		super();
		this.firstName = firstName;
		this.email = email;
		this.cellphone = cellphone;
		this.lastName = lastName;
		this.address = address;
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
	
}
