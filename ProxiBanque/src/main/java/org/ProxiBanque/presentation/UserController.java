package org.ProxiBanque.presentation;

import java.io.Serializable;

import javax.faces.bean.SessionScoped;

import org.ProxiBanque.model.User;
import org.springframework.stereotype.Controller;

@Controller
@SessionScoped
public class UserController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9176116508320531060L;
	
	private User currentUser;

	public String login() {
		
		return null;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	
}
