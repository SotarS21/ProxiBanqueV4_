package org.ProxiBanque.presentation;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;

import org.ProxiBanque.model.Address;
import org.ProxiBanque.model.Advisor;
import org.ProxiBanque.model.Agence;
import org.ProxiBanque.model.Client;
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

		// TODO : supprimer après les tests
		User user = new User("login", "mdp");
		Advisor advisor = new Advisor("Bob", "Bob", new Address(), user);
		Client client = new Client("Boub", "Boub", new Address());
		Agence agence = new Agence();
		advisor.setAgence(agence);
		advisor.addClient(client);

		serviceAdvisor.save(advisor);
		System.out.println(serviceAdvisor.findOne(advisor.getId()).getClients().get(0));

		client.setLastName("test");
		client.setAddress(new Address("10 rue lila", "92810", "Sarcelles"));
		advisor.setCellphone("0000000000000");
		System.out.println(client);
		
		serviceAdvisor.update(advisor);
		System.out.println(serviceAdvisor.findOne(advisor.getId()).getClients().get(0));

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
