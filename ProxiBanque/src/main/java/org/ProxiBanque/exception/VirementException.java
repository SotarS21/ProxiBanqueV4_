package org.ProxiBanque.exception;

/**
 * impl�mentation de l'interface de service concernant le conseiller, elle est appeler dans les controllers addapter ou dans un webService
 * 
* @author kevin, jonas, andy, mathieu
* @version 1.0
*/
public class VirementException extends Exception {

	/**
	 * Classe d'erreur n�cessaire � la lev� d'une exception durant l'appel de la fonction virement du service Compte,
	 * permettant une gestion par Programmation Orient�e Aspect des transactions g�n�r�es par le virement.
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
