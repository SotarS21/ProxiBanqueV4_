package org.ProxiBanque.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;

import org.ProxiBanque.exception.VirementException;
import org.ProxiBanque.model.BankAccount;
import org.ProxiBanque.model.Client;
import org.ProxiBanque.service.IServiceAccount;
import org.ProxiBanque.service.IServiceClient;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Classe de contrôler permettant d'effectuer des actions sur le client dans nos pages xhtml
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
	private double value;

	private  List<Client> listClient = new ArrayList<>();
	private  List<Client> listFilter = new ArrayList<>();
	private static Logger LOGGER = LoggerFactory.getLogger(ClientController.class);
	

	@Autowired
	private IServiceClient serviceClient;
	@Autowired
	private IServiceAccount serviceAccount;

	
	public List<Client> getListClient() {
		return listClient;
	}
	public List<Client> getListFilter() {
		return listFilter;
	}

	
	@PostConstruct
	private void init() {
		
		serviceClient.init();
//		serviceAccount.init();
	}
	

	
	

	
	public void loadClients() {
		listClient.clear();
		LOGGER.debug("Load all client in BDD");
		try {
			listClient = serviceClient.findAll();//findByConseiller_Id(3L);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
/**
 * Appelle le service pour effectuer une mise à jour sur la basse de données
 * @param client : récupère le client a modifier
 * @return la page de destination. Si erreur dans la méthode il restera sur la page en cours
 */
	public void updateClient() {
		LOGGER.debug("Update Client!");
//		try {
//			serviceClient.update(client);
//
//		} catch (Exception e) {
//			return null;
//		}
//		return "listClient";
	}

	/**
	 * Appelle le service pour supprimer le client dans la basse de données 
	 * @param id : identifiant du client
	 * @return la page de destination. Si erreur dans la méthode il restera sur la page en cours
	 */
	public String deleteClient(long id) {
		LOGGER.info("Delete CLient!");
		try {
			serviceClient.delete(id);
		} catch (Exception e) {

			LOGGER.error("error virement:" + e.getMessage());
			return null;
		}
		return "listClient";
	}
	
	public List<BankAccount> accountLoad(Client client){
			List<BankAccount> listAccount = new ArrayList<>();
			if (! (client.getSafeAccount() == null)) {
				
				listAccount.add(client.getSafeAccount());
			}
			if(! (client.getCurrentAccount() == null)) {
				
				listAccount.add(client.getCurrentAccount());
			}
		return listAccount;
	}

	/**
	 * Appelle le service pour supprimer le compte dans la basse de données 
	 * @param idClient : identifiant du client
	 * @param account : compte à supprimer
	 * @return la page de destination. Si erreur dans la méthode il restera sur la page en cours
	 */

	public void cancel(RowEditEvent event) {
		LOGGER.info("Cancel modification!");
	}

	/**
	 * Appelle le service pour créer un compt épargne au client dans la basse de données 
	 * @param client : envois le client sur lequel créer un compte épargne
	 * @return la page de destination. Si erreur dans la méthode il restera sur la page en cours
	 */
	public String addSavingAccount(Client client) {
		try {
			//serviceAccount.addAccountToClient(client.getId(), e_AccountType.SAVING_ACCOUNT, 80);
		} catch (Exception e) {

			LOGGER.error("error virement:" + e.getMessage());
			return null;
		}
		return "listClient";
	}

	/**
	 * Appelle le service pour créer un compt courrant au client dans la basse de données 
	 * @param client : envois le client sur lequel créer un compte courrant
	 * @return la page de destination. Si erreur dans la méthode il restera sur la page en cours
	 */
	public String addCurrentAccount(Client client) {
		try {
			//serviceAccount.addAccountToClient(client.getId(), e_AccountType.CURRENT_ACCOUNT, 80);
		} catch (Exception e) {
			LOGGER.error("error virement:" + e.getMessage());
			return null;
		}
		return "listClient";
	}
	
	public String forwardToVirement(BankAccount account) {
		
		bankAccount = account;
		return "virement";
	}
	
	public String virement(BankAccount account) {
		
		try {
			serviceAccount.doVirement(bankAccount, account, value);
		} catch (VirementException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
		}
		return "listClient";
	}
	public BankAccount getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	
}
