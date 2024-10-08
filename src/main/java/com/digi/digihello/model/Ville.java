package com.digi.digihello.model;

public class Ville {

	private String nom;
	private String nbHabitants;
	private int id;

	public Ville(int id, String nom, String nbHabitants) {
		this.id = id;
		this.nom = nom;
		this.nbHabitants = nbHabitants;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
}
