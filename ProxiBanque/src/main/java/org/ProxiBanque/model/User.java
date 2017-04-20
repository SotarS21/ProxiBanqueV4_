package org.ProxiBanque.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Embeddable
@Table(name="USER")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="IDUSER")
	private Long idUser = 1L;
	
	@Column(name="LOGIN")
	private String login;
	
	@Column(name="PASSWORD")
	private String password;
	
	

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

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
