package org.ProxiBanque.model;

public class CompteEpargne extends CompteBancaire{
	
	private double tauxDeRemuneration;

	public CompteEpargne(double tauxDeRemuneration, double sold) {
		super(sold);
		this.tauxDeRemuneration = tauxDeRemuneration;
	}

	public CompteEpargne() {
		super();
	}

	public double getTauxDeRemuneration() {
		return tauxDeRemuneration;
	}

	public void setTauxDeRemuneration(double tauxDeRemuneration) {
		this.tauxDeRemuneration = tauxDeRemuneration;
	}
	
	
	
	
	

}
