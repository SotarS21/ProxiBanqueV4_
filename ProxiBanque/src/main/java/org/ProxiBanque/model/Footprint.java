package org.ProxiBanque.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
//@Table(name="FOOTPRINT")
public class Footprint {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private e_HeadType head;
	private String body;
	private e_State foot;
	private String dateCreation;
	
	public enum e_HeadType{
		TRANSACTION,
		CLENT,
		ADVISOR
	};
	
	public enum e_State
	{
		SUCCESS,
		WAITING,
		FAIL,
		CANCELED
	}

	public Footprint(e_HeadType head, String  body, e_State foot) {
		super();
		this.body = body;
		this.head = head;
		this.foot = foot;
		Date date = new Date();
		this.dateCreation = new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	public e_HeadType getHead() {
		return head;
	}

	public void setHead(e_HeadType head) {
		this.head = head;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Footprint() {
		super();
	}

	public long getId() {
		return id;
	}



	public e_State getFoot() {
		return foot;
	}

	public void setFoot(e_State foot) {
		this.foot = foot;
	}

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	@Override
	public String toString() {
		return "Footprint [id=" + id + ", head=" + head + ", body=" + body + ", foot=" + foot + ", dateCreation="
				+ dateCreation + "]";
	};
	
	
	
}
