package org.ProxiBanque.model;

public class CompteCourant extends CompteBancaire{

	private double decouvert;

	public CompteCourant(double decouvert, double sold) {
		super(sold);
		this.decouvert = decouvert;
	}

	public double getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}
	
	
	
}
