package org.ProxiBanque.exception;

/**
 * implémentation de l'interface de service concernant le conseiller, elle est appeler dans les controllers addapter ou dans un webService
 * 
* @author kevin, jonas, andy, mathieu
* @version 1.0
*/
public class VirementException extends Exception {

	/**
	 * Classe d'erreur nécessaire à la levé d'une exception durant l'appel de la fonction virement du service Compte,
	 * permettant une gestion par Programmation Orientée Aspect des transactions générées par le virement.
	 */
	private static final long serialVersionUID = -3466565264610274762L;
	
	private String message = null;

	public VirementException() {

		super();
	}

	public VirementException(String message) {

		super();
		this.message = message;
		
	}
	
	@Override
	public String getMessage() {
		
		return this.message;
	}

	
}
