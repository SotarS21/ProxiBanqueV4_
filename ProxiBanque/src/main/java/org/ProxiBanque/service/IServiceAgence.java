package org.ProxiBanque.service;

import org.ProxiBanque.model.Agence;

/**
 * Interface de service concernant l'agence, elle est appeler dans les controllers addapter ou dans un webService .
 * 
 * 
* @author kevin, jonas, andy, mathieu
* @version 1.0
*/

public interface IServiceAgence {

	public Agence findOne(Long id);

	
}
