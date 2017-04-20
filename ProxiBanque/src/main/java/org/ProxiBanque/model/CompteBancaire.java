package org.ProxiBanque.model;

public abstract class CompteBancaire {

	private double sold;

	public CompteBancaire() {
		super();
	}
	
	

	public CompteBancaire(double sold) {
		super();
		this.sold = sold;
	}



	public double getSold() {
		return sold;
	}

	public void setSold(double sold) {
		this.sold = sold;
	}
	
	
	
	
}
