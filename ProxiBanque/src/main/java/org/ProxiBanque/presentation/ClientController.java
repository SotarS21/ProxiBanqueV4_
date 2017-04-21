package org.ProxiBanque.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;

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

	private static List<Client> listClient = new ArrayList<>();
	private static List<Client> listFilter = new ArrayList<>();
	private static Logger LOGGER = LoggerFactory.getLogger(ClientController.class);
	

	@Autowired
	private IServiceClient serviceClient;
	@Autowired
	private IServiceAccount serviceAccount;

	
	public List<Client> getListClient() {
		return listClient;
	}
	public static List<Client> getListFilter() {
		return listFilter;
	}
	public static void setListFilter(List<Client> listFilter) {
		ClientController.listFilter = listFilter;
	}
	public static void setListClient(List<Client> listClient) {
		ClientController.listClient = listClient;
	}
	
	@PostConstruct
	private void init() {
		
		serviceClient.init();
//		serviceAccount.init();
	}
	


//	static{
//		listClient.add(new Client("toto", "toto", null));
//		listClient.add(new Client("toto1", "toto1", null));
//		listClient.add(new Client("toto1", "toto1", null));
//	}

	
	

	
	public void loadClients() {
		listClient.clear();
		try {

			listClient = serviceClient.findAll(); //serviceClient.findByConseiller_Id(idConseiller)

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
/**
 * Appelle le service pour effectuer une mise à jour sur la basse de données
 * @param client : récupère le client a modifier
 * @return la page de destination. Si erreur dans la méthode il restera sur la page en cours
 */
	public String updateClient(Client client) {
		LOGGER.info("Update Client!");
		try {
			serviceClient.save(client);

		} catch (Exception e) {
			return null;
		}
		return "listClient";
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

	/**
	 * Appelle le service pour supprimer le compte dans la basse de données 
	 * @param idClient : identifiant du client
	 * @param account : compte à supprimer
	 * @return la page de destination. Si erreur dans la méthode il restera sur la page en cours
	 */

	public String cancel(RowEditEvent event) {
		LOGGER.info("Cancel modification!");
		return "listClient";
	}

	/**
	 * Appelle le service pour créer un compt épargne au client dans la basse de données 
	 * @param client : envois le client sur lequel créer un compte épargne
	 * @return la page de destination. Si erreur dans la méthode il restera sur la page en cours
	 */
//	public String addSavingAccount(Client client) {
//		try {
//			serviceAccount.addAccountToClient(client.getId(), etype.SAVING_ACCOUNT, 80);
//		} catch (Exception e) {
//
//			LOGGER.error("error virement:" + e.getMessage());
//			return null;
//		}
//		return "listClient";
//	}

	/**
	 * Appelle le service pour créer un compt courrant au client dans la basse de données 
	 * @param client : envois le client sur lequel créer un compte courrant
	 * @return la page de destination. Si erreur dans la méthode il restera sur la page en cours
	 */
//	public String addCurrentAccount(Client client) {
//		try {
//			serviceAccount.addAccountToClient(client.getId(), etype.CURRENT_ACCOUNT, 80);
//		} catch (Exception e) {
//			LOGGER.error("error virement:" + e.getMessage());
//			return null;
//		}
//		return "listClient";
//	}


}
