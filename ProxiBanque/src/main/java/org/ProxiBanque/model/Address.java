package org.ProxiBanque.model;

public class Address {

	private String number;
	private String zipCode;
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
	
	
	
	
}
