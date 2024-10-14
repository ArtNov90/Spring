package com.digi.digihello.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.digi.digihello.model.Ville;
import com.digi.digihello.repository.VilleRepository;

@Service
public class VilleService {

    @Autowired
    private VilleRepository villeRepository;

    // Méthode pour extraire toutes les villes
    public List<Ville> extractVilles() {
        return villeRepository.findAll();
    }

  

    // Méthode pour extraire une ville par son nom
    public List<Ville> extractVille(String nom) {
        return villeRepository.findByNomStartingWith(nom);
    }

    // Méthode pour insérer une nouvelle ville et retourner la liste mise à jour
    public List<Ville> insertVille(Ville ville) {
        villeRepository.save(ville);
        return villeRepository.findAll();
    }


    // Méthode pour supprimer une ville par son ID et retourner la liste mise à jour
    public List<Ville> supprimerVille(int idVille) {
        villeRepository.deleteById(idVille);
        return villeRepository.findAll();
    }

    // Méthode pour rechercher des villes dont la population est supérieure à un certain minimum
    public List<Ville> rechercherVillesPopulationSuperieureA(int min) {
        return villeRepository.findByNbHabitantsGreaterThan(min);
    }

    // Méthode pour rechercher des villes dans un département avec une population entre min et max
    public List<Ville> rechercherVillesDansDepartementAvecPopulationEntre(int departementId, int min, int max) {
        return villeRepository.findByDepartementIdAndNbHabitantsGreaterThanAndNbHabitantsLessThan(departementId, min, max);
    }

    // Méthode pour obtenir les n villes les plus peuplées dans un département
    //public List<Ville> rechercherTopNPlusPeupleesDansDepartement(int departementId, int n) {
       // return villeRepository.findByDepartementIdOrderByNbHabitantsDesc(departementId, Pageable.ofSize(n)).getContent();
    }


