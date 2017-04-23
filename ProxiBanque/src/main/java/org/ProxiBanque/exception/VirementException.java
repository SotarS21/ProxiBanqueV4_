package org.ProxiBanque.exception;

public class VirementException extends Exception {

	/**
	 * 
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
