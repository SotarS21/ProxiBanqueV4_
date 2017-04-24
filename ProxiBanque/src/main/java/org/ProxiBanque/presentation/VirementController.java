package org.ProxiBanque.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.ProxiBanque.model.Client;
import org.ProxiBanque.service.IServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * controller gérant une action de virement entre deux comptes
 * 
 * @author kevin, jonas, andy, matthieu
 *
 */
@Controller
@SessionScoped
public class VirementController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Client> listClient = new ArrayList<>();
	private List<Client> listFilter = new ArrayList<>();
	private static Logger LOGGER = LoggerFactory.getLogger(VirementController.class);
	

	@Autowired
	private IServiceClient serviceClient;


	public void setListFilter(List<Client> listFilter) {
		this.listFilter = listFilter;
	}

	public List<Client> getListClient() {
		return listClient;
	}

	public List<Client> getListFilter() {
		return listFilter;
	}

	@PostConstruct
	private void init() {

		serviceClient.init();
		// serviceAccount.init();
	}

	/**
	 * Charge la liste de tout les clients dans le controller afin de proposer le client bénéficiaire du virement
	 */
	public void loadClients() {
		listClient.clear();
		LOGGER.debug("VIREMENT Load all client in BDD");
		try {

			listClient = serviceClient.findAll();// findByConseiller_Id(3L);
			listFilter = serviceClient.findAll();
			LOGGER.info("Loading success");
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("all clients loading failure");
			notificationError(e, "all clients loading failure");
		}
	}

	

	/**
	 * Affiche un message pop up lors de la réussite d'une opération
	 * @param operation
	 */
	public void notificationSuccess(String operation) {

		LOGGER.info("Operation " + operation + " success");
		FacesMessage msg = null;
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notification", "Success");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * Affiche un message pop up lors de l'échec d'une opération
	 * @param operation
	 */
	public void notificationError(Exception e, String operation) {
		LOGGER.error("Operation " + operation + " Error ", e);
		FacesMessage msg = null;
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notification", "Une erreur est survenue");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
