package org.ProxiBanque.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {

	@Column(name="NUMBER")
	private String number;
	
	@Column(name="ZIPCODE")
	private String zipCode;
	
	@Column(name="TOWN")
	private String town;
	
	public Address(String number, String zipCode, String town) {
		super();
		this.number = number;
		this.zipCode = zipCode;
		this.town = town;
	}

	public Address() {
		super();
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	@Override
	public String toString() {
		return "Address [number=" + number + ", zipCode=" + zipCode + ", town=" + town + "]";
	}
	
	
	
	
}
