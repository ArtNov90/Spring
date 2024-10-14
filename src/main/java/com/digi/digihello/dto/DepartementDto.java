package com.digi.digihello.dto;

public class DepartementDto {
	private int nbHabitants;
	private int codedepartement;
	private String nomdepartement;
	public DepartementDto(int nbHabitants, int codedepartement, String nomdepartement) {
		super();
		this.nbHabitants = nbHabitants;
		this.codedepartement = codedepartement;
		this.nomdepartement = nomdepartement;
	}
	public int getNbHabitants() {
		return nbHabitants;
	}
	public void setNbHabitants(int nbHabitants) {
		this.nbHabitants = nbHabitants;
	}
	public int getCodedepartement() {
		return codedepartement;
	}
	public void setCodedepartement(int codedepartement) {
		this.codedepartement = codedepartement;
	}
	public String getNomdepartement() {
		return nomdepartement;
	}
	public void setNomdepartement(String nomdepartement) {
		this.nomdepartement = nomdepartement;
	}
}
