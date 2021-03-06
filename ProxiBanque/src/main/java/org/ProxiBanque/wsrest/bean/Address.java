package org.ProxiBanque.wsrest.bean;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *	Bean Address regroupe les informations d'adresse : le num�ro, le code postale et la ville 
 * 
 * @author Jonas, Kevin, Andy, Mathieu 
 *
 */

@XmlRootElement
public class Address {

	@Column(name="NUMBER")
	private String number = "null";
	
	@Column(name="ZIPCODE")
	private String zipCode = "null";
	
	@Column(name="TOWN")
	private String town = "null";
	
	public Address(String number, String zipCode, String town) {
		super();
		this.number = number;
		this.zipCode = zipCode;
		this.town = town;
	}

	public Address() {
		super();
	}

	/**
	 * Retourne le num�ro de rue de l'adresse
	 * @return le num�ro de l'adresse sous forme de string
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * C
	 * @param number : nouvel value
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * Retourne le code postale de rue de l'adresse
	 * @return le code postale sous forme de string
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * Retourne le num�ro de rue de l'adresse
	 * @param zipCode
	 */
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
