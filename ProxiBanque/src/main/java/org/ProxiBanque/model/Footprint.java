package org.ProxiBanque.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Footprint <T>{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private T element;
	private String message;
	
	public T getElement() {
		return element;
	}
	public void setElement(T element) {
		this.element = element;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
