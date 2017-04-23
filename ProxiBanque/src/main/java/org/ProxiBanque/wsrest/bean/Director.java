package org.ProxiBanque.wsrest.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Director extends Person{
	
	public Director(String firstName, String lastName, Address address) {
		super(firstName, lastName, address);
	}

	public Director() {
		super();
	}
}
