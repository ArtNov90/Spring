package com.digi.digihello.villes;

public class Ville {
	
	private String nom;
	private String nbHabitants;
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getNbHabitants() {
		return nbHabitants;
	}
	public void setNbHabitants(String nbHabitants) {
		this.nbHabitants = nbHabitants;
	}
	public Ville(String nom, String nbHabitants) {
		super();
		this.nom = nom;
		this.nbHabitants = nbHabitants;
	}

}
