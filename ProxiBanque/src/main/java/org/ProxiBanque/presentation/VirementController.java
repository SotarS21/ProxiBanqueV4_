package org.ProxiBanque.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.ProxiBanque.exception.VirementException;
import org.ProxiBanque.model.BankAccount;
import org.ProxiBanque.model.BankAccount.e_AccountType;
import org.ProxiBanque.model.Client;
import org.ProxiBanque.service.IServiceAccount;
import org.ProxiBanque.service.IServiceClient;
import org.primefaces.event.RowEditEvent;
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

}
