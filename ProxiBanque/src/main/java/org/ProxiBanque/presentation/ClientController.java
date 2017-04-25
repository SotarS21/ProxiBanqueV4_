package org.ProxiBanque.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.ProxiBanque.exception.VirementException;
import org.ProxiBanque.model.Advisor;
import org.ProxiBanque.model.BankAccount;
import org.ProxiBanque.model.BankAccount.e_AccountType;
import org.ProxiBanque.model.Client;
import org.ProxiBanque.model.Virement;
import org.ProxiBanque.service.IServiceAccount;
import org.ProxiBanque.service.IServiceClient;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Classe de contrï¿½ler permettant d'effectuer des actions sur le client dans
 * nos pages xhtml
 * 
 * @author kevin jonas
 *
 */

@Controller
@SessionScoped
public class ClientController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BankAccount bankAccount;
	private Client clientContr;
	private Virement virement;
	private List<Client> listClient = new ArrayList<>();
	private List<Client> listFilter = new ArrayList<>();
	private static Logger LOGGER = LoggerFactory.getLogger(ClientController.class);
	private String typeClient;

	@Autowired
	private IServiceClient serviceClient;
	@Autowired
	private IServiceAccount serviceAccount;

	public Client getClientContr() {
		return clientContr;
	}

	public void setClientContr(Client clientContr) {
		this.clientContr = clientContr;
	}

	public Virement getVirement() {
		return virement;
	}

	public void setVirement(Virement virement) {
		this.virement = virement;
	}

	public String getTypeClient() {
		return typeClient;
	}

	public void setTypeClient(String typeClient) {
		this.typeClient = typeClient;
	}

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
		virement = new Virement(0, this.bankAccount);
	}

	public void loadClients() {
		listClient.clear();
		LOGGER.debug("Load all client in BDD");
		try {
			FacesContext ctx = FacesContext.getCurrentInstance();
			ExternalContext extCtx = ctx.getExternalContext();
			Map<String, Object> sessionMap = extCtx.getSessionMap();
			Advisor advisor = (Advisor) sessionMap.get("advisor");
			listClient = serviceClient.findByConseiller_Id(advisor.getId());
			listFilter = serviceClient.findByConseiller_Id(advisor.getId());
			LOGGER.info("Loading success");
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("all clients loading failure");
			notificationError(e, "all clients loading failure");
		}
	}

	/**
	 * Appelle le service pour effectuer une mise ï¿½ jour sur la basse de
	 * donnï¿½es
	 * 
	 * @param client
	 *            : rï¿½cupï¿½re le client a modifier
	 * @return la page de destination. Si erreur dans la mï¿½thode il restera
	 *         sur la page en cours
	 */
	public String updateClient(Client client) {
		LOGGER.debug("Update Client!");
		try {
			serviceClient.update(client);

		} catch (Exception e) {
			return null;
		}
		return "listClient?faces-redirect=true";
	}

	/**
	 * Appelle le service pour supprimer le client dans la basse de donnï¿½es
	 * 
	 * @param id
	 *            : identifiant du client
	 * @return la page de destination. Si erreur dans la mï¿½thode il restera
	 *         sur la page en cours
	 */
	public String deleteClient(long id) {
		LOGGER.info("Delete CLient!");
		try {
			Client client1 = serviceClient.findOne(id);
			client1.setAdvisor(null);

			serviceClient.save(client1);
			serviceClient.delete(client1.getId());
			LOGGER.info("client n° " + id + "deleted");
			notificationSuccess("client n° " + id + "deleted");
			return "listClient?faces-redirect=true";
		} catch (Exception e) {

			LOGGER.error("client n° " + id + "error deleting");
			notificationError(e, "client n° " + id + "deleting");
			return null;
		}

	}

	public List<BankAccount> accountLoad(Client client) {
		List<BankAccount> listAccount = new ArrayList<>();
		if (!(client.getSafeAccount() == null)) {

			listAccount.add(client.getSafeAccount());
		}
		if (!(client.getCurrentAccount() == null)) {

			listAccount.add(client.getCurrentAccount());
		}
		return listAccount;
	}

	/**
	 * Appelle le service pour supprimer le compte dans la basse de donnï¿½es
	 * 
	 * @param idClient
	 *            : identifiant du client
	 * @param account
	 *            : compte ï¿½ supprimer
	 * @return la page de destination. Si erreur dans la mï¿½thode il restera
	 *         sur la page en cours
	 */

	public void cancel(RowEditEvent event) {
		LOGGER.info("Cancel modification!");
		notificationSuccess("operation cancelled");
	}

	/**
	 * Appelle le service pour crï¿½er un compt ï¿½pargne au client dans la
	 * basse de donnï¿½es
	 * 
	 * @param client
	 *            : envois le client sur lequel crï¿½er un compte ï¿½pargne
	 * @return la page de destination. Si erreur dans la mï¿½thode il restera
	 *         sur la page en cours
	 */
	public String addSavingAccount(Client client) {
		try {
			serviceAccount.addAccount(client, e_AccountType.SAVING_ACCOUNT);
			LOGGER.info("saving account added");
			notificationSuccess("saving account added");
		} catch (Exception e) {

			LOGGER.info("error adding account : " + e.getMessage());
			notificationError(e, "adding saving account");
			return "";
		}
		return "listClient?faces-redirect=true";
	}

	/**
	 * Appelle le service pour crï¿½er un compt courrant au client dans la basse
	 * de donnï¿½es
	 * 
	 * @param client
	 *            : envois le client sur lequel crï¿½er un compte courrant
	 * @return la page de destination. Si erreur dans la mï¿½thode il restera
	 *         sur la page en cours
	 */
	public String addCurrentAccount(Client client) {
		LOGGER.debug("add CurrentAccount");
		try {
			serviceAccount.addAccount(client, e_AccountType.CURRENT_ACCOUNT);
			notificationSuccess("account creation");
		} catch (Exception e) {

			LOGGER.error("error virement:" + e.getMessage());
			notificationError(e, "account creation");
			return "";
		}
		return "listClient?faces-redirect=true";
	}

	public String forwardToVirement(Client client, BankAccount account) {

		bankAccount = account;
		clientContr = client;
		return "virement?faces-redirect=true";
	}

	public String doVirement(BankAccount account) {

		try {
			virement.setAccount(account);

			if (virement.getValue() > 0)
				serviceAccount.doVirement(bankAccount, account, virement.getValue());

			notificationSuccess("virement");
		} catch (VirementException e) {

			LOGGER.error(e.getMessage());
			notificationError(e, "virement");
		}
		return "listClient";
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

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String decouvertColor(Client client) {
		double solds = 0;
		double sold = 0;
		if (client.getCurrentAccount() != null) {
			solds = client.getCurrentAccount().getSold();
			sold = client.getCurrentAccount().getSold();
		}

		if (client.getSafeAccount() != null)
			solds = solds + client.getSafeAccount().getSold();

		if (sold < 0)
			return "decouvert";
		else if (solds > 500000)
			return "isRich";
		else
			return "";
	}

	public String styleCurrentAccountFull(Client client) {

		if (client.getCurrentAccount() != null)
			return "btn accountFull";
		else
			return "btn btn-success";
	}

	public String styleAdvisorFull(Advisor advisor) {

		if (advisor.getClients().size() < 10)
			return "btn btn-success";
		else
			return "btn accountFull";
	}

	public String styleSavingAccountFull(Client client) {

		if (client.getSafeAccount() != null)
			return "btn accountFull";
		else
			return "btn btn-success";
	}

	public String advisorNumber() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		ExternalContext extCtx = ctx.getExternalContext();
		Map<String, Object> sessionMap = extCtx.getSessionMap();
		Advisor advisor = (Advisor) sessionMap.get("advisor");
		if (advisor.getClients().size() <= 10) {
			int size = advisor.getClients().size();
			LOGGER.debug("-------------------------"+ size);
			return "Nombre de client : " + Integer.toString(size);
		} else
			return "Le conseiller a déjà 10 client";

	}

	public String decouvertColor(Advisor advisor) {
		double solds = 0;
		double sold = 0;
		int flag = 0;
		List<Client> clients = advisor.getClients();
		for (Client client : clients) {
			if (client.getCurrentAccount() != null) {
				solds = client.getCurrentAccount().getSold();
				sold = client.getCurrentAccount().getSold();
				if (sold < 0)
					flag = 1;
			}

			if (client.getSafeAccount() != null)
				solds = solds + client.getSafeAccount().getSold();
			if (solds > 500000)
				flag = 2;

		}
		if (flag == 1)
			return "decouvert";
		else if (flag == 2)
			return "isRich";
		else
			return "";
	}

	public void notificationFullClient() {
		LOGGER.info("Operation ajout client echec");
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notification",
				"Le conseiller a plus de 10 clients");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String forwardToAddClient() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		ExternalContext extCtx = ctx.getExternalContext();
		Map<String, Object> sessionMap = extCtx.getSessionMap();
		Advisor advisor = (Advisor) sessionMap.get("advisor");
		if (advisor.getClients().size() < 10) {
			LOGGER.debug("Ajout d'un client " + advisor.getClients().size());
			notificationSuccess("Ajout client");
			return "ajoutClient?faces-redirect=true";
		} else {
			notificationFullClient();
			return "";
		}

	}

}
