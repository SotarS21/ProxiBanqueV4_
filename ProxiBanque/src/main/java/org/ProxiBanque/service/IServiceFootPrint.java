package org.ProxiBanque.service;

import java.util.List;

import org.ProxiBanque.model.Footprint;
/**
 * Interface de service concernant le footPrint, elle est appeler dans les
 * controllers addapter ou dans un webService .
 * 
 * 
 * @author kevin, jonas, andy, mathieu
 * @version 1.0
 */
public interface IServiceFootPrint {
	/**
	 * 
	 * @param id identifiant de la trace
	 * @return la trace associer à : id
	 */ 
	public Footprint findOne(Long id);
	/**
	 * 
	 * @return liste de toutes les Trace
	 */
	public List<Footprint> findAll();
	/**
	 * 
	 * @param footPrint trace à sauvegarder en base de données
	 */
	public void save(Footprint footPrint);
	/**
	 * 
	 * @param footPrint trace à modifier en base de données
	 */
	public void update(Footprint footPrint);
	/**
	 * 
	 * @param id identifiant de la trace à supprimer
	 */
	public void delete(Long id);
}
