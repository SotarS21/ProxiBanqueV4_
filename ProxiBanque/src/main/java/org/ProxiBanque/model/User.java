package org.ProxiBanque.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
@Table(name="USER")
public class User {
	
	@Column(name="LOGIN")
	private String login;
	
	@Column(name="PASSWORD")
	private String password;
	


	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	public User() {
		super();
	}
	
	
	
}
