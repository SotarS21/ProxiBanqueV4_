package org.ProxiBanque.service;

import java.util.List;

import org.ProxiBanque.model.Client;

/**
 * Interface de service concernant le client, elle est appeler dans les
 * controllers addapter ou dans un webService .
 * 
 * 
 * @author kevin, jonas, andy, mathieu
 * @version 1.0
 */

public interface IServiceClient {
	/**
	 * 
	 * @param id
	 *            Identifiant du client a récupèrer
	 * @return Le client rechercher
	 */
	public Client findOne(Long id);

	/**
	 * 
	 * @return La liste de tous les clients
	 */
	public List<Client> findAll();

	/**
	 * 
	 * @param idConseiller
	 *            identifiant du conseiller
	 * @return La liste des tous les clients d'un conseiller
	 */
	public List<Client> findByConseiller_Id(Long idConseiller);

	/**
	 * 
	 * @param lastName
	 *            prenom
	 * @param firstName
	 *            nom
	 * @return la liste des clients ayant le : lastName et firstName
	 */
	public List<Client> findAllByLastNameAndFirstNameAllIgnoreCase(String lastName, String firstName);

	/**
	 * 
	 * @param client
	 *            client à pesister
	 */
	public void save(Client client);

	/**
	 * 
	 * @param client
	 *            client à modifier
	 */
	public void update(Client client);

	/**
	 * 
	 * @param id identifiant du client à supprimer
	 */
	public void delete(Long id);

	/**
	 * methode de test d'initialisation du service
	 */
	public void init();

}
