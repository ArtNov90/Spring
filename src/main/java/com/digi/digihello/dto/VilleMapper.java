package com.digi.digihello.dto;

import com.digi.digihello.model.Departement;
import com.digi.digihello.model.Ville;

public class VilleMapper {
	// Mapper Ville -> VilleDto
	public static VilleDto toVilleDto(Ville ville) {
		if (ville == null) {
			return null;
		}

		// Crée un nouvel objet VilleDto avec les données de l'objet Ville
		return new VilleDto(ville.getId(), 
				ville.getNbHabitants(), 
				ville.getDepartement().getId(), 
				ville.getDepartement().getNom() 
		);
	}

	// Mapper VilleDto -> Ville
	public static Ville toVilleEntity(VilleDto villeDto) {
		if (villeDto == null) {
			return null;
		}

		// Crée un nouvel objet Ville avec les données de l'objet VilleDto
		Ville ville = new Ville();
		ville.setId(villeDto.getCodeVille());
		ville.setNbHabitants(villeDto.getNbHabitants());

		// Assure-vous que l'objet Departement existe avant de l'affecter
		Departement departement = new Departement();
		departement.setId(villeDto.getCodedepartement());
		departement.setNom(villeDto.getNomdepartement());

		ville.setDepartement(departement);

		return ville;
	}
}
