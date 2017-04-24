package org.ProxiBanque.presentation;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Controller gérant le login des deux types d'utilisateurs : Conseiller ou Gérant.
 * @author jonas, kevin, matthieu, andy
 *
 */
@Controller
@SessionScoped
public class UserController implements Serializable {

	/**
	 * @author Andy
	 */
	private static final long serialVersionUID = -9176116508320531060L;
	private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	IServiceUser serviceUser;

	@Autowired
	IServiceAdvisor serviceAdvisor;

	private User currentUser;

	@PostConstruct
	private void init() {

		currentUser = new User();

		// TODO : supprimer aprï¿½s les tests
		User user = new User("login", "mdp");
		User user2 = new User("login2", "mdp2");
		Agence agence = new Agence();
		Director director = new Director("lol", "lol", new Address());
		director.setUser(user2);
		agence.setDirector(director);
		CurrentAccount current = new CurrentAccount(2000, 2000);
		SavingAccount saving = new SavingAccount(0.3, 5000);
		CurrentAccount current2 = new CurrentAccount(1000, -1000);
		SavingAccount saving2 = new SavingAccount(0.4, 1000000);
		Client client = new Client("Bobinoux", "Bobinou", new Address("12 rue des Lila", "94800", "Villejuif"));
		Client client2 = new Client("Bobinette", "Bobina", new Address("12 rue des Lila", "94800", "Villejuif"));
		client.setCellphone("0101010101");
		client.setEmail("a@a.a");
		client.setType(e_ClientType.CASUAL_CLIENT);
		client2.setCellphone("0101010101");
		client2.setEmail("a@a.a");
		client2.setType(e_ClientType.CASUAL_CLIENT);
		client.setCurrentAccount(current);
		client.setSafeAccount(saving);
		//client2.setCurrentAccount(current2);
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

	/**
	 * Vérifie les identifiants de connection fournit, et redirige vers une page selon la nature de l'utilisateur
	 * @return
	 */
	public String login() {

		User verifUser = serviceUser.findFirstByLoginAndPasswordAllIgnoreCase(currentUser.getLogin().trim(),
				currentUser.getPassword().trim());

		if (verifUser == null) {

			currentUser.setLogin("");
			currentUser.setPassword("");
			LOGGER.info("connexion identifier failure");
			notificationSuccess("Wrong login or password");

			return "";
		} else {

			currentUser = verifUser;
			FacesContext ctx = FacesContext.getCurrentInstance();
			   ExternalContext extCtx = ctx.getExternalContext();
			   Map<String, Object> sessionMap = extCtx.getSessionMap();
//			   sessionMap.put("advisor", currentUser.getAdvisor());
			   
			if (currentUser.getAdvisor() != null && currentUser.getDirector() == null) {
				if (sessionMap.get("director") != null)
					sessionMap.remove("director");
				sessionMap.put("advisor", currentUser.getAdvisor());
				LOGGER.info("user logged as Advisor");
				notificationSuccess("successfully logged as Advisor");
				return "accueilAdvisor?sendredirect=true";
			} else  {
				if (sessionMap.get("advisor") != null)
					sessionMap.remove("advisor");
				sessionMap.put("director", currentUser.getDirector());
				LOGGER.info("user logged as Director");
				notificationSuccess("successfully logged as Director");
				return "accueilDirector?faces-redirect=true";
			}
		}
		
	}

	/**
	 * Cette fonction permet de déconnecter l'utilisateur courant
	 * @return
	 */
	public String logout() {

		currentUser = new User();
		LOGGER.info("user logout");
		notificationSuccess("successfully logged out");
		return "index";
	}

	public void notificationSuccess(String operation) {

		LOGGER.info("Operation " + operation + " success");
		FacesMessage msg = null;
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notification", "Success");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void notificationError(Exception e, String operation) {
		LOGGER.error("Operation " + operation + " Error ", e);
		FacesMessage msg = null;
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notification", "Une erreur est survenue");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

}
