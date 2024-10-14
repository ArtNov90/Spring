package com.digi.digihello.dto;

import com.digi.digihello.model.Departement;
import com.digi.digihello.model.Ville;

public class DepartementMapper {

    // Mapper Departement -> DepartementDto
    public static DepartementDto toDepartementDto(Departement departement) {
        if (departement == null) {
            return null;
        }
        //Calucler nbhabitants
        int nbHabitants = 0;
        for(Ville v : departement.getVilles()) {
        	nbHabitants += v.getNbHabitants();
        }
        // Crée un nouvel objet DepartementDto avec les données de l'objet Departement
        return new DepartementDto( 
        	nbHabitants,
            departement.getId(),                   
            departement.getNom()               
        );
    }

    // Mapper DepartementDto -> Departement
    public static Departement toDepartementEntity(DepartementDto departementDto) {
        if (departementDto == null) {
            return null;
        }

        // Crée un nouvel objet Departement avec les données de l'objet DepartementDto
        Departement departement = new Departement();
        departement.setId(departementDto.getCodedepartement());
        departement.setNom(departementDto.getNomdepartement());

        return departement;
    }
}
