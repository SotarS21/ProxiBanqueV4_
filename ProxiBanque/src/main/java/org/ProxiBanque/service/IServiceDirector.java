package org.ProxiBanque.service;

import java.util.List;

import org.ProxiBanque.model.Director;

/**
 * Interface de service concernant le directeur, elle est appeler dans les
 * controllers addapter ou dans un webService .
 * 
 * 
 * @author kevin, jonas, andy, mathieu
 * @version 1.0
 */
public interface IServiceDirector {

	/**
	 * 
	 * @param id
	 *            identifiant du directeur
	 * @return le directeur associer � : id
	 */
	public Director findOne(Long id);

	/**
	 * 
	 * @return liste de tous les directeurs
	 */
	public List<Director> findAll();

	/**
	 * 
	 * @param dir
	 *            directeur � sauvegarder en base de donn�es
	 */
	public void save(Director dir);

	/**
	 * 
	 * @param dir
	 *            directeur � modifier en base de donn�es
	 */
	public void update(Director dir);

	/**
	 * 
	 * @param id identifiant du directeur � supprimer
	 */
	public void delete(Long id);
}
