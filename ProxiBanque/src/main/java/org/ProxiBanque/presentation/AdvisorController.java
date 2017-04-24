package org.ProxiBanque.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.ProxiBanque.model.Advisor;
import org.ProxiBanque.model.BankAccount;
import org.ProxiBanque.model.Client;
import org.ProxiBanque.service.IServiceAdvisor;
import org.ProxiBanque.service.IServiceClient;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Classe de contrôler permettant d'effectuer des actions sur le client dans nos
 * pages xhtml
 * 
 * @author kevin jonas
 *
 */

@Controller
@SessionScoped
public class AdvisorController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3609975386276172308L;
	private static List<Advisor> listAdvisor = new ArrayList<>();
	private static Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

	@Autowired
	IServiceAdvisor serviceAdvisor;

	@Autowired
	IServiceClient serviceClient;

	public List<Advisor> getListAdvisor() {
		return listAdvisor;
	}

	/**
	 * Récupérer la liste des clients pour un conseiller sélectionner
	 * 
	 * @param adviser
	 *            : le conseiller
	 * @return List : renvois la liste des clients
	 */
	public Collection<Client> loadClients(Advisor advisor) {
		LOGGER.debug("Load Clients");
		return advisor.getClients();
	}

	/**
	 * re-charge la liste des conseillers
	 */
	public void loadAdvisors() {
		listAdvisor.clear();
		try {
			listAdvisor = serviceAdvisor.findAll();
			for (Advisor advisor : listAdvisor) {
				for (Client c : advisor.getClients()) {
					LOGGER.warn(c.getFirstName());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Charger la page de création d'un client
	 * 
	 * @param adviser
	 *            : sauvegarder le conseiller pour le récupèrer dans la création
	 *            du client
	 * @return la page de destination. S'il y a une erreur dans la méthode il
	 *         restera sur la page en cours
	 */
	public String addClient(Advisor advisor) {
		LOGGER.debug("advisor " + advisor.getFirstName());
		try {
			ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> requestMap = ext.getSessionMap();
			requestMap.put("adviser", advisor);
		} catch (Exception e) {
			System.err.println("error");
			return null;
		}

		return "ajoutClient";
	}

	public String addClient(Client newClient) {
		LOGGER.debug("CREATE CLIENT");
		if (newClient == null) {
			LOGGER.error("client  modify = NULL");
			notificationSuccess("client not created");
			return null;
		}
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		Advisor advisor = (Advisor) sessionMap.get("advisor");
		System.out.println("name adviser" + advisor.getFirstName());
		newClient.setAdvisor(advisor);
		serviceClient.save(newClient);
		
		LOGGER.info("client creation success");
		notificationSuccess("client creation");
		
		return "listClient";
	}

	/**
	 * Appelle le service qui modifie un conseiller et le met a jour en base de
	 * données.
	 * 
	 * @param adviser
	 *            : récupère le conseiller à modifier
	 * @return la page de destination. S'il y a une erreur dans la méthode il
	 *         restera sur la page en cours
	 */
	public String updateAdvisor(Advisor advisor) {
		LOGGER.info("Update Advisor!");
		try {

			serviceAdvisor.save(advisor);
			LOGGER.info("advisor updated");
			notificationSuccess("advisor updated");
		} catch (Exception e) {
			
			LOGGER.error("advisor update failure");
			notificationError(e, "advisor update");
			return "";
		}
		return "listAdvisor";
	}

	/**
	 * Appelle le service qui supprime un conseiller
	 * 
	 * @param id
	 *            : identifiant du conseiller à suprimmer
	 * @return la page de destination. S'il y a une erreur dans la méthode il
	 *         restera sur la page en cours
	 */
	public String deleteAdvisor(long id) {
		LOGGER.info("Delete adviser!");
		try {
			serviceAdvisor.delete(id);
			LOGGER.info("advisor deleted");
			notificationSuccess("advisor deleted");
		} catch (Exception e) {
			
			LOGGER.error("deleting Advisor failure");
			notificationError(e, "deleting Advisor");
			return "";
		}
		return "listAdvisor";
	}

	public void cancel(RowEditEvent event) {
		LOGGER.info("Cancel modification!");
		notificationSuccess("cancelled event : " + event);
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
	
	
	public double sumSold(Client client) {
		 double sumSold = 0;
		if (!(client.getSafeAccount() == null)) {

			sumSold = client.getSafeAccount().getSold();
		}
		if (!(client.getCurrentAccount() == null)) {

			sumSold = client.getCurrentAccount().getSold();
		}
		return sumSold;
	}
	
}
