package org.ProxiBanque.presentation;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;

import org.ProxiBanque.model.Address;
import org.ProxiBanque.model.Advisor;
import org.ProxiBanque.model.Agence;
import org.ProxiBanque.model.Client;
import org.ProxiBanque.model.Client.e_ClientType;
import org.ProxiBanque.model.CurrentAccount;
import org.ProxiBanque.model.Director;
import org.ProxiBanque.model.SavingAccount;
import org.ProxiBanque.model.User;
import org.ProxiBanque.service.IServiceAdvisor;
import org.ProxiBanque.service.IServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@SessionScoped
public class UserController implements Serializable {

	/**
	 * @author Andy
	 */
	private static final long serialVersionUID = -9176116508320531060L;

	@Autowired
	IServiceUser serviceUser;

	@Autowired
	IServiceAdvisor serviceAdvisor;

	private User currentUser;

	@PostConstruct
	private void init() {

		currentUser = new User();
		
		// TODO : supprimer apr�s les tests
		User user = new User("login", "mdp");
		User user2 = new User("login2", "mdp2");
		Agence agence = new Agence();
		Director director = new Director("lol", "lol", new Address());
		director.setUser(user2);
		agence.setDirector(director);
		CurrentAccount current = new CurrentAccount(2000, 2000);
		SavingAccount saving = new SavingAccount(0.3, 5000);
		CurrentAccount current2 = new CurrentAccount(1000, 1000);
		SavingAccount saving2 = new SavingAccount(0.4, 10000);
		Client client = new Client("Bobinou", "Bobinou", new Address("12 rue des Lila", "94800", "Villejuif"));
		Client client2 = new Client("Bobinou", "Bobinou", new Address("12 rue des Lila", "94800", "Villejuif"));
		client.setCellphone("0101010101");
		client.setEmail("a@a.a");
		client.setType(e_ClientType.CASUAL_CLIENT);
		client2.setCellphone("0101010101");
		client2.setEmail("a@a.a");
		client2.setType(e_ClientType.CASUAL_CLIENT);
		client.setCurrentAccount(current);
		client.setSafeAccount(saving);
		client2.setCurrentAccount(current2);
		client2.setSafeAccount(saving2);
		Advisor advisor = new Advisor("Bob", "Bob", new Address());
		advisor.setCellphone("0101010101");
		advisor.setEmail("a@a.a");
		advisor.setUser(user);
		Advisor advisor2 = new Advisor("Bib", "Bib", new Address());
		advisor2.setUser(user2);
		agence.addAdvisor(advisor2);
		agence.addAdvisor(advisor);
		advisor.addClient(client);
		advisor2.addClient(client2);
		serviceAdvisor.save(advisor);
		serviceAdvisor.save(advisor2);
	}

	public String login() {

		User verifUser = serviceUser.findFirstByLoginAndPasswordAllIgnoreCase(currentUser.getLogin().trim(),
				currentUser.getPassword().trim());

		if (verifUser == null) {

			currentUser.setLogin("");
			currentUser.setPassword("");

			return "";
		} else {

			currentUser = verifUser;
			if (currentUser.getAdvisor() != null && currentUser.getDirector() == null) {

				return "listClient?sendredirect=true";
			} else if (currentUser.getDirector() != null && currentUser.getAdvisor() == null) {

				return "listAdvisor?sendredirect=true";
			}
		}
		return "";
	}

	public String logout() {

		currentUser = new User();
		return "index";
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

}
