package org.ProxiBanque.presentation;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.ProxiBanque.model.Advisor;
import org.ProxiBanque.model.Client;
import org.ProxiBanque.model.Client.e_ClientType;
import org.ProxiBanque.service.IServiceAgence;
import org.ProxiBanque.service.IServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Classe de contr�ler permettant d'effectuer des actions sur le client dans nos
 * pages xhtml
 * 
 * @author kevin jonas
 *
 */

@Controller
@SessionScoped
public class addClientController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private static Logger LOGGER = LoggerFactory.getLogger(addClientController.class);
	private String typeClient;
	private Client client;
	private Client clientPro;
	
	@Autowired
	private IServiceClient serviceClient;

	@Autowired
	private IServiceAgence serviceAgence;
	
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

	public Client getClientPro() {
		return clientPro;
	}

	public void setClientPro(Client clientPro) {
		this.clientPro = clientPro;
	}
	
	public String addClient(Client client){
		LOGGER.debug("addClient");
		 FacesContext ctx = FacesContext.getCurrentInstance();
		   ExternalContext extCtx = ctx.getExternalContext();
		   Map<String, Object> sessionMap = extCtx.getSessionMap();
		   LOGGER.debug("advisor" +sessionMap.get("advisor"));
		if(typeClient.equals("particulier")){
			client.setType(e_ClientType.CASUAL_CLIENT);
			client.setAdvisor((Advisor) sessionMap.get("advisor"));
		   client.setAgence(serviceAgence.findOne(2L));
		   serviceClient.save(client);}
		else if(typeClient.equals("professionnel")){
			clientPro.setType(e_ClientType.CASUAL_CLIENT);
		   	clientPro.setAdvisor((Advisor) sessionMap.get("advisor"));
		   	clientPro.setAgence(serviceAgence.findOne(2L));
		    serviceClient.save(clientPro);
		}
		return "listClient";
		
	}

	
	

	
}
