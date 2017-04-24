package org.ProxiBanque.presentation;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;

import org.ProxiBanque.model.Address;
import org.ProxiBanque.model.Advisor;
import org.ProxiBanque.model.Client;
import org.ProxiBanque.model.Client.e_ClientType;
import org.ProxiBanque.service.IServiceAdvisor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *Controller permettant d'ajouter des clients à l'utilisateur courant
 * 
 * @author kevin jonas
 *
 */

@Controller
@SessionScoped
public class AddClientController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger LOGGER = LoggerFactory.getLogger(AddClientController.class);
	private String typeClient;
	private Client client;
	private Client clientPro;

	@Autowired
	private IServiceAdvisor serviceAdvisor;

	@Autowired
	private UserController userController;

	@PostConstruct
	public void init() {
		clientPro = new Client("", "", new Address("", "", ""));
		client = new Client("", "", new Address("", "", ""));
		typeClient = "particulier";
	}

	public Client getClientPro() {
		return clientPro;
	}

	public void setClientPro(Client clientPro) {
		this.clientPro = clientPro;
	}

	public String getTypeClient() {
		return typeClient;
	}

	public void setTypeClient(String typeClient) {
		this.typeClient = typeClient;
	}

	public Client getClient() {
		LOGGER.debug("GetClient");
		return client;
	}

	public void setClient(Client client) {
		LOGGER.debug("setClient");
		this.client = client;
	}

	/**
	 * Fonction ajoutant un client au conseiller contenu dans ce controller,
	 * selon le type d'utilisateur spécifié dans la page JSF correspondante.
	 * @return
	 */
	public String addClient() {
		LOGGER.debug("addClient");
		// FacesContext ctx = FacesContext.getCurrentInstance();
		// ExternalContext extCtx = ctx.getExternalContext();
		// Map<String, Object> sessionMap = extCtx.getSessionMap();
		// Advisor advisor = (Advisor) sessionMap.get("advisor");4
		Advisor advisor = userController.getCurrentUser().getAdvisor();

		LOGGER.debug("advisor" + advisor);
		if (typeClient.equals("particulier")) {
			client.setType(e_ClientType.CASUAL_CLIENT);
			advisor.addClient(client);
			serviceAdvisor.save(advisor);
		} else if (typeClient.equals("professionnel")) {
			clientPro.setType(e_ClientType.ENTERPRISE_CLIENT);
			clientPro.setFirstName("Entreprise :");
			advisor.addClient(clientPro);
			serviceAdvisor.save(advisor);
		}

		return "listClient";
	}
	
	public String annule(){
		clientPro = new Client("", "", new Address("", "", ""));
		client = new Client("", "", new Address("", "", ""));
		return "listClient?faces-redirect=true";
	}

}
